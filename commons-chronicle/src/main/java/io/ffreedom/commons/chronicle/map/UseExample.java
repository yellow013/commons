package io.ffreedom.commons.chronicle.map;

import io.ffreedom.common.env.SystemPropertys;
import io.ffreedom.common.thread.ThreadUtil;
import net.openhft.chronicle.map.ChronicleMap;

public class UseExample {

	public static void main(String[] args) {

		ChronicleMapAttributes<String, byte[]> attributes = ChronicleMapAttributes
				.buildOf(String.class, byte[].class, SystemPropertys.USER_HOME, "betting")
				.setAverageKey("uuid__game__merOrderId______").setAverageValue(new byte[128]);

		ChronicleMapKeeperOfLocalDate<String, byte[]> mapKeeper = new ChronicleMapKeeperOfLocalDate<>(attributes);

		ChronicleMap<String, byte[]> acquire = mapKeeper.acquire("2019.10.11");
		
		while (true) {
			System.out.println(acquire.size());
			ThreadUtil.sleep(2000);
		}


	}
}
