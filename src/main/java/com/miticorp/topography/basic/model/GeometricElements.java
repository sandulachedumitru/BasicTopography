package com.miticorp.topography.basic.model;

public abstract class GeometricElements {
	// Fields exposed by Shape by getters and setters
	protected double scaleFactor = 1D; // by default
	protected String name;
	
	// Setters and Getters
	// TODO de facut test in clasele de test care mostenesc metodele
	public double getScaleFactor() {
		return scaleFactor;
	}
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
