package com.miticorp.topography.basic.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GeometricElementsTest {
	private static final double DELTA_TOLERANCE = 0.0001;
	private static final double SCALE_FACTOR_DEFAULT = (new GeometricElements() {
		{ scale = new Scale(); name = ""; }
		@Override public int hashCode() {return 0;}
		@Override public boolean equals(Object obj) {return false;}
		@Override public String  toString() {return "";}
	}).getScale().getScaleFactor();

	@Test
	public void testGetDefaultScaleFactor() {
		// setup
		GeometricElements geoElem = new GeometricElements() {
			{ scale = new Scale(); name = ""; }
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		
		// test
		assertEquals(geoElem.getScale().getScaleFactor(), SCALE_FACTOR_DEFAULT, DELTA_TOLERANCE);
	}

	@Test
	public void testSetDefaultScaleFactor() {
		// setup
		GeometricElements geoElem1 = new GeometricElements() {
			{ scale = new Scale(); name = ""; }
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		GeometricElements geoElem2 = new GeometricElements() {
			{ scale = new Scale(); name = ""; }
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		double factor = SCALE_FACTOR_DEFAULT * (new Random()).nextDouble();
		geoElem2.getScale().setScaleFactor(factor);
		
		// test
		assertEquals(geoElem1.getScale().getScaleFactor(), SCALE_FACTOR_DEFAULT, DELTA_TOLERANCE);
		assertEquals(geoElem2.getScale().getScaleFactor(), factor, DELTA_TOLERANCE);
	}
	
	@Test
	public void testGetName() {
		// setup
		GeometricElements geoElem = new GeometricElements() {
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		
		// test
		assertEquals(geoElem.getName(), null);
	}

	@Test
	public void testSetName() {
		// setup
		GeometricElements geoElem1 = new GeometricElements() {
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		GeometricElements geoElem2 = new GeometricElements() {
			@Override public int hashCode() {return 0;}
			@Override public boolean equals(Object obj) {return false;}
			@Override public String  toString() {return "";}
		};
		// String name = Utils.getRandString(); geoElem2.setName(name);
		String name = "name"; geoElem2.setName(name);
		
		// test
		assertEquals(geoElem1.getName(), null);
		assertEquals(geoElem2.getName(), name);
	}
}
