package io.ffreedom.common.sequence;

public interface Serial<T extends Serial<?>> extends Comparable<T> {

	long getSerialNumber();

	@Override
	default int compareTo(T o) {
		return getSerialNumber() < o.getSerialNumber() ? -1 : getSerialNumber() > o.getSerialNumber() ? 1 : 0;
	}

}
