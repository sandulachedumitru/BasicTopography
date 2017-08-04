package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO de terminat clasele de test
public class DistanceTest {

	// TODO nu e terminata
	@Test
	public void testDistancePointOfTPointOfTDistanceType() {
		// setup
		Coordinates coord1 = new CoordinatesRectangular(100, 100, 100);
		Coordinates coord2 = new CoordinatesRectangular(200, 200, 200);
		Point<CoordinatesRectangular> from = new Point<>((CoordinatesRectangular) coord1);
		Point<CoordinatesRectangular> to = new Point<>((CoordinatesRectangular) coord2);
		DistanceType type = new DistanceTypeImperialInch();
		
		Distance<CoordinatesRectangular> distance = new Distance<>(from, to, type);

		
		// test
		assertTrue(distance.getDistanceType() == type && distance.getFrom() == from && distance.getTo() == to);
		assertNotNull(distance.getValue());
		System.out.println(distance.getValue());
		distance.setValue(null);
		assertNull(distance.getValue());
	}

	@Test
	public void testDistancePointOfTPointOfT() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDistanceDoubleDistanceType() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDistanceDouble() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDistance() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTransformDistanceFromSystemToSystemDistanceTypeDistanceTypeDouble() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTransformDistanceFromSystemToSystemDistanceOfQDistanceOfQDouble() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTransformDistanceFromCurrentToAnotherUnitSystem() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTransformDistanceFromAnotherToCurrentUnitSystem() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFrom() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetFrom() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetTo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetTo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetValue() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetValue() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDistanceType() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetDistanceType() {
		//fail("Not yet implemented");
	}

}
