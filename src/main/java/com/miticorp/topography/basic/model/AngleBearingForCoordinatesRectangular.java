package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

public class AngleBearingForCoordinatesRectangular extends Angle {
	private Point from;
	private Point to;
	
	// Constructors
	public AngleBearingForCoordinatesRectangular(Point from, Point to, AngleType angleType) {
		this.from = from;
		this.to = to;
		setAngleType(angleType);
		if (from != null && to != null) setAngleValue(from, to, getAngleType());
		else setValue(null);
	}
	public AngleBearingForCoordinatesRectangular(Double value, AngleType angleType) {
		setValue(value);
		setAngleType(angleType);
	}
	
	// Getters and Setters
	public Point getFrom() {
		return from;
	}
	public void setFrom(Point from) {
		this.from = from;
		setAngleValue(from, to, getAngleType());
	}
	
	public Point getTo() {
		return to;
	}
	public void setTo(Point to) {
		this.to = to;
		setAngleValue(from, to, getAngleType());
	}
	
	private void setAngleValue(Point from, Point to, AngleType angleType) {
		CoordinatesPolar coordinatesPolar = Utils.calculatesPolarFromRectangularCoordinates(from, to, getAngleType());
		setValue(coordinatesPolar.getAngle().getValue());
	}
}
