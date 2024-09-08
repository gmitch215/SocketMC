package xyz.gmitch215.socketmc.neoforge.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.gmitch215.socketmc.ModAuditLog;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.config.ModPermission;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.machines.MachineFinder;
import xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC;
import xyz.gmitch215.socketmc.neoforge.machines.NeoForgeMachineFinder;
import xyz.gmitch215.socketmc.neoforge.NeoForgeRetriever;
import xyz.gmitch215.socketmc.retriever.Retriever;
import xyz.gmitch215.socketmc.retriever.RetrieverType;
import xyz.gmitch215.socketmc.spigot.SocketPlugin;

import java.util.List;
import java.util.UUID;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@Mixin(PacketDecoder.class)
public class PacketDecoderMixin {

    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list, CallbackInfo ci) {
        if (byteBuf.readableBytes() == 0) return;

        try {
            FriendlyByteBuf buf = new FriendlyByteBuf(byteBuf.copy());
            int id = buf.readVarInt();
            switch (id) {
                // Instruction Sent
                case -2: {
                    ci.cancel();
                    byteBuf.clear();

                    byte[] i0 = buf.readByteArray();
                    Instruction i = Instruction.fromByteArray(i0);

                    byte[] p0 = buf.readByteArray();
                    SocketPlugin p = SocketPlugin.fromByteArray(p0);

                    minecraft.execute(() -> {
                        try {
                            ModPermission perm = i.getPermission();
                            if (SocketMC.isPermissionEnabled(p, perm)) {
                                MachineFinder.getMachine(NeoForgeMachineFinder.MACHINES, i.getId()).onInstruction(i);
                            } else {
                                SocketMC.LOGGER.warn("Plugin {} tried to execute instruction {} without permission", p.getPluginName(), i.getId());
                                ModAuditLog.INSTANCE.log("Plugin " + p.getPluginName() + " tried to execute instruction '" + i.getId() + "' without permission");
                            }

                            SocketMC.LOGGER.info(ModAuditLog.CLIENT_RECEIVED_MESSAGE, i, i0.length);
                            SocketMC.addPlugin(p);
                            ModAuditLog.INSTANCE.logReceived(i, p);
                        } catch (Exception e) {
                            SocketMC.print(e);
                        }
                    });
                }
                // Retriever Requested
                case -4: {
                    byteBuf.clear();
                    ci.cancel();

                    UUID retriever = buf.readUUID();

                    byte[] r0 = buf.readByteArray();
                    RetrieverType<?> r = RetrieverType.fromByteArray(r0);

                    byte[] p0 = buf.readByteArray();
                    SocketPlugin p = SocketPlugin.fromByteArray(p0);

                    Object value = Retriever.value(r, NeoForgeRetriever.PROPERTIES);

                    NeoForgeSocketMC.minecraft.execute(() -> {
                        try {
                            ModPermission perm = r.getPermission();
                            if (SocketMC.isPermissionEnabled(p, perm)) {
                                NeoForgeRetriever.response(retriever, value);
                            } else {
                                SocketMC.LOGGER.warn("Plugin {} tried to retrieve {} without permission", p.getPluginName(), r.getId());
                                ModAuditLog.INSTANCE.log("Plugin " + p.getPluginName() + " tried to retrieve '" + r.getId() + "' without permission");
                            }

                            SocketMC.LOGGER.info(ModAuditLog.CLIENT_RECEIVED_MESSAGE, r, r0.length);
                            SocketMC.addPlugin(p);
                            ModAuditLog.INSTANCE.logReceived(r, p, value);
                        } catch (Exception e) {
                            SocketMC.print(e);
                        }
                    });
                    break;
                }
            }
        } catch (Exception e) {
            SocketMC.print(e);
        }
    }

}
