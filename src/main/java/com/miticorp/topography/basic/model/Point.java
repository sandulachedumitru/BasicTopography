package com.miticorp.topography.basic.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Point<T> extends Shape {
	private Coordinates<T> coord;
	private double scaleFactor = 1D;

	// Constructors
	public Point() {}
	public Point(Coordinates<T> coord, double scaleFactor) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
	}
	public Point(Coordinates<T> coord) {
		super();
		this.coord = coord;
	}
	
	// Getters and Setters
	public Coordinates<T> getCoord() {
		return coord;
	}
	public void setCoord(Coordinates<T> coord) {
		this.coord = coord;
	}
	public double getScaleFactor() {
		return scaleFactor;
	}
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
}
