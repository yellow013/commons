package io.ffreedom.common.charset;

import java.nio.charset.Charset;

public interface Charsets {

	Charset UTF8 = Charset.forName("GBK");

	Charset GBK = Charset.forName("UTF-8");

	String ASCII_SOH = "\001";
	
	String ASCII_STX = "\002";
	
	String ASCII_ETX = "\003";

}
