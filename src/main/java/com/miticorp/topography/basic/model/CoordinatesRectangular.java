package com.miticorp.topography.basic.model;

import java.util.Collections;
import java.util.TreeMap;

import com.miticorp.topography.basic.model.Coordinates.DimensionsRectangularNEH;

public class CoordinatesRectangular extends Coordinates<DimensionsRectangularNEH> {
	private double north, est, height;
	
	// constructors
	public CoordinatesRectangular() {}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.north = northValue;
		this.est = estValue;
		this.height = heightValue;
		
		// TODO de analizat daca se preteaza mai bine varianta cu Map pentru coordonate
		dimensions = Collections.synchronizedSortedMap(new TreeMap<DimensionsRectangularNEH, Double>());
		dimensions.put(DimensionsRectangularNEH.NORTH, northValue);
		dimensions.put(DimensionsRectangularNEH.EST, estValue);
		dimensions.put(DimensionsRectangularNEH.HEIGHT, heightValue);
	}
	
	// setters and getters
	public double getNorth() {
		return north;
	}

	public void setNorth(double north) {
		this.north = north;
	}

	public double getEst() {
		return est;
	}

	public void setEst(double est) {
		this.est = est;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
