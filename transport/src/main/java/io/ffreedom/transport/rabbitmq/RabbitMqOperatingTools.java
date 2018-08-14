package io.ffreedom.transport.rabbitmq;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.list.mutable.FastList;

public final class RabbitMqOperatingTools {

	static class TestBean {
		private long L;
		private int I;
		private double D;
		private String S;

		public long getL() {
			return L;
		}

		public TestBean setL(long l) {
			L = l;
			return this;
		}

		public int getI() {
			return I;
		}

		public TestBean setI(int i) {
			I = i;
			return this;
		}

		public double getD() {
			return D;
		}

		public TestBean setD(double d) {
			D = d;
			return this;
		}

		public String getS() {
			return S;
		}

		public TestBean setS(String s) {
			S = s;
			return this;
		}

	}

	public static void main(String[] args) {
		
		MutableList<TestBean> list = new FastList<>();
		
		list.add(new TestBean().setI(1).setD(3.0D).setL(2L).setS("AT"));
		list.add(new TestBean().setI(1).setD(4.0D).setL(5L).setS("AT"));
		list.add(new TestBean().setI(2).setD(3.0D).setL(5L).setS("AS"));
		list.add(new TestBean().setI(2).setD(4.0D).setL(5L).setS("AT"));
		
		list.collectDouble(bean->{
			return bean.getD();
		}).forEach(d->{
			System.out.println(d);
		});

		//list.collectIf(Predicates.)
		
		
		
	}

}
