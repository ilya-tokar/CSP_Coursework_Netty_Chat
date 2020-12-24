package Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public class ChatLogic {

    public void SendAllMsg(ChannelHandlerContext ctx, ChannelGroup channels){
        channels.add(ctx.channel());
        Channel incoming = ctx.channel();
        System.out.println("[CLIENT] - " + incoming.remoteAddress() + " has joined");
        for (Channel channel : channels) {
            channel.writeAndFlush(("[SERVER] - " + incoming.remoteAddress() + " has joined\n"));
        }
    }

    public void ClientDisconect(ChannelHandlerContext ctx, ChannelGroup channels){
        Channel incoming = ctx.channel();
        System.out.println("[CLIENT] - " + incoming.remoteAddress() + " has removed");
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has removed\n");
        }
        channels.remove(ctx.channel());
    }

    public void ReadMsg(ChannelHandlerContext ctx, ChannelGroup channels, Object obj){
        ctx.fireChannelRead(obj);
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + obj.toString() + "\n");
            }
        }
    }
}
