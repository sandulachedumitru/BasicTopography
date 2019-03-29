package com.miticorp.topography.basic.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesRectangular extends CoordinatesOrthogonal {
	private double north, east, height;

	// https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null
	// google search: autowired instance is null

	@Autowired
	private DistanceType distanceTypeMetricMeter;

	@Autowired
	private AngleType angleTypeCentesimal;
	
	// constructors
	public CoordinatesRectangular() {}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = distanceTypeMetricMeter;
		this.angleType = angleTypeCentesimal;
	}

	public CoordinatesRectangular(double northValue, double estValue, double heightValue, DistanceType distanceType, AngleType angleType) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = distanceType;
		this.angleType = angleType;
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
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((east == null) ? 0 : east.hashCode());
//		result = prime * result + ((height == null) ? 0 : height.hashCode());
//		result = prime * result + ((north == null) ? 0 : north.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CoordinatesRectangular other = (CoordinatesRectangular) obj;
//		if (east == null) {
//			if (other.east != null)
//				return false;
//		} else if (!east.equals(other.east))
//			return false;
//		if (height == null) {
//			if (other.height != null)
//				return false;
//		} else if (!height.equals(other.height))
//			return false;
//		if (north == null) {
//			if (other.north != null)
//				return false;
//		} else if (!north.equals(other.north))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "CoordinatesRectangular{" +
//				"north=" + north +
//				", east=" + east +
//				", height=" + height +
//				'}';
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CoordinatesRectangular that = (CoordinatesRectangular) o;

		if (Double.compare(that.north, north) != 0) return false;
		if (Double.compare(that.east, east) != 0) return false;
		if (Double.compare(that.height, height) != 0) return false;
		if (distanceTypeMetricMeter != null ? !distanceTypeMetricMeter.equals(that.distanceTypeMetricMeter) : that.distanceTypeMetricMeter != null)
			return false;
		return angleTypeCentesimal != null ? angleTypeCentesimal.equals(that.angleTypeCentesimal) : that.angleTypeCentesimal == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(north);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(east);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (distanceTypeMetricMeter != null ? distanceTypeMetricMeter.hashCode() : 0);
		result = 31 * result + (angleTypeCentesimal != null ? angleTypeCentesimal.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CoordinatesRectangular{" +
				"north=" + north +
				", east=" + east +
				", height=" + height +
				", distanceTypeMetricMeter=" + distanceTypeMetricMeter +
				", angleTypeCentesimal=" + angleTypeCentesimal +
				'}';
	}
}
