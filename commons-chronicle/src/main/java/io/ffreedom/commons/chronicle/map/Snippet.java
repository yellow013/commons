package io.ffreedom.commons.chronicle.map;

import java.time.LocalDate;

import io.ffreedom.common.env.SysProperty;
import io.ffreedom.commons.chronicle.map.base.ChronicleMapAttributes;
import net.openhft.chronicle.map.ChronicleMap;

public class Snippet {

	public static void main(String[] args) {

		ChronicleMapAttributes<String, byte[]> attributes = ChronicleMapAttributes.buildOf(String.class, byte[].class)
				.setRootPath(SysProperty.USER_HOME).setFolder("betting").setAverageKey("ABCD")
				.setAverageValue("DEAFER".getBytes());

		DateChronicleMapKeeper<String, byte[]> mapKeeper = new DateChronicleMapKeeper<>();

		ChronicleMap<String, byte[]> acquire = mapKeeper.acquire(LocalDate.now(), attributes);

		acquire.put("1", "AA1".getBytes());
		acquire.put("2", "BB2".getBytes());
		acquire.put("3", "CC3".getBytes());
		acquire.put("4", "DD4".getBytes());

	}
}
