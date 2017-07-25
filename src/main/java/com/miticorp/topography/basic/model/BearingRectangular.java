package com.miticorp.topography.basic.model;

public class BearingRectangular {
	private double value;
	private Point from;
	private Point to;
	
	// Constructors
//	public BearingRectangular() { super(); }
	public BearingRectangular(Point from, Point to) {
		super();
		this.from = from;
		this.to = to;
	}
	public BearingRectangular(double value, Point from, Point to) {
		super();
		this.value = value;
		this.from = from;
		this.to = to;
	}
	
	// Getters and Setters
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Point getFrom() {
		return from;
	}
	public void setFrom(Point from) {
		this.from = from;
	}
	public Point getTo() {
		return to;
	}
	public void setTo(Point to) {
		this.to = to;
	}
	
	// Calculate the bearing
//	private double calculateBearing(Point from, Point to) {
//		
//	}
}
