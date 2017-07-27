package com.miticorp.topography.basic.model;

public class AngleTypeHexadecinal extends AngleType {
	private double degree = 360;
	private double rad2centFactor = degree/(2*Math.PI);
	private double cent2radFactor = 2*Math.PI/degree;
	
	@Override
	Double getAngleValueFromRadianByTransformationFactor() {
		return rad2centFactor;
	}
	
	@Override
	Double getRadianFromAngleValueByTransformationFactor() {
		return cent2radFactor;
	}

	@Override
	Double getMaxNumberOfCircleDegrees() {
		return degree;
	}

}
