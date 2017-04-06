package org.beam.transport.serializable;

import org.beam.common.serialization.SerializableBean;
import org.beam.transport.serializable.base.AbsByteSerialization;

public class ProtobufSerialization extends AbsByteSerialization {

	@Override
	public byte[] serialization(SerializableBean t) {
		return null;
	}

	@Override
	public SerializableBean deSerialization(byte[] r, Class<SerializableBean> tclass) {
		return null;
	}

}
