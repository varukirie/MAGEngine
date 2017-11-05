package magengine.mulplay;

import java.io.Closeable;

import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

public abstract class Transport implements Closeable{
	protected NioEventLoopGroup group;
	protected Channel channel;
	
	public Channel getChannel() {
		return channel;
	}
	public NioEventLoopGroup getGroup() {
		return group;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void setGroup(NioEventLoopGroup group) {
		this.group = group;
	}
}
