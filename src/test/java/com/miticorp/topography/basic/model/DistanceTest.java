package com.miticorp.topography.basic.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DistanceTest {
	private static final double DELTA_TOLERANCE = 0.0001;
	private static final double SCALE_FACTOR_DEFAULT = (new GeometricElements() {
		@Override public int hashCode() {return 0;}
		@Override public boolean equals(Object obj) {return false;}
	}).getScaleFactor();
	
	/**
	 * Test constructor Distance(Point<T> from, Point<T> to, DistanceType distanceType, double scaleFactor, String name)
	 */
	@Test
	public void testDistancePointOfTPointOfTDistanceTypeDoubleString() {
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

		double scaleFactor = random.nextDouble() * 100;
		
		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		
		Point<Coordinates> from1 = new Point<>(coord1);
		Point<Coordinates> to1 = new Point<>(coord2);
		DistanceType type1 = new DistanceTypeImperialInch();
		String name1 = "name1";
		
		Point<Coordinates> from2 = new Point<>(null);
		Point<Coordinates> to2 = new Point<>(null);
		DistanceType type2 = new DistanceTypeMetricMeter();
		String name2 = "name2";
		
		Point<Coordinates> from3 = null;
		Point<Coordinates> to3 = null;
		DistanceType type3 = null;
		String name3 = null;
		
		Distance distance1 = new Distance(from1, to1, type1, scaleFactor, name1);
		Distance distance2 = new Distance(from2, to2, type2, scaleFactor, name2);
		Distance distance3 = new Distance(from3, to3, type3, scaleFactor, name3);
		
		// test
		assertTrue(distance1.getFrom().equals(from1) 
				&& distance1.getTo().equals(to1) 
				&& distance1.getDistanceType().equals(type1)
				&& distance1.getScaleFactor() == scaleFactor 
				&& distance1.getName().equals(name1));
		assertNotNull(distance1.getValue());
		
		assertTrue(distance2.getFrom().equals(from2) 
				&& distance2.getTo().equals(to2) 
				&& distance2.getDistanceType().equals(type2)
				&& distance2.getScaleFactor() == scaleFactor 
				&& distance2.getName().equals(name2));
		assertNull(distance2.getValue());
		
		assertTrue(distance3.getFrom() == null
				&& distance3.getTo() == null
				&& distance3.getDistanceType() == null
				&& distance3.getScaleFactor() == scaleFactor 
				&& distance3.getName() == null);
		assertNull(distance3.getValue());
	}

	/**
	 * Test constructor Distance(Point<T> from, Point<T> to, DistanceType distanceType)
	 */
	@Test
	public void testDistancePointOfTPointOfTDistanceType() {
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

		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		
		Point<Coordinates> from1 = new Point<>(coord1);
		Point<Coordinates> to1 = new Point<>(coord2);
		DistanceType type1 = new DistanceTypeImperialInch();
		
		Point<Coordinates> from2 = new Point<>(null);
		Point<Coordinates> to2 = new Point<>(null);
		DistanceType type2 = new DistanceTypeMetricMeter();
		
		Distance distance1 = new Distance(from1, to1, type1);
		Distance distance2 = new Distance(from2, to2, type2);
		Distance distance3 = new Distance(null, null, null);
		
		// test
		assertTrue(distance1.getFrom().equals(from1) 
				&& distance1.getTo().equals(to1) 
				&& distance1.getDistanceType().equals(type1)
				&& distance1.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance1.getName() == null);
		assertNotNull(distance1.getValue());
		
		assertTrue(distance2.getFrom().equals(from2) 
				&& distance2.getTo().equals(to2) 
				&& distance2.getDistanceType().equals(type2)
				&& distance2.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance2.getName() == null);
		assertNull(distance2.getValue());
		
		assertTrue(distance3.getFrom() == null
				&& distance3.getTo() == null
				&& distance3.getDistanceType() == null
				&& distance3.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance3.getName() == null);
		assertNull(distance3.getValue());
	}

	/**
	 * Test constructor Distance(Point<T> from, Point<T> to)
	 */
	@Test
	public void testDistancePointOfTPointOfT() {
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

		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		
		Point<Coordinates> from1 = new Point<>(coord1);
		Point<Coordinates> to1 = new Point<>(coord2);
		
		Point<Coordinates> from2 = new Point<>(null);
		Point<Coordinates> to2 = new Point<>(null);
		
		Distance distance1 = new Distance(from1, to1);
		Distance distance2 = new Distance(from2, to2);
		Distance distance3 = new Distance((Point<?>) null, null);
		
		// test
		assertTrue(distance1.getFrom().equals(from1) 
				&& distance1.getTo().equals(to1) 
				&& distance1.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance1.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance1.getName() == null);
		assertNotNull(distance1.getValue());
		
		assertTrue(distance2.getFrom().equals(from2) 
				&& distance2.getTo().equals(to2) 
				&& distance2.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance2.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance2.getName() == null);
		assertNull(distance2.getValue());
		
		assertTrue(distance3.getFrom() == null
				&& distance3.getTo() == null
				&& distance3.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance3.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance3.getName() == null);
		assertNull(distance3.getValue());
	}

	/**
	 * Test constructor Distance(Double value, DistanceType distanceType)
	 */
	@Test
	public void testDistanceDoubleDistanceType() {
		// setup
		Double value = (new Random().nextDouble()) * 100;
		DistanceType distanceType = new DistanceTypeImperialInch();
		
		Distance distance = new Distance(value, distanceType);
		Distance distance2 = new Distance((Double) null, null);
		
		// test
		assertTrue(distance.getFrom() == null
				&& distance.getTo() == null
				&& distance.getDistanceType().equals(distanceType)
				&& distance.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance.getName() == null
				&& distance.getValue().equals(value));
		
		assertTrue(distance2.getFrom() == null
				&& distance2.getTo() == null
				&& distance2.getDistanceType() == null
				&& distance2.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance2.getName() == null
				&& distance2.getValue() == null);
	}

	/**
	 * Test constructor Distance(Double value)
	 */
	@Test
	public void testDistanceDouble() {
		// setup
		Double value1 = (new Random().nextDouble()) * 100;
		Double value2 = null;
		
		Distance distance1 = new Distance(value1);
		Distance distance2 = new Distance(value2);
		
		
		// test
		assertTrue(distance1.getFrom() == null
				&& distance1.getTo() == null
				&& distance1.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance1.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance1.getName() == null
				&& distance1.getValue().equals(value1));
		
		assertTrue(distance2.getFrom() == null
				&& distance2.getTo() == null
				&& distance2.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance2.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance2.getName() == null
				&& distance2.getValue() == null);
	}

	/**
	 * Test constructor Distance()
	 */
	@Test
	public void testDistance() {
		// setup
		Distance distance = new Distance();
		
		// test
		assertTrue(distance.getFrom() == null
				&& distance.getTo() == null
				&& distance.getDistanceType() instanceof DistanceTypeMetricMeter
				&& distance.getScaleFactor() == SCALE_FACTOR_DEFAULT 
				&& distance.getName() == null
				&& distance.getValue() == null);
	}

	/**
	 * Test method for Distance's transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value)
	 */
	@Test
	public void testTransformDistanceFromSystemToSystemDistanceTypeDistanceTypeDouble() {
		// setup
		DistanceTypeMetric fromMetricSystem;
		DistanceTypeImperial fromImperialSystem;
		Double value = 1D, rez;
		
		// test NULL
		rez = Distance.transformDistanceFromSystemToSystem(null, null, value);
		//System.out.println("rez(" + rez + ") / value(" + value + ")");
		assertFalse(value.equals(rez));
		assertNull(rez);

		// test metrics
		//System.out.println("===========METRICS TO ...=============");
		fromMetricSystem = new DistanceTypeMetricKilometer(); combinesWithAll(fromMetricSystem, value);
		//System.out.println("===========METRICS TO ...=============");
		fromMetricSystem = new DistanceTypeMetricMeter(); combinesWithAll(fromMetricSystem, value);
		//System.out.println("===========METRICS TO ...=============");
		fromMetricSystem = new DistanceTypeMetricCentimeter(); combinesWithAll(fromMetricSystem, value);
		//System.out.println("===========METRICS TO ...=============");
		fromMetricSystem = new DistanceTypeMetricMillimeter(); combinesWithAll(fromMetricSystem, value);
		
		// test imperials
		//System.out.println("===========IMPERIALS TO ...=============");
		fromImperialSystem = new DistanceTypeImperialMile(); combinesWithAll(fromImperialSystem, value);
		//System.out.println("===========IMPERIALS TO ...=============");
		fromImperialSystem = new DistanceTypeImperialYard(); combinesWithAll(fromImperialSystem, value);
		//System.out.println("===========IMPERIALS TO ...=============");
		fromImperialSystem = new DistanceTypeImperialInch(); combinesWithAll(fromImperialSystem, value);
	}
	
	/**
	 * Method used by testTransformDistanceFromSystemToSystemDistanceTypeDistanceTypeDouble() method.
	 * This method test all combination of fromSystem with DistanceType's types
	 * @param fromSystem source DistanceType which will be transformed in another DistanceType system
	 * @param value value which will be transformed
	 */
	private static void combinesWithAll(DistanceType fromSystem, Double value) {
		DistanceTypeMetric toMetricSystem;
		DistanceTypeImperial toImperialSystem;
		
		toMetricSystem = new DistanceTypeMetricKilometer();
		transform(fromSystem, toMetricSystem, value);
		
		toMetricSystem = new DistanceTypeMetricMeter();
		transform(fromSystem, toMetricSystem, value);
		
		toMetricSystem = new DistanceTypeMetricCentimeter();
		transform(fromSystem, toMetricSystem, value);
		
		toMetricSystem = new DistanceTypeMetricMillimeter();
		transform(fromSystem, toMetricSystem, value);
		
		toImperialSystem = new DistanceTypeImperialMile();
		transform(fromSystem, toImperialSystem, value);
		
		toImperialSystem = new DistanceTypeImperialYard();
		transform(fromSystem, toImperialSystem, value);
		
		toImperialSystem = new DistanceTypeImperialInch();
		transform(fromSystem, toImperialSystem, value);
	}
	
	/**
	 * Method used by combinesWithAll(DistanceType fromSystem, Double value) and testTransformDistanceFromSystemToSystemDistanceTypeDistanceTypeDouble() methods.
	 * @param from source DistanceType
	 * @param to target DistanceType
	 * @param value value which will be transformed
	 */
	private static void transform(DistanceType from, DistanceType to, Double value) {
		Double newValue = null;
		Double newRez = null;
		
		Double rez = Distance.transformDistanceFromSystemToSystem(from, to, value);
		//System.out.println("rez(" + rez + ") / value(" + value + ")");
		assertNotNull(rez);
		
		if (from instanceof DistanceTypeMetric) {
			if (to instanceof DistanceTypeMetric) {
				newValue = (double) Math.round(value * ((DistanceTypeMetric) from).getConversionToMeter());
				newRez = (double) Math.round(rez * ((DistanceTypeMetric) to).getConversionToMeter());
			}
			else if (to instanceof DistanceTypeImperial) {
				newValue = (double) Math.round(value * ((DistanceTypeMetric) from).getConversionToMeter());
				newRez = (double) Math.round(rez * ((DistanceTypeImperial) to).getConversionToYard() * Distance.YARD_TO_METER);
			}
			else fail("Unknown DistanceType object for 'to'");
		} 
		else if (from instanceof DistanceTypeImperial) {
			if (to instanceof DistanceTypeMetric) {
				newValue = (double) Math.round(value * ((DistanceTypeImperial) from).getConversionToYard());
				newRez = (double) Math.round(rez * ((DistanceTypeMetric) to).getConversionToMeter() * Distance.METER_TO_YARD);
			}
			else if (to instanceof DistanceTypeImperial) {
				newValue = (double) Math.round(value * ((DistanceTypeImperial) from).getConversionToYard());
				newRez = (double) Math.round(rez * ((DistanceTypeImperial) to).getConversionToYard());
			}
			else fail("Unknown DistanceType object for 'to'");
		}
		else fail("Unknown DistanceType object for 'from'");
		
		//System.out.println("newrez(" + newRez + ") / newvalue(" + newValue + ")");
		assertTrue(newValue.equals(newRez));
	}

	/**
	 * Test method for Distance's transformDistanceFromSystemToSystem(Distance from, Distance to)
	 */
	@Test
	public void testTransformDistanceFromSystemToSystemDistanceOfQDistanceOfQ() {
		// setup
		Distance from1, from2;
		Distance to1, to2;
		Double value1 = 1D, value2 = null;
		Double rez1, rez2;
		
		DistanceType distanceType1 = new DistanceTypeMetricMeter();
		DistanceType distanceType2 = new DistanceTypeImperialYard();
		
		from1 = new Distance(value1, distanceType1);
		to1 = new Distance(null, distanceType2);
		from2 = new Distance(value2, distanceType1);
		to2 = new Distance(null, distanceType2);
		
		rez1 = Distance.transformDistanceFromSystemToSystem(from1, to1);
		//System.out.println("rez1(" + rez1 + ") / value1(" + value1 + ")");
		rez2 = Distance.transformDistanceFromSystemToSystem(from2, to2);
		//System.out.println("rez2(" + rez2 + ") / value2(" + value2 + ")");
		
		// test
		assertNotNull(value1);
		assertNotNull(rez1);
		assertEquals(rez1, value1 * Distance.METER_TO_YARD, DELTA_TOLERANCE);
		assertEquals(value1 * Distance.METER_TO_YARD, to1.getValue(), DELTA_TOLERANCE);
		
		assertNull(rez2);
	}

	/**
	 * Test method for Distance's transformDistanceFromCurrentToAnotherUnitSystem(DistanceType another)
	 */
	@Test
	public void testTransformDistanceFromCurrentToAnotherUnitSystem() {
		// setup
		DistanceType anotherType = new DistanceTypeImperialYard();
		DistanceType currentType = new DistanceTypeMetricMeter();
		Double value = 1D;
		Double rez;
		
		Distance distance = new Distance();
		distance.setDistanceType(currentType);
		distance.setValue(value);
		rez = distance.transformDistanceFromCurrentToAnotherUnitSystem(anotherType);
		
		// test
		assertEquals(rez, value * Distance.METER_TO_YARD, DELTA_TOLERANCE);
	}

	/**
	 * Test method for Distance's transformDistanceFromAnotherToCurrentUnitSystem(Distance another)
	 */
	@Test
	public void testTransformDistanceFromAnotherToCurrentUnitSystem() {
		// setup
		DistanceType currentType = new DistanceTypeMetricMeter();
		DistanceType anotherType = new DistanceTypeImperialYard();
		Double anotherValue = 1D;
		Double rez, rez2, rez3;
		
		Distance distanceCurrent = new Distance();
		distanceCurrent.setDistanceType(currentType);
		distanceCurrent.setValue(null);
		
		Distance distanceAnother = new Distance();
		distanceAnother.setDistanceType(anotherType);
		distanceAnother.setValue(anotherValue);
		
		Distance distanceCurrent2 = new Distance();
		distanceCurrent2.setDistanceType(null);
		distanceCurrent2.setValue(null);
		
		Distance distanceAnother2 = new Distance();
		distanceAnother2.setDistanceType(null);
		distanceAnother2.setValue(null);
		
		rez = distanceCurrent.transformDistanceFromAnotherToCurrentUnitSystem(distanceAnother);
		rez2 = distanceCurrent2.transformDistanceFromAnotherToCurrentUnitSystem(distanceAnother);
		rez3 = distanceCurrent.transformDistanceFromAnotherToCurrentUnitSystem(distanceAnother2);
		
		// test
		//System.out.println("rez(" + rez + ") / anotherValue(" + anotherValue + ") / " + "getValue(" + distanceCurrent.getValue() + ")");
		assertEquals(anotherValue, rez * Distance.METER_TO_YARD, DELTA_TOLERANCE);
		assertNull(rez2);
		assertNull(rez3);
	}

	/**
	 * Test method for Distance's getFrom()
	 */
	@Test
	public void testGetFrom() {
		// setup
		DistanceType distanceType = null;
		Coordinates coord = new CoordinatesRectangular();
		
		Point<Coordinates> from1 = new Point<>(coord);
		Point<Coordinates> to1 = new Point<>(coord);
		Point<Coordinates> from2 = null;
		Point<Coordinates> to2 = null;
		
		Distance distance1 = new Distance(from1, to1, distanceType);
		Distance distance2 = new Distance(from2, to2, distanceType);
		
		// test
		assertEquals(distance1.getFrom(), from1);
		assertEquals(distance2.getFrom(), from2);
	}

	/**
	 * Test method for Distance's setFrom(Point<T> from)
	 */
	@Test
	public void testSetFrom() {
		// setup
		DistanceType distanceType = null;
		Coordinates coord = new CoordinatesRectangular();
		Point<Coordinates> from = new Point<>(coord);
		Point<Coordinates> to = new Point<>(coord);
		
		Distance distance = new Distance(null, to, distanceType); distance.setFrom(from);
		
		// test
		assertEquals(distance.getFrom(), from);
	}

	/**
	 * Test method for Distance's getTo()
	 */
	@Test
	public void testGetTo() {
		// setup
		DistanceType distanceType = null;
		Coordinates coord = new CoordinatesRectangular();
		
		Point<Coordinates> from1 = new Point<>(coord);
		Point<Coordinates> to1 = new Point<>(coord);
		Point<Coordinates> from2 = null;
		Point<Coordinates> to2 = null;
		
		Distance distance1 = new Distance(from1, to1, distanceType);
		Distance distance2 = new Distance(from2, to2, distanceType);
		
		// test
		assertEquals(distance1.getTo(), to1);
		assertEquals(distance2.getTo(), to2);
	}

	/**
	 * Test method for Distance's setTo(Point<T> to)
	 */
	@Test
	public void testSetTo() {
		// setup
		DistanceType distanceType = null;
		Coordinates coord = new CoordinatesRectangular();
		Point<Coordinates> from = new Point<>(coord);
		Point<Coordinates> to = new Point<>(coord);
		
		Distance distance = new Distance(from, null, distanceType); distance.setTo(to);
		
		// test
		assertEquals(distance.getTo(), to);
	}

	/**
	 * Test method for Distance's getValue()
	 */
	@Test
	public void testGetValue() {
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

		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		Point<Coordinates> from = new Point<>(coord1);
		Point<Coordinates> to = new Point<>(coord2);
		DistanceType type = new DistanceTypeImperialInch();
		
		Distance distance = new Distance(from, to, type);
		
		// test
		assertNotNull(distance.getValue());
	}

	/**
	 * Test method for Distance's setValue(Double value)
	 */
	@Test
	public void testSetValue() {
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

		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		Point<Coordinates> from = new Point<>(coord1);
		Point<Coordinates> to = new Point<>(coord2);
		DistanceType type = new DistanceTypeImperialInch();
		Double value = random.nextDouble();
		
		Distance distance = new Distance(from, to, type);
		
		// test
		assertNotNull(distance.getValue());
		distance.setValue(null);
		assertNull(distance.getValue());
		distance.setValue(value);
		assertEquals(value, distance.getValue(), DELTA_TOLERANCE);
	}

	/**
	 * Test method for Distance's getDistanceType()
	 */
	@Test
	public void testGetDistanceType() {
		// setup
		DistanceType distanceType = new DistanceTypeMetricMeter();
		
		Distance distance = new Distance(null, distanceType);
		
		// test
		assertEquals(distance.getDistanceType(), distanceType);
	}

	/**
	 * Test method for Distance's setDistanceType(DistanceType distanceType)
	 */
	@Test
	public void testSetDistanceType() {
		// setup
		DistanceType distanceType = new DistanceTypeMetricMeter();
		
		Distance distance = new Distance(null, distanceType);
		
		// test
		assertNotNull(distance.getDistanceType());
		assertEquals(distance.getDistanceType(), distanceType);
		
		distance.setDistanceType(null);
		
		assertNull(distance.getDistanceType());
	}
	
		/**
		 * Test method for Distance's getScaleFactor()
		 */
		@Test
		public void testGetScaleFactor() {
			// setup
			Double scaleFactor = SCALE_FACTOR_DEFAULT;
			Distance distance = new Distance();
			
			// test
			assertEquals(scaleFactor, distance.getScaleFactor(), DELTA_TOLERANCE);
		}

		/**
		 * Test method for Distance's setScaleFactor(double scaleFactor)
		 */
		@Test
		public void testSetScaleFactor() {
			// setup
			Distance distance = new Distance();
			Double scaleFactor = SCALE_FACTOR_DEFAULT *2 + 1;
			distance.setScaleFactor(scaleFactor);
			
			// test
			assertEquals(scaleFactor, distance.getScaleFactor(), DELTA_TOLERANCE);
		}

		/**
		 * Test method for Distance's getName()
		 */
		@Test
		public void testGetName() {
			// setup
			Double scaleFactor = SCALE_FACTOR_DEFAULT *2 +1;
			String name = "name";
			Distance distance = new Distance(null, null, null, scaleFactor, name);
			
			// test
			assertEquals(name, distance.getName());
		}

		/**
		 * Test method for Distance's setName(String name)
		 */
		@Test
		public void testSetName() {
			// setup
			Double scaleFactor = SCALE_FACTOR_DEFAULT *2 +1;
			String name = "name";
			Distance distance = new Distance(null, null, null, scaleFactor, null);
			distance.setName(name);
			
			// test
			assertEquals(name, distance.getName());
		}
		
	/**
	 * Test method for Distance's hashCode();
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
		
		DistanceType distanceType = new DistanceTypeMetricMeter();
		DistanceType distanceType4 =  new DistanceTypeImperialInch();
		double scaleFactor = SCALE_FACTOR_DEFAULT;
		String name = "name";
		String name6 = "name6";
		
		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		
		Point<Coordinates> from1 = new Point<>();
		Point<Coordinates> to1 = new Point<>();
		Point<Coordinates> from2 = new Point<>(coord1);
		Point<Coordinates> to2 = new Point<>(coord2);
		Point<Coordinates> from3 = new Point<>(coord1);
		Point<Coordinates> to3 = new Point<>(coord2);
		
		// the scaleFactor and name parameters does not meet any importance in hashCode() method
		Distance distance1 = new Distance(from1, to1, distanceType, scaleFactor, name);
		Distance distance2 = new Distance(from2, to2, distanceType, scaleFactor, name);
		Distance distance3 = new Distance(from3, to3, distanceType, scaleFactor, name);
		Distance distance4 = new Distance(from2, to2, distanceType4, scaleFactor, name);
		Distance distance5 = new Distance(from2, to2, distanceType, scaleFactor * 2, name);
		Distance distance6 = new Distance(from2, to2, distanceType, scaleFactor, name6);
		Distance distance7 = new Distance(null, null, null, scaleFactor, name);
		Distance distance8 = new Distance(null, null, null, scaleFactor, null);
		
		// test
		assertEquals(distance1, distance1);
		assertEquals(distance2, distance3);
		assertEquals(distance2, distance5);
		assertEquals(distance2, distance6);
		assertEquals(distance7, distance8);
		
		assertNotEquals(distance1, distance2);
		assertNotEquals(distance2, distance4);
	}
	
	/**
	 * Test method for Distance's equals(Object obj)
	 */
	@Test
	public void testEquals() {
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
		
		Coordinates coord1 = new CoordinatesRectangular(N1, E1, H1);
		Coordinates coord2 = new CoordinatesRectangular(N2, E2, H2);
		
		DistanceType distanceType = new DistanceTypeMetricMeter();
		DistanceType distanceType4 =  new DistanceTypeImperialInch();
		double scaleFactor = SCALE_FACTOR_DEFAULT;
		String name = "name";
		String name6 = "name6";
		
		Point<Coordinates> from1 = new Point<>();
		Point<Coordinates> to1 = new Point<>();
		Point<Coordinates> from2 = new Point<>(coord1);
		Point<Coordinates> to2 = new Point<>(coord2);
		
		// the scaleFactor and name parameters does not meet any importance in hashCode() method
		Distance distance1 = new Distance(from1, to1, distanceType, scaleFactor, name);
		Distance distance2 = new Distance(from2, to2, distanceType, scaleFactor, name);
		Distance distance3 = new Distance(from2, to2, distanceType, scaleFactor, name);
		Distance distance4 = new Distance(from2, to2, distanceType4, scaleFactor, name);
		Distance distance5 = new Distance(from2, to2, distanceType, scaleFactor * 2, name);
		Distance distance6 = new Distance(from2, to2, distanceType, scaleFactor, name6);
		Distance distance7 = new Distance(null, null, null, scaleFactor, name);
		Distance distance8 = new Distance(null, null, null, scaleFactor, null);
		
		// test
		assertTrue(distance2.equals(distance3));
		assertTrue(distance2.equals(distance5));
		assertTrue(distance2.equals(distance6));
		assertTrue(distance7.equals(distance8));
		
		assertFalse(distance1.equals(distance2));
		assertFalse(distance2.equals(distance4));
	}
}
