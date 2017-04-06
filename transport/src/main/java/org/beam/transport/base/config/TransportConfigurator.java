package org.beam.transport.base.config;

import org.beam.common.config.Configurator;

public interface TransportConfigurator extends Configurator {

	public String host();

	public int port();

}
