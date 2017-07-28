package com.miticorp.topography.basic.model;

public class AngleTypeCentesimal extends AngleType {
	private double degree = 400;

	@Override
	public Double getMaxNumberOfCircleDegrees() {
		return degree;
	}
}
