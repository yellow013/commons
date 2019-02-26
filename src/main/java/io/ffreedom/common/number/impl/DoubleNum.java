package io.ffreedom.common.number.impl;

import io.ffreedom.common.number.api.Num;
import io.ffreedom.common.utils.DoubleUtil;

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

	public DoubleNum copy() {
		return new DoubleNum(value, isPrecision8);
	}

	@Override
	public int compareTo(DoubleNum o) {
		return 0;
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
	public DoubleNum sqrt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleNum sqrt(int precision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double abs() {
		return Math.abs(value);

	}

	@Override
	public boolean isZero() {
		return value == Num.DoubleZero;
	}

	@Override
	public boolean isPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPositiveOrZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNegativeOrZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEqual(Num<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGreaterThan(Num<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGreaterThanOrEqual(Num<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLessThan(Num<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLessThanOrEqual(Num<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DoubleNum min(Num<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleNum max(Num<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public int intValue() {
		return Double.valueOf(value).intValue();
	}

	@Override
	public long longValue() {
		return Double.valueOf(value).longValue();
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

}
