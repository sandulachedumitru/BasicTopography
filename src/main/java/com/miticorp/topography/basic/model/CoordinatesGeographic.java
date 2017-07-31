package com.miticorp.topography.basic.model;

public class CoordinatesGeographic extends CoordinatesOrthogonal {
	private double latitude, longitude, height;

	// Constructors
	public CoordinatesGeographic() {}
	public CoordinatesGeographic(double latitude,double longitude,double height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
	}

	// Getters and Setters
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
}
