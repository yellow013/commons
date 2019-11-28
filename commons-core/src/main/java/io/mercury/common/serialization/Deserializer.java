package io.mercury.common.serialization;

import java.util.List;

public interface Deserializer<T, F> {

	T deSerializationSingle(F f);

	List<T> deSerializationMultiple(F f);

}
