package xyz.gmitch215.socketmc.forge.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import xyz.gmitch215.socketmc.ModAuditLog;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.config.ModPermission;
import xyz.gmitch215.socketmc.forge.machines.ForgeMachineFinder;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.machines.MachineFinder;
import xyz.gmitch215.socketmc.spigot.SocketPlugin;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;

@Mixin(PacketDecoder.class)
public class PacketDecoderMixin {

    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list, CallbackInfo ci) {
        if (byteBuf.readableBytes() == 0) return;

        try {
            FriendlyByteBuf buf = new FriendlyByteBuf(byteBuf.copy());
            int id = buf.readVarInt();
            if (id == -2) {
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
                            MachineFinder.getMachine(ForgeMachineFinder.MACHINES, i.getId()).onInstruction(i);
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
        } catch (Exception e) {
            SocketMC.print(e);
        }
    }

}
