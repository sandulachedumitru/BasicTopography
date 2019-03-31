package com.miticorp.topography.basic.model;

public abstract class Coordinates {
	protected DistanceType distanceType; // unit/measure system in which coordinates are expressed
	protected AngleType angleType; // angular system in witch coordinate are expressed

	public DistanceType getDistanceType() {
		return distanceType;
	}
	public void setDistanceType(DistanceType distanceType) {
		this.distanceType = distanceType;
	}
	public AngleType getAngleType() {
		return angleType;
	}
	public void setAngleType(AngleType angleType) {
		this.angleType = angleType;
	}

	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract String toString();
}