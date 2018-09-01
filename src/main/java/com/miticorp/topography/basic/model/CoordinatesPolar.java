package com.miticorp.topography.basic.model;

public class CoordinatesPolar extends Coordinates {
	private Angle angle;
	private Distance distance;
	
	// Constructors
	public CoordinatesPolar() { super(); }
	public CoordinatesPolar(Angle angle, Distance distance) {
		super();
		this.angle = angle;
		this.distance = distance;
	}

	// Getters and Setters
	public synchronized Angle getAngle() {
		return angle;
	}
	public synchronized void setAngle(Angle angle) {
		this.angle = angle;
	}
	public synchronized Distance getDistance() {
		return distance;
	}
	public synchronized void setDistance(Distance distance) {
		this.distance = distance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angle == null) ? 0 : angle.hashCode());
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinatesPolar other = (CoordinatesPolar) obj;
		if (angle == null) {
			if (other.angle != null)
				return false;
		} else if (!angle.equals(other.angle))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		return true;
	}
}
