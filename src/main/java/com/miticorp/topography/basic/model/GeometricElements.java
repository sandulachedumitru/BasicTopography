package com.miticorp.topography.basic.model;

public abstract class GeometricElements {
	// Fields exposed by Shape by getters and setters
//	protected double scaleFactor = 1D; // by default

	protected Scale scale;
	protected String name;
	
	// Setters and Getters
	public Scale getScale() {
		return scale;
	}
	public void setScale(Scale scale) {
		this.scale = scale;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract String toString();
}
