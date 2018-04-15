package magengine.game;

import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import magengine.mulplay.Client;
import magengine.mulplay.Server;
import magengine.mulplay.Transport;

public class MulplaySession {
	public static final int PORT = 10231;
	public static String remoteHost = "127.0.0.1";
	public NioEventLoopGroup loopGroup;
	public Channel mulplayChannel;
	public Server server;
	public Client client;
	public Transport clientOrServer;
	public NioEventLoopGroup getLoopGroup() {
		return loopGroup;
	}

	public void setLoopGroup(NioEventLoopGroup loopGroup) {
		this.loopGroup = loopGroup;
	}

	public void setMulplayChannel(Channel mulplayChannel) {
		this.mulplayChannel = mulplayChannel;
	}

	public Channel getMulplayChannel() {
		return mulplayChannel;
	}
}
