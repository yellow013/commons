package org.beam.transport.module;

import org.beam.transport.base.role.Publisher;
import org.beam.transport.base.role.Receiver;
import org.beam.transport.base.role.Sender;
import org.beam.transport.base.role.Subscriber;
import org.beam.transport.jeromq.JeroMqPublisher;
import org.beam.transport.jeromq.JeroMqReceiver;
import org.beam.transport.jeromq.JeroMqSender;
import org.beam.transport.jeromq.JeroMqSubscriber;

import com.google.inject.AbstractModule;

@Deprecated
public class TransportModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Publisher.class).to(JeroMqPublisher.class);
		bind(Subscriber.class).to(JeroMqSubscriber.class);
		bind(Sender.class).to(JeroMqSender.class);
		bind(Receiver.class).to(JeroMqReceiver.class);
	}

}
