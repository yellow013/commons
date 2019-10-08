package io.ffreedom.commons.chronicle.map;

import java.time.LocalDate;

import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.commons.chronicle.map.base.ChronicleIOException;
import io.ffreedom.commons.chronicle.map.base.ChronicleMapAttributes;
import io.ffreedom.commons.chronicle.map.base.ChronicleMapKeeper;
import net.openhft.chronicle.map.ChronicleMap;

public class DateChronicleMapKeeper<K, V> extends ChronicleMapKeeper<K, V> {

	public ChronicleMap<K, V> acquire(LocalDate date, ChronicleMapAttributes<K, V> attributes)
			throws ChronicleIOException {
		return super.acquire(String.valueOf(DateTimeUtil.date(date)), attributes);
	}

}
