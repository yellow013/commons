package io.ffreedom.commons.chronicle.map;

import io.ffreedom.common.utils.BinaryUtil;
import io.ffreedom.commons.chronicle.map.base.ChronicleMapAttributes;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class Snippet {

	public static void main(String[] args) {

		ChronicleMapAttributes<String, byte[]> bttributes = ChronicleMapAttributes.buildOf(String.class, byte[].class);

		ChronicleMapBuilder<String, byte[]> builder = ChronicleMapBuilder.of(bttributes.getKeyClass(),
				bttributes.getValueClass());

		System.out.println(BinaryUtil.intToBinaryString(3));

		System.out.println(32 << 16);

		System.out.println(byte[].class.getSimpleName());

		System.out.println("dasfa\\".endsWith("/") || "dasfa\\".endsWith("\\"));

	}
}
