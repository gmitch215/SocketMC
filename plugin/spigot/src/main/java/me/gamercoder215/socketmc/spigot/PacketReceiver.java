package me.gamercoder215.socketmc.spigot;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.FriendlyByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

@SuppressWarnings("unchecked")
final class PacketReceiver extends ChannelDuplexHandler {

    private final SocketPlayer player;

    PacketReceiver(SocketPlayer player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FriendlyByteBuf buf) {
            FriendlyByteBuf clone = new FriendlyByteBuf(buf.copy());
            if (clone.readVarInt() == -3) {
                buf.clear();

                int id = clone.readVarInt();
                byte[] params = clone.readByteArray();

                ByteArrayInputStream bis = new ByteArrayInputStream(params);
                ObjectInputStream ois = new ObjectInputStream(bis);
                Map<String, Object> parameters = (Map<String, Object>) ois.readObject();

                EventFactory.call(player, id, parameters);
            }
        }
    }

}
