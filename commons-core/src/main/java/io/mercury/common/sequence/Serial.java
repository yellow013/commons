package io.mercury.common.sequence;

public interface Serial<T extends Serial<T>> extends Comparable<T> {

	long getSerialNumber();

	@Override
	default int compareTo(T o) {
		return getSerialNumber() < o.getSerialNumber() ? -1 : getSerialNumber() > o.getSerialNumber() ? 1 : 0;
	}

}
