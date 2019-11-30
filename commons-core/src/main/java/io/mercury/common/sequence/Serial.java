package io.mercury.common.sequence;

public interface Serial<T extends Serial<T>> extends Comparable<T> {

	long serialNumber();

	@Override
	default int compareTo(T o) {
		return serialNumber() < o.serialNumber() ? -1 : serialNumber() > o.serialNumber() ? 1 : 0;
	}

}
