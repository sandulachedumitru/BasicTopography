package com.miticorp.topography.basic.model;

public abstract class AngleType {
	abstract Double getMaxNumberOfCircleDegrees();
	
	<T extends AngleType> Double getAngleChangeSystemFactor(T t) {
		double factor = t.getMaxNumberOfCircleDegrees() / getMaxNumberOfCircleDegrees(); 
		return factor;
	}
}
