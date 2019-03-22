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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((east == null) ? 0 : east.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((north == null) ? 0 : north.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinatesRectangular other = (CoordinatesRectangular) obj;
		if (east == null) {
			if (other.east != null)
				return false;
		} else if (!east.equals(other.east))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (north == null) {
			if (other.north != null)
				return false;
		} else if (!north.equals(other.north))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CoordinatesRectangular{" +
				"north=" + north +
				", east=" + east +
				", height=" + height +
				'}';
	}
}
