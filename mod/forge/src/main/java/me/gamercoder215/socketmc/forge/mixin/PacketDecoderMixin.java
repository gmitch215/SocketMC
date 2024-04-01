package me.gamercoder215.socketmc.forge.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.gamercoder215.socketmc.instruction.Instruction;
import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "net.minecraft.network.PacketDecoder")
public class PacketDecoderMixin {

    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list, CallbackInfo ci) {
        if (byteBuf.readableBytes() == 0) return;

        FriendlyByteBuf buf = new FriendlyByteBuf(byteBuf.copy());
        int id = buf.readVarInt();
        if (id == -2) {
            byte[] arr = buf.readByteArray();
            Instruction i = Instruction.fromByteArray(arr);
            list.add(i);

            ci.cancel();
        }
    }

}
