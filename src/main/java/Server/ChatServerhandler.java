package Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerhandler extends ChannelInboundHandlerAdapter {
	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		ChatLogic logic = new ChatLogic();
		logic.SendAllMsg(ctx,channels);
	}
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		ChatLogic logic = new ChatLogic();
		logic.ClientDisconect(ctx, channels);

	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		ChatLogic logic = new ChatLogic();
		logic.ReadMsg(ctx, channels, obj);
	}


}
