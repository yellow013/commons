package io.ffreedom.transport.base.config;

import io.ffreedom.common.config.Configurator;

public interface TransportConfigurator extends Configurator {

	public String getHost();

	public int getPort();

}
