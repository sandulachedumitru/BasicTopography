package com.miticorp.topography.basic.model;

public class AngleTypeHexadecimal extends AngleType {
	private final double degree = 360;

	@Override
	public Double getMaxNumberOfCircleDegrees() {
		return degree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AngleTypeHexadecimal other = (AngleTypeHexadecimal) obj;
		return Double.doubleToLongBits(degree) == Double.doubleToLongBits(other.degree);
	}

	@Override
	public String toString() {
		return "AngleTypeHexadecimal{" +
				"degree=" + degree +
				'}';
	}
}
