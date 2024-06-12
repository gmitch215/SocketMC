package me.gamercoder215.socketmc.fabric.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.gamercoder215.socketmc.SocketMC;
import me.gamercoder215.socketmc.fabric.FabricAuditLog;
import me.gamercoder215.socketmc.fabric.machines.FabricMachineFinder;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.machines.MachineFinder;
import me.gamercoder215.socketmc.spigot.SocketPlugin;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.minecraft;

@Mixin(PacketDecoder.class)
public class PacketDecoderMixin {

    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list, CallbackInfo ci) {
        if (byteBuf.readableBytes() == 0) return;

        try {
            FriendlyByteBuf buf = new FriendlyByteBuf(byteBuf.copy());
            int id = buf.readVarInt();
            if (id == -2) {
                byteBuf.clear();
                ci.cancel();

                byte[] i0 = buf.readByteArray();
                Instruction i = Instruction.fromByteArray(i0);

                byte[] p0 = buf.readByteArray();
                SocketPlugin p = SocketPlugin.fromByteArray(p0);

                minecraft.execute(() -> {
                    try {
                        MachineFinder.getMachine(FabricMachineFinder.MACHINES, i.getId()).onInstruction(i);
                        SocketMC.LOGGER.info(FabricAuditLog.CLIENT_RECEIVED_MESSAGE, i, i0.length);
                        FabricAuditLog.INSTANCE.logReceived(i, p);
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
