package com.miticorp.topography.basic.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class CoordinatesRectangular extends CoordinatesOrthogonal {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangular.class);

	private Double north, east, height;

	// https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null
	// google search: autowired instance is null

	@Autowired
	private DistanceType distanceTypeImperialInch;

	@Autowired
	private AngleType angleTypeRadian;

	// constructors
	public CoordinatesRectangular() {
		LOG.info("Create a rectangular coordinate with values: default North[{}], default East[{}], default Height[{}], default DistanceType[{}], defaultAngleType[{}]", north, east, height, distanceType, angleType);
	}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = distanceTypeImperialInch;
		this.angleType = angleTypeRadian;

		LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}], default DistanceType[{}], defaultAngleType[{}]", north, east, height, distanceType, angleType);
	}

	public CoordinatesRectangular(double northValue, double estValue, double heightValue, DistanceType distanceType, AngleType angleType) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = distanceType;
		this.angleType = angleType;
		LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}], DistanceType[{}], AngleType[{}]", north, east, height, distanceType, angleType);
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


//	public double getNorth() {
//		return north;
//	}
//
//	public void setNorth(double north) {
//		this.north = north;
//	}
//
//	public double getEast() {
//		return east;
//	}
//
//	public void setEast(double east) {
//		this.east = east;
//	}
//
//	public double getHeight() {
//		return height;
//	}
//
//	public void setHeight(double height) {
//		this.height = height;
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		CoordinatesRectangular that = (CoordinatesRectangular) o;
//
//		if (Double.compare(that.north, north) != 0) return false;
//		if (Double.compare(that.east, east) != 0) return false;
//		if (Double.compare(that.height, height) != 0) return false;
//		if (distanceTypeMetricMeter != null ? !distanceTypeMetricMeter.equals(that.distanceTypeMetricMeter) : that.distanceTypeMetricMeter != null) return false;
//		return angleTypeCentesimal != null ? angleTypeCentesimal.equals(that.angleTypeCentesimal) : that.angleTypeCentesimal == null;
//	}
//
//	@Override
//	public int hashCode() {
//		int result;
//		long temp;
//		temp = Double.doubleToLongBits(north);
//		result = (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(east);
//		result = 31 * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(height);
//		result = 31 * result + (int) (temp ^ (temp >>> 32));
//		result = 31 * result + (distanceTypeMetricMeter != null ? distanceTypeMetricMeter.hashCode() : 0);
//		result = 31 * result + (angleTypeCentesimal != null ? angleTypeCentesimal.hashCode() : 0);
//		return result;
//	}
//
//	@Override
//	public String toString() {
//		return "CoordinatesRectangular{" +
//				"north=" + north +
//				", east=" + east +
//				", height=" + height +
//				", distanceType=" + distanceTypeMetricMeter +
//				", angleType=" + angleTypeCentesimal +
//				'}';
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatesRectangular that = (CoordinatesRectangular) o;
		return Objects.equals(north, that.north) &&
				Objects.equals(east, that.east) &&
				Objects.equals(height, that.height) &&
				Objects.equals(distanceTypeImperialInch, that.distanceTypeImperialInch);
	}

	@Override
	public int hashCode() {
		return Objects.hash(north, east, height);
	}

	@Override
	public String toString() {
		return "CoordinatesRectangular{" +
				"north=" + north +
				", east=" + east +
				", height=" + height +
				", distanceTypeImperialInch=" + distanceTypeImperialInch +
				", angleTypeRadian=" + angleTypeRadian +
				'}';
	}
}
