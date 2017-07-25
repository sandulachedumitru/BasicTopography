package com.miticorp.topography.basic.model;

import java.util.Collections;
import java.util.TreeMap;

import com.miticorp.topography.basic.model.Coordinates.DimensionsRectangularNEH;

public class CoordinatesRectangular extends Coordinates<DimensionsRectangularNEH> {
	private double northValue;
	private double estValue;
	private double heightValue;
	
	// constructors
	public CoordinatesRectangular() {}
	
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this.northValue = northValue;
		this.estValue = estValue;
		this.heightValue = heightValue;
		
		// TODO de analizat daca se preteaza mai bine varianta cu Map pentru coordonate
		dimensions = Collections.synchronizedSortedMap(new TreeMap<DimensionsRectangularNEH, Double>());
		dimensions.put(DimensionsRectangularNEH.NORTH, northValue);
		dimensions.put(DimensionsRectangularNEH.EST, estValue);
		dimensions.put(DimensionsRectangularNEH.HEIGHT, heightValue);
	}
	
	// set and get for NORTH
	public synchronized void NORTH (double northValue) {
		this.northValue = northValue;
	}
	public synchronized double NORTH() {
		return northValue;
	}
	
	// set and get for EST
	public synchronized void EST(double estValue) {
		this.estValue = estValue;
	}
	public synchronized double EST() {
		return estValue;
	}
	
	// set and get for HEIGHT
	public synchronized void HEIGHT(double heightValue) {
		this.heightValue = heightValue;
	}
	public synchronized double HEIGHT() {
		return heightValue;
	}
	

			
}
