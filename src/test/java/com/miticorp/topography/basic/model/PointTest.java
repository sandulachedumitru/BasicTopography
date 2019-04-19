package com.miticorp.topography.basic.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PointTest {
	private static final String DEFAULT_NAME = "";
	private static final double DELTA_TOLERANCE = 0.0001;
	private static final double SCALE_FACTOR_DEFAULT = (new GeometricElements() {
		{ scale = new Scale(); name = ""; }
		@Override public int hashCode() {return 0;}
		@Override public boolean equals(Object obj) {return false;}
		@Override public String  toString() {return "";}
	}).getScale().getScaleFactor();

	/**
	 * Test constructor Point()
	 */
	@Test
	public void testPoint() {
		// setup
		Point point = new Point();
		
		// test
		assertNull(point.getCoord());
		assertNotNull(point.getScale());
		assertEquals(SCALE_FACTOR_DEFAULT, point.getScale().getScaleFactor(), DELTA_TOLERANCE);
		assertNotNull(point.getName());
		assertEquals(point.getName(), DEFAULT_NAME);
	}

	/**
	 * Test constructor Point(Coordinates coord, double scaleFactor, String name)
	 */
	@Test
	public void testPointTDoubleString() {
		// setup
		Coordinates coord = new CoordinatesRectangular();
		Double scaleFactor = (new Random()).nextDouble() * 100;
		Scale scale = new Scale(scaleFactor);
		String name = "first point";
		Point point = new Point(coord, scale, name);
		
		// test
		assertEquals(coord.getClass(), point.getCoord().getClass());
		assertEquals(scaleFactor, point.getScale().getScaleFactor(), DELTA_TOLERANCE);
		assertEquals(name, point.getName());
	}

	/**
	 * Test method getCoord()
	 */
	@Test
	public void testGetCoord() {
		// setup
		Coordinates coord = new CoordinatesRectangular();
		Point point = new Point(coord, null, null);
		
		// test
		assertEquals(coord.getClass(), point.getCoord().getClass());
		assertEquals(coord, point.getCoord());
	}

	/**
	 * Test method setCoord(Coordinates coord)
	 */
	@Test
	public void testSetCoord() {
		// setup
		Coordinates coord = new CoordinatesRectangular();
		Point point = new Point();
		point.setCoord(coord);
		
		// test
		assertEquals(coord.getClass(), point.getCoord().getClass());
		assertEquals(coord, point.getCoord());
	}

	/**
	 * Test method getScaleFactor()
	 */
	@Test
	public void testGetScaleFactor() {
		// setup
		Coordinates coord = new CoordinatesRectangular();
		Double scaleFactor = SCALE_FACTOR_DEFAULT;
		Scale scale = new Scale(scaleFactor);
		Point point = new Point(coord, scale, null);
		
		// test
		assertEquals(scaleFactor, point.getScale().getScaleFactor(), DELTA_TOLERANCE);
	}

	/**
	 * Test method setScaleFactor(double scaleFactor)
	 */
	@Test
	public void testSetScaleFactor() {
		// setup
		Double scaleFactor = (new Random()).nextDouble() * 100;
		Scale scale = new Scale(scaleFactor);
		Point point = new Point();
		point.setScale(scale);
		
		// test
		assertEquals(scaleFactor, point.getScale().getScaleFactor(), DELTA_TOLERANCE);
	}

	/**
	 * Test method getName()
	 */
	@Test
	public void testGetName() {
		// setup
		String name = "control point";
		Point point = new Point(null, null, name);
		
		// test
		assertEquals(name, point.getName());
	}

	/**
	 * Test method setName(String name)
	 */
	@Test
	public void testSetName() {
		// setup
		String name1 = "first", name2 = "second";
		Point point = new Point(null, null, name1);
		point.setName(name2);
		
		// test
		assertEquals(name2, point.getName());
	}

	/**
	 * Test method hashCode()
	 */
	@Test
	public void testHashCode() {
		// setup
		double N1, E1, H1, N2, E2, H2;
		Random random = new Random();
		N1 = random.nextDouble(); N1 *= 200; N1 -= 100;
		E1 = random.nextDouble(); E1 *= 200; E1 -= 100;
		H1 = random.nextDouble(); H1 *= 200; H1 -= 100;
		//System.out.println(N1 + " / " + E1 + " / " + H1);
		N2 = random.nextDouble(); N2 *= 200; N2 -= 100;
		E2 = random.nextDouble(); E2 *= 200; E2 -= 100;
		H2 = random.nextDouble(); H2 *= 200; H2 -= 100;
		//System.out.println(N2 + " / " + E2 + " / " + H2);
		
		Double scaleFactor = (new Random()).nextDouble() * 100;
		Scale scale = new Scale(scaleFactor);
		Scale scale2 = new Scale(scale.getScaleFactor() * 2);
		String name1 = "point1", name2 = "point2";
		
		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord3 = new CoordinatesRectangular(N2, E2, H2);
		Coordinates coord4 = new CoordinatesGeographic(N1, E1, H1);
		Coordinates coord5 = new CoordinatesGeographic(N1, E1, H1);
		Coordinates coord6 = new CoordinatesGeographic(N1, E1, H1);
		
		Point point1 = new Point(coord1, scale, name1);
		Point point2 = new Point(coord2, scale, name1);
		Point point3 = new Point(coord3, scale, name1);
		Point point4 = new Point(coord4, scale, name1);
		Point point5 = new Point(coord5, scale, name1);
		Point point6 = new Point(coord6, scale2, name2);
		
		// test
		assertEquals(point1.hashCode(), point1.hashCode());
		assertEquals(point1.hashCode(), point2.hashCode());
		assertNotEquals(point1.hashCode(), point3.hashCode());
		assertEquals(point1.hashCode(), point4.hashCode());
		assertEquals(point4.hashCode(), point5.hashCode());
		assertEquals(point4.hashCode(), point6.hashCode());
	}

	/**
	 * Test method equals(Object obj)
	 */
	@Test
	public void testEqualsObject() {
		// setup
		double N1, E1, H1, N2, E2, H2;
		Random random = new Random();
		N1 = random.nextDouble(); N1 *= 200; N1 -= 100;
		E1 = random.nextDouble(); E1 *= 200; E1 -= 100;
		H1 = random.nextDouble(); H1 *= 200; H1 -= 100;
		//System.out.println(N1 + " / " + E1 + " / " + H1);
		N2 = random.nextDouble(); N2 *= 200; N2 -= 100;
		E2 = random.nextDouble(); E2 *= 200; E2 -= 100;
		H2 = random.nextDouble(); H2 *= 200; H2 -= 100;
		//System.out.println(N2 + " / " + E2 + " / " + H2);
		
		Double scaleFactor = (new Random()).nextDouble() * 100;
		Scale scale1 = new Scale(scaleFactor);
		Scale scale2 = new Scale(scale1.getScaleFactor() * 10);
		String name1 = "point1", name2 = "point2";
		
		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord3 = new CoordinatesRectangular(N2, E2, H2);
		Coordinates coord4 = new CoordinatesGeographic(N1, E1, H1);
		Coordinates coord5 = new CoordinatesGeographic(N1, E1, H1);
		Coordinates coord6 = new CoordinatesGeographic(N1, E1, H1);

		Point point1 = new Point(coord1, scale1, name1);
		Point point2 = new Point(coord2, scale1, name1);
		Point point3 = new Point(coord3, scale1, name1);
		Point point4 = new Point(coord4, scale1, name1);
		Point point5 = new Point(coord5, scale1, name1);
		Point point6 = new Point(coord6, scale2, name2);
		Point point7 = new Point(null, scale1, name1);
		Point point8 = null;
		
		// test
		assertTrue(point1.equals(point2));
		assertFalse(point1.equals(point3));
		assertFalse(point1.equals(point4));
		assertTrue(point4.equals(point5));
		assertTrue(point4.equals(point6));
		assertFalse(point1.equals(point7));
		assertFalse(point1.equals(point8));
	}
}
