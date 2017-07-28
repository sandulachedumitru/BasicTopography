package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

public class Distance {
	private Point<?> from;
	private Point<?> to;
	private Double value;
	
	// Constructors
	public Distance(Point<?> from, Point<?> to) {
		super();
		this.from = from;
		this.to = to;
		if (from != null && to != null) {
			CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, new AngleTypeRadian()); 
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
	public synchronized Point<?> getFrom() {
		return from;
	}
	public synchronized void setFrom(Point<?> from) {
		this.from = from;
	}
	public synchronized Point<?> getTo() {
		return to;
	}
	public synchronized void setTo(Point<?> to) {
		this.to = to;
	}
	public synchronized Double getValue() {
		return value;
	}
	public synchronized void setValue(Double value) {
		this.value = value;
	}
}
