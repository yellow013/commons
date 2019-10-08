package io.ffreedom.common.collections;

public interface InitialCapacity {

	int size();

	// 2
	InitialCapacity POW2_1 = () -> 2;

	// 4
	InitialCapacity POW2_2 = () -> 4;

	// 8
	InitialCapacity POW2_3 = () -> 8;

	// 16
	InitialCapacity POW2_4 = () -> 16;

	// 32
	InitialCapacity POW2_5 = () -> 32;

	// 64
	InitialCapacity POW2_6 = () -> 64;

	// 128
	InitialCapacity POW2_7 = () -> 128;

	// 256
	InitialCapacity POW2_8 = () -> 256;

	// 512
	InitialCapacity POW2_9 = () -> 512;

	// 1024
	InitialCapacity POW2_10 = () -> 1024;

	// 2048
	InitialCapacity POW2_11 = () -> 2048;

	// 4096
	InitialCapacity POW2_12 = () -> 4096;

	// 8192
	InitialCapacity POW2_13 = () -> 8192;

	// 16384
	InitialCapacity POW2_14 = () -> 16384;

	// 32768
	InitialCapacity POW2_15 = () -> 32768;

	// 65536
	InitialCapacity POW2_16 = () -> 65536;

}
