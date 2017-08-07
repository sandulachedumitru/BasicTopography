package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

// TODO de terminat clasele de test
public class DistanceTest {

	@Test
	public void testDistancePointOfTPointOfTDistanceType() {
		// setup
		double N1, E1, H1, N2, E2, H2;
		Random random = new Random();
		N1 = random.nextDouble(); N1 *= 200; N1 -= 100;
		E1 = random.nextDouble(); E1 *= 200; E1 -= 100;
		H1 = random.nextDouble(); H1 *= 200; H1 -= 100;
		System.out.println(N1 + " / " + E1 + " / " + H1);
		N2 = random.nextDouble(); N2 *= 200; N2 -= 100;
		E2 = random.nextDouble(); E2 *= 200; E2 -= 100;
		H2 = random.nextDouble(); H2 *= 200; H2 -= 100;
		System.out.println(N2 + " / " + E2 + " / " + H2);

		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		Point<CoordinatesRectangular> from1 = new Point<>((CoordinatesRectangular) coord1);
		Point<CoordinatesRectangular> to1 = new Point<>((CoordinatesRectangular) coord2);
		DistanceType type1 = new DistanceTypeImperialInch();
		
		Point<CoordinatesRectangular> from2 = new Point<>(null);
		Point<CoordinatesRectangular> to2 = new Point<>(null);
		DistanceType type2 = new DistanceTypeMetricMeter();
		
		Distance<CoordinatesRectangular> distance1 = new Distance<>(from1, to1, type1);
		Distance<CoordinatesRectangular> distance2 = new Distance<>(from2, to2, type2);
		Distance<CoordinatesRectangular> distance3 = new Distance<>(null, null, null);
		
		// test
		assertTrue(distance1.getDistanceType() == type1 && distance1.getFrom() == from1 && distance1.getTo() == to1);
		assertNotNull(distance1.getValue());
		System.out.println(distance1.getValue());
		
		assertTrue(distance2.getDistanceType() == type2 && distance2.getFrom() == from2 && distance2.getTo() == to2);
		assertNull(distance2.getValue());
		//System.out.println(distance2.getValue());

		assertTrue(distance3.getDistanceType() == null && distance3.getFrom() == null && distance3.getTo() == null);
		assertNull(distance3.getValue());
		//System.out.println(distance3.getValue());
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
