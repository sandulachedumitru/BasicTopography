package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.factory.AngleTypeCentesimalFactory;
import com.miticorp.topography.basic.factory.DistanceTypeMetricMeterFactory;

import java.util.Objects;


public class CoordinatesGeographic extends CoordinatesOrthogonal {
	private double latitude, longitude, height;

	// Constructors
	public CoordinatesGeographic() {}
	public CoordinatesGeographic(double latitude,double longitude,double height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.distanceType = DistanceTypeMetricMeterFactory.getInstance();
		this.angleType = AngleTypeCentesimalFactory.getInstance();
	}
	public CoordinatesGeographic(double latitude,double longitude,double height, DistanceType distanceType, AngleType angleType) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.distanceType = distanceType;
		this.angleType = angleType;
	}

	// Getters and Setters
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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
		CoordinatesGeographic that = (CoordinatesGeographic) o;
		return Double.compare(that.latitude, latitude) == 0 &&
				Double.compare(that.longitude, longitude) == 0 &&
				Double.compare(that.height, height) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude, height);
	}

	@Override
	public String toString() {
		return "CoordinatesGeographic{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", height=" + height +
				", distanceType=" + distanceType +
				", angleType=" + angleType +
				'}';
	}
}
