package xyz.gmitch215.socketmc.spigot;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.FriendlyByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unchecked")
final class PacketReceiver extends ChannelDuplexHandler {

    SocketPlayer player;

    PacketReceiver(SocketPlayer player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf buf) {
            FriendlyByteBuf clone = new FriendlyByteBuf(buf.copy());
            switch (clone.readVarInt()) {
                // Client Event
                case -3: {
                    buf.clear();

                    int id = clone.readVarInt();
                    byte[] params = clone.readByteArray();

                    ByteArrayInputStream bis = new ByteArrayInputStream(params);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Map<String, Object> parameters = (Map<String, Object>) ois.readObject();

                    EventFactory.call(player, id, parameters);
                    break;
                }
                // Retriever Response
                case -5: {
                    buf.clear();

                    UUID id = clone.readUUID();
                    byte[] value0 = clone.readByteArray();

                    ByteArrayInputStream bis = new ByteArrayInputStream(value0);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Object value = ois.readObject();

                    player.retriever.getBus0().remove(id).accept(value);
                    break;
                }
            }
        }

        super.channelRead(ctx, msg);
    }

}
