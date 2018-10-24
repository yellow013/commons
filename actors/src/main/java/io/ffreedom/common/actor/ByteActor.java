package io.ffreedom.common.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ByteActor extends AbstractActor {

	public static Props props() {
		return Props.create(ByteActor.class, ByteActor::new);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Integer.class, i -> {
			System.out.println("int -> " + i.intValue());
		}).match(Double.class, d -> {
			System.out.println("double -> " + d.doubleValue());
		}).build();
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("HelloActor");
		ActorRef actorRef = system.actorOf(ByteActor.props(), "ByteActor");
		actorRef.tell(12D, ActorRef.noSender());
		system.terminate();
	}

}
