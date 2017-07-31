package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

public class Distance<T extends CoordinatesRectangular> {
	private Point<T> from;
	private Point<T> to;
	private Double value;
	private DistanceType distanceType; // unitatea de masura a distantelor (m, inch, yard, mile, km, etc)
	
	// Constructors
	public Distance(Point<T> from, Point<T> to) {
		super();
		this.from = from;
		this.to = to;
		if (from != null && to != null) {
			CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, new AngleTypeRadian()); 
			this.value = coordinatesPolar.getDistance().getValue();
			setDistanceValue(from, to);
		}
	}
	public Distance(Double value) {
		super();
		this.value = value;
	}
	public Distance() {
		super();
	}

	// Getters and Setters
	public synchronized Point<T> getFrom() {
		return from;
	}
	public synchronized void setFrom(Point<T> from) {
		this.from = from;
	}
	public synchronized Point<T> getTo() {
		return to;
	}
	public synchronized void setTo(Point<T> to) {
		this.to = to;
	}
	public synchronized Double getValue() {
		return value;
	}
	public synchronized void setValue(Double value) {
		this.value = value;
	}
	public synchronized DistanceType getDistanceType() {
		return distanceType;
	}
	public synchronized void setDistanceType(DistanceType distanceType) {
		this.distanceType = distanceType;
	}
	
	private void setDistanceValue(Point<T> from, Point<T> to) {
		CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, new AngleTypeRadian()); 
		this.value = coordinatesPolar.getDistance().getValue();
	}
}
