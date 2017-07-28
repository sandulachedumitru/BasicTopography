package com.miticorp.topography.basic.model;

public class AngleTypeHexadecinal extends AngleType {
	private double degree = 360;

	@Override
	public Double getMaxNumberOfCircleDegrees() {
		return degree;
	}
}
