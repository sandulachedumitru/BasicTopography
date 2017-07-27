package com.miticorp.topography.basic.model;

public abstract class AngleType {
	abstract Double getAngleValueFromRadianByTransformationFactor();
	abstract Double getRadianFromAngleValueByTransformationFactor();
	abstract Double getMaxNumberOfCircleDegrees();
}
