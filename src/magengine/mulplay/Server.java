package magengine.mulplay;

import java.io.Closeable;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Server implements Closeable {

	private int port;
	private NioEventLoopGroup bossLoop;
	private NioEventLoopGroup workerLoop;

	public Server(int port) {
		this.port = port;
	}

	public void start() {
		ServerBootstrap boot = new ServerBootstrap();
		NioEventLoopGroup bossLoop = new NioEventLoopGroup(2);
		this.bossLoop = bossLoop;
		NioEventLoopGroup workerLoop = new NioEventLoopGroup();
		this.workerLoop = workerLoop;
		try {
			boot.channel(NioServerSocketChannel.class).childOption(ChannelOption.TCP_NODELAY, true)
					.group(bossLoop, workerLoop).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
									.addLast(new LineBasedFrameDecoder(1024))
									.addLast(new StringDecoder())
									.addLast(new StringEncoder())
									.addLast(new ServerHandler());
						}
					});
			ChannelFuture cf = boot.bind(port).sync();
			cf.channel().closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					close();
				}
			});
		} catch (Exception e) {
			Thread.interrupted();
			e.printStackTrace();
			close();
		}
	}

	public void close() {
		this.bossLoop.shutdownGracefully();
		this.workerLoop.shutdownGracefully();

	}
	public NioEventLoopGroup getBossLoop() {
		return bossLoop;
	}
}
