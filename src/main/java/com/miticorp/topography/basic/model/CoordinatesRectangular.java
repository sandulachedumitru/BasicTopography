package com.miticorp.topography.basic.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CoordinatesRectangular extends CoordinatesOrthogonal {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangular.class);

	// TODO change 'Double' to 'double'. This change have deep impact in code.
	private Double north, east, height;

	// constructors
	public CoordinatesRectangular() {
		LOG.info("Create a rectangular coordinate with values: default North[{}], default East[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", north, east, height, distanceType, angleType);
	}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = new DistanceTypeMetricMeter();
		this.angleType = new AngleTypeCentesimal();

		LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}], default DistanceType[{}], default AngleType[{}]", north, east, height, distanceType, angleType);
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatesRectangular that = (CoordinatesRectangular) o;
		return Objects.equals(north, that.north) &&
				Objects.equals(east, that.east) &&
				Objects.equals(height, that.height);
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
				", distanceType=" + distanceType +
				", angleType=" + angleType +
				'}';
	}
}
