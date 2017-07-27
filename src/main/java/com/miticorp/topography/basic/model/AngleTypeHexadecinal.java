package com.miticorp.topography.basic.model;

public class AngleTypeHexadecinal extends AngleType {
	private double degree = 360;

	@Override
	Double getMaxNumberOfCircleDegrees() {
		return degree;
	}
}
