package com.miticorp.topography.basic.model;

import java.util.Collections;
import java.util.TreeMap;

import com.miticorp.topography.basic.model.Coordinates.DimensionsGeographicLatLongH;

public class CoordinatesGeographic extends Coordinates<DimensionsGeographicLatLongH> {
	private double latitude, longitude, height;

	// Constructors
	public CoordinatesGeographic() {}
	public CoordinatesGeographic(double latitude,double longitude,double height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		
		// TODO de analizat daca se preteaza mai bine varianta cu Map pentru coordonate
		dimensions = Collections.synchronizedSortedMap(new TreeMap<DimensionsGeographicLatLongH, Double>());
		dimensions.put(DimensionsGeographicLatLongH.LATITUDE, latitude);
		dimensions.put(DimensionsGeographicLatLongH.LONGITUDE, longitude);
		dimensions.put(DimensionsGeographicLatLongH.HEIGHT, height);
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
