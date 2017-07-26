package com.miticorp.topography.basic.model;

public class Point extends Shape {
	private Coordinates coord;
	private double scaleFactor = 1D;

	// Constructors
	public Point() {}
	public Point(Coordinates coord, double scaleFactor) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
	}
	public Point(Coordinates coord) {
		super();
		this.coord = coord;
	}

	// Getters and Setters
	public synchronized Coordinates getCoord() {
		return coord;
	}
	public synchronized void setCoord(Coordinates coord) {
		this.coord = coord;
	}
	
	public synchronized double getScaleFactor() {
		return scaleFactor;
	}
	public synchronized void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
}
