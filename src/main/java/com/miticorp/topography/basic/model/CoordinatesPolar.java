package com.miticorp.topography.basic.model;

public class CoordinatesPolar extends Coordinates {
	private Angle angle;
	private Distance<?> distance;
	
	// Constructors
	public CoordinatesPolar() { super(); }
	public CoordinatesPolar(Angle angle, Distance<?> distance) {
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
	public synchronized Distance<?> getDistance() {
		return distance;
	}
	public synchronized void setDistance(Distance<?> distance) {
		this.distance = distance;
	}
}
