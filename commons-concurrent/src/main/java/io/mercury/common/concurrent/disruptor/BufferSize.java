package io.mercury.common.concurrent.disruptor;

public interface BufferSize {

	int size();

	BufferSize POW2_1 = () -> 2;
	BufferSize POW2_2 = () -> 4;
	BufferSize POW2_3 = () -> 8;
	BufferSize POW2_4 = () -> 16;
	BufferSize POW2_5 = () -> 32;
	BufferSize POW2_6 = () -> 64;
	BufferSize POW2_7 = () -> 128;
	BufferSize POW2_8 = () -> 256;
	BufferSize POW2_9 = () -> 512;
	BufferSize POW2_10 = () -> 1024;
	BufferSize POW2_11 = () -> 2048;
	BufferSize POW2_12 = () -> 4096;
	BufferSize POW2_13 = () -> 8192;
	BufferSize POW2_14 = () -> 16384;
	BufferSize POW2_15 = () -> 32768;
	BufferSize POW2_16 = () -> 65536;

}
