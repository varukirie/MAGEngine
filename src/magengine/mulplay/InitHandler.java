package magengine.mulplay;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import magengine.game.GameSession;

public class InitHandler extends ChannelInboundHandlerAdapter{




	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		GameSession.getGameSession().mulSession.setMulplayChannel(ctx.channel());
		super.channelActive(ctx);
	}


	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		GameSession.getGameSession().mulSession.setMulplayChannel(null);
		super.channelInactive(ctx);
	}


}
