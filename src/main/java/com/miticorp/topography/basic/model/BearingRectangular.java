package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

// TODO de schimbat numele clasei in AngleBearingRectangula
public class BearingRectangular<T extends CoordinatesRectangular> extends Angle {
	private Point<T> from;
	private Point<T> to;
	
	// Constructors
	public BearingRectangular(Point<T> from, Point<T> to, AngleType angleType) {
		super();
		this.from = from;
		this.to = to;
		setAngleType(angleType);
		if (from != null && to != null) setAngleValue(from, to, getAngleType());
		else setValue(null);
	}
	public BearingRectangular(Double value, AngleType angleType) {
		super(value, angleType);
	}
	
	// Getters and Setters
	public Point<T> getFrom() {
		return from;
	}
	public void setFrom(Point<T> from) {
		this.from = from;
		setAngleValue(from, to, getAngleType());
	}
	
	public Point<T> getTo() {
		return to;
	}
	public void setTo(Point<T> to) {
		this.to = to;
		setAngleValue(from, to, getAngleType());
	}
	
	private void setAngleValue(Point<T> from, Point<T> to, AngleType angleType) {
		CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, getAngleType());
		setValue(coordinatesPolar.getAngle().getValue());
	}
	
}
