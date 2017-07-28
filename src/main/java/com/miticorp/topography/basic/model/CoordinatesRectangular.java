package com.miticorp.topography.basic.model;

// TODO de grupat sistemele ortogonale sub o clasa separata
public class CoordinatesRectangular extends Coordinates {
	private double north, east, height;
	
	// constructors
	public CoordinatesRectangular() {}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
	}
	
	// setters and getters
	public double getNorth() {
		return north;
	}

	public void setNorth(double north) {
		this.north = north;
	}

	public double getEast() {
		return east;
	}

	public void setEast(double east) {
		this.east = east;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
