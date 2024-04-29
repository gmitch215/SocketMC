package me.gamercoder215.socketmc.forge.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.gamercoder215.socketmc.forge.ForgeSocketMC;
import me.gamercoder215.socketmc.forge.machines.ForgeMachineFinder;
import me.gamercoder215.socketmc.instruction.Instruction;
import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

@Mixin(targets = "net.minecraft.network.PacketDecoder")
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

                byte[] arr = buf.readByteArray();
                Instruction i = Instruction.fromByteArray(arr);

                minecraft.execute(() -> {
                    try {
                        ForgeMachineFinder.getMachine(i.getId()).onInstruction(i);
                        ForgeSocketMC.LOGGER.info("Received instruction: {}, size {} bytes", i, arr.length);
                    } catch (Exception e) {
                        ForgeSocketMC.print(e);
                    }
                });
            }
        } catch (Exception e) {
            ForgeSocketMC.print(e);
        }
    }

}
