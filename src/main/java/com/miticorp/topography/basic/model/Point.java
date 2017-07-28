package com.miticorp.topography.basic.model;

public class Point<T extends Coordinates> extends Shape {
	private T coord;
	private double scaleFactor = 1D;
	private String name;

	// Constructors
	public Point() {}
	public Point(T coord, double scaleFactor, String name) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
		this.name = name;
	}
	public Point(T coord, double scaleFactor) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
	}
	public Point(T coord) {
		super();
		this.coord = coord;
	}

	// Getters and Setters
	public synchronized T getCoord() { return coord; }
	public synchronized void setCoord(T coord) { this.coord = coord; }
	
	public synchronized double getScaleFactor() { return scaleFactor; }
	public synchronized void setScaleFactor(double scaleFactor) { this.scaleFactor = scaleFactor; }
	
	public synchronized String getName() { return name; }
	public synchronized void setName(String name) { this.name = name; }
}
