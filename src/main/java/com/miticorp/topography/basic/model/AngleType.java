package com.miticorp.topography.basic.model;

// TODO insert logs to all classes
public abstract class AngleType {
	public abstract Double getMaxNumberOfCircleDegrees();
	
	public <T extends AngleType> Double getAngleChangeSystemFactor(T t) {
		return t.getMaxNumberOfCircleDegrees() / getMaxNumberOfCircleDegrees();
	}
	
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract String toString();
}
