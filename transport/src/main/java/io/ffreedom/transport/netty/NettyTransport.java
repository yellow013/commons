package io.ffreedom.transport.netty;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.transport.netty.config.NettyConfigurator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public abstract class NettyTransport {

	protected String tag;
	protected NettyConfigurator configurator;

	protected EventLoopGroup workerGroup;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected ChannelHandler[] channelHandlers;

	public NettyTransport(String tag, NettyConfigurator configurator, ChannelHandler... channelHandlers) {
		if (configurator == null) {
			throw new IllegalArgumentException("Param-configurator is null in NettyTransport constructor.");
		}
		if (channelHandlers == null) {
			throw new IllegalArgumentException("Param-channelHandlers is null in NettyTransport constructor.");
		}
		this.tag = tag;
		this.configurator = configurator;
		this.channelHandlers = channelHandlers;
		this.workerGroup = new NioEventLoopGroup();
		init();
	}

	protected abstract void init();

	public String getName() {
		return tag;
	}

}
