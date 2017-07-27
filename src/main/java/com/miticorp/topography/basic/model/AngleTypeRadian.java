package com.miticorp.topography.basic.model;

public class AngleTypeRadian extends AngleType {
	private double degree = 2*Math.PI;

	@Override
	Double getMaxNumberOfCircleDegrees() {
		return degree;
	}
}
