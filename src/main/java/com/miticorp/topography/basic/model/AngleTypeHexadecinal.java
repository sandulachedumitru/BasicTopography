package com.miticorp.topography.basic.model;

public class AngleTypeHexadecinal extends AngleType {
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
		AngleTypeHexadecinal other = (AngleTypeHexadecinal) obj;
		if (Double.doubleToLongBits(degree) != Double.doubleToLongBits(other.degree))
			return false;
		return true;
	}
}
