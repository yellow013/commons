package org.beam.transport.netty;

import org.beam.common.charsets.Charsets;
import org.beam.transport.netty.config.NettyConfigurator;

public class ServerTest {

	public static void main(String[] args) {

		NettyConfigurator configurator = NettyConfigurator.builder().setHost("127.0.0.1").setPort(9500).build();

		NettyNioServer nettyNioServer = new NettyNioServer("ServerTest", configurator, (bytes) -> {
			System.out.println(new String(bytes, Charsets.UTF8));
		});

		nettyNioServer.receive();

	}

}
