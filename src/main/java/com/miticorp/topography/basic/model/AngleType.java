package com.miticorp.topography.basic.model;

public abstract class AngleType {
	public abstract Double getMaxNumberOfCircleDegrees();
	
	public <T extends AngleType> Double getAngleChangeSystemFactor(T t) {
		double factor = t.getMaxNumberOfCircleDegrees() / getMaxNumberOfCircleDegrees(); 
		return factor;
	}
	
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
