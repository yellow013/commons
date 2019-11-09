package io.ffreedom.common.di;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;

public abstract class InjectionComponent {

	private Logger logger;

	public InjectionComponent() {
	}

	public InjectionComponent(Logger logger) {
		this.logger = logger;
	}

	@PostConstruct
	private void postConstruct() {
		initComponent();
		if (logger != null)
			logger.info("Component -> {} is loading finished.", getClass().getName());
	}

	protected abstract void initComponent();

}
