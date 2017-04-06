package org.beam.common.charsets;

import java.nio.charset.Charset;

public interface Charsets {

	Charset UTF8 = Charset.forName("UTF-8");

	Charset GBK = Charset.forName("GBK");

	interface Code {

		String GBK = "GBK";

		String UTF8 = "UTF-8";

	}

}
