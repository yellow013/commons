package io.ffreedom.transport.netty;

import io.ffreedom.transport.base.role.TransportClient;
import io.ffreedom.transport.netty.config.NettyConfigurator;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient extends NettyTransport implements TransportClient {

	private Bootstrap bootstrap;

	public NettyClient(String tag, NettyConfigurator configurator, ChannelHandler... channelHandlers) {
		super(tag, configurator, channelHandlers);
	}

	@Override
	protected void init() {
		this.bootstrap = new Bootstrap();
		this.bootstrap.group(workerGroup).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(channelHandlers);
					}
				}).option(ChannelOption.SO_KEEPALIVE, configurator.isKeepAlive())
				.option(ChannelOption.TCP_NODELAY, configurator.isTcpNoDelay());
		logger.info(tag + ": Init-BootStrap.connect -> " + configurator.getPort());

	}

	@Override
	public void connect() {
		try {
			// Start the client.
			bootstrap.connect(configurator.getHost(), configurator.getPort()).sync()
					// Wait until the connection is closed.
					.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("NettyClient method connection() -> {}", e.getMessage(), e);
			destroy();
		}
	}

	@Override
	public boolean isConnected() {
		return workerGroup.isShutdown();
	}

	@Override
	public boolean destroy() {
		logger.info("NettyClient call method destroy().");
		workerGroup.shutdownGracefully();
		return true;
	}

}