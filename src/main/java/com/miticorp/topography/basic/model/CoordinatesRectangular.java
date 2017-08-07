package com.miticorp.topography.basic.model;

public class CoordinatesRectangular extends CoordinatesOrthogonal {
	private Double north, east, height;
	
	// constructors
	public CoordinatesRectangular() {}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
	}

	// setters and getters
	public Double getNorth() {
		return north;
	}
	public void setNorth(Double north) {
		this.north = north;
	}
	public Double getEast() {
		return east;
	}
	public void setEast(Double east) {
		this.east = east;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
}
