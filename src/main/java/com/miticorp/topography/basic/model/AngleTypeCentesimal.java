package com.miticorp.topography.basic.model;

public class AngleTypeCentesimal extends AngleType {
	private double degree = 400;

	@Override
	Double getMaxNumberOfCircleDegrees() {
		return degree;
	}
}
