package io.ffreedom.common.number.impl;

import io.ffreedom.common.number.DoubleUtil;
import io.ffreedom.common.number.api.Num;

public final class DoubleNum implements Num<DoubleNum> {

	private double value;
	private boolean isPrecision8;

	private DoubleNum(double value, boolean isPrecision8) {
		this.value = value;
		this.isPrecision8 = isPrecision8;
	}

	public static final DoubleNum ofPrecision4(double value) {
		return new DoubleNum(value, false);
	}

	public static final DoubleNum ofPrecision8(double value) {
		return new DoubleNum(value, true);
	}

	@Override
	public DoubleNum copy() {
		return new DoubleNum(value, isPrecision8);
	}

	@Override
	public DoubleNum plusBy(Num<?> augend) {
		if (isPrecision8)
			this.value = DoubleUtil.add8(value, augend.doubleValue());
		else
			this.value = DoubleUtil.add4(value, augend.doubleValue());
		return this;
	}

	@Override
	public DoubleNum minusBy(Num<?> subtrahend) {
		this.value = DoubleUtil.subtraction(value, subtrahend.doubleValue());
		return this;
	}

	@Override
	public DoubleNum multipliedBy(Num<?> multiplicand) {
		if (isPrecision8)
			this.value = DoubleUtil.multiply8(value, multiplicand.doubleValue());
		else
			this.value = DoubleUtil.multiply4(value, multiplicand.doubleValue());
		return this;
	}

	@Override
	public DoubleNum dividedBy(Num<?> divisor) {
		this.value = DoubleUtil.division(value, divisor.doubleValue());
		return this;
	}

	@Override
	public DoubleNum powBy(int n) {
		this.value = Math.pow(value, n);
		return this;
	}

	@Override
	public boolean isZero() {
		return value == Num.DoubleZero;
	}

	@Override
	public boolean isPositive() {
		return value > Num.DoubleZero;
	}

	@Override
	public boolean isPositiveOrZero() {
		return value >= Num.DoubleZero;
	}

	@Override
	public boolean isNegative() {
		return value < Num.DoubleZero;
	}

	@Override
	public boolean isNegativeOrZero() {
		return value <= Num.DoubleZero;
	}

	@Override
	public boolean isEqual(Num<?> other) {
		return value == other.doubleValue();
	}

	@Override
	public boolean isGreaterThan(Num<?> other) {
		return value > other.doubleValue();
	}

	@Override
	public boolean isGreaterThanOrEqual(Num<?> other) {
		return value >= other.doubleValue();
	}

	@Override
	public boolean isLessThan(Num<?> other) {
		return value < other.doubleValue();
	}

	@Override
	public boolean isLessThanOrEqual(Num<?> other) {
		return value <= other.doubleValue();
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return (long) value;
	}

	@Override
	public boolean isNaN() {
		return Double.isNaN(value);
	}

}