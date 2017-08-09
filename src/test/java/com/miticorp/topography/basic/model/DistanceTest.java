package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

// TODO de terminat clasele de test
public class DistanceTest {

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
		//System.out.println(distance1.getValue());
		
		assertTrue(distance2.getDistanceType() == type2 && distance2.getFrom() == from2 && distance2.getTo() == to2);
		assertNull(distance2.getValue());
		//System.out.println(distance2.getValue());

		assertTrue(distance3.getDistanceType() == null && distance3.getFrom() == null && distance3.getTo() == null);
		assertNull(distance3.getValue());
		//System.out.println(distance3.getValue());
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
		Point<CoordinatesRectangular> from1 = new Point<>((CoordinatesRectangular) coord1);
		Point<CoordinatesRectangular> to1 = new Point<>((CoordinatesRectangular) coord2);
		
		Point<CoordinatesRectangular> from2 = new Point<>(null);
		Point<CoordinatesRectangular> to2 = new Point<>(null);
		
		Distance<CoordinatesRectangular> distance1 = new Distance<>(from1, to1);
		Distance<CoordinatesRectangular> distance2 = new Distance<>(from2, to2);
		Distance<CoordinatesRectangular> distance3 = new Distance<>((Point<CoordinatesRectangular>) null, null);
		
		// test
		assertTrue(distance1.getDistanceType() instanceof DistanceTypeMetricMeter && distance1.getFrom() == from1 && distance1.getTo() == to1);
		assertNotNull(distance1.getValue());
		//System.out.println(distance1.getValue());
		
		assertTrue(distance2.getDistanceType() instanceof DistanceTypeMetricMeter && distance2.getFrom() == from2 && distance2.getTo() == to2);
		assertNull(distance2.getValue());
		//System.out.println(distance2.getValue());

		assertTrue(distance3.getDistanceType() instanceof DistanceTypeMetricMeter && distance3.getFrom() == null && distance3.getTo() == null);
		assertNull(distance3.getValue());
		//System.out.println(distance3.getValue());
	}

	/**
	 * Test constructor Distance(Double value, DistanceType distanceType)
	 */
	@Test
	public void testDistanceDoubleDistanceType() {
		// setup
		Double value = (new Random().nextDouble()) * 100;
		DistanceType distanceType = new DistanceTypeImperialInch();
		
		Distance<CoordinatesRectangular> distance = new Distance<>(value, distanceType);
		Distance<CoordinatesRectangular> distance2 = new Distance<>((Double) null, null);
		
		// test
		assertTrue(distance.getValue() == value && distance.getDistanceType() == distanceType);
		assertNull(distance.getFrom());
		assertNull(distance.getTo());
		
		assertNull(distance2.getValue());
		assertNull(distance2.getDistanceType());
		assertNull(distance2.getFrom());
		assertNull(distance2.getTo());
	}

	/**
	 * Test constructor Distance(Double value)
	 */
	@Test
	public void testDistanceDouble() {
		// setup
		Double value1 = (new Random().nextDouble()) * 100;
		Double value2 = null;
		
		Distance<CoordinatesRectangular> distance1 = new Distance<>(value1);
		Distance<CoordinatesRectangular> distance2 = new Distance<>(value2);
		
		
		// test
		assertTrue(distance1.getValue() == value1 && distance1.getDistanceType() instanceof DistanceTypeMetricMeter);
		assertNull(distance1.getFrom());
		assertNull(distance1.getTo());
		
		assertTrue(distance2.getValue() == value2 && distance2.getDistanceType() instanceof DistanceTypeMetricMeter);
		assertNull(distance2.getFrom());
		assertNull(distance2.getTo());
	}

	/**
	 * Test constructor Distance()
	 */
	@Test
	public void testDistance() {
		// setup
		Distance<CoordinatesRectangular> distance = new Distance<>();
		
		// test
		assertTrue(distance.getDistanceType() instanceof DistanceTypeMetricMeter);
		assertNull(distance.getValue());
		assertNull(distance.getFrom());
		assertNull(distance.getTo());
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
		rez = Distance.transformDistanceFromSystemToSystem((DistanceTypeMetricMeter) null, (DistanceTypeMetricMeter) null, value);
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

	@Test
	public void testTransformDistanceFromSystemToSystemDistanceOfQDistanceOfQ() {
		// setup
		Distance<?> from1, from2;
		Distance<?> to1, to2;
		Double value1 = 1D, value2 = null;
		Double rez1 = null, rez2 = null;
		
		DistanceType distanceType1 = new DistanceTypeMetricMeter();
		DistanceType distanceType2 = new DistanceTypeImperialYard();
		
		from1 = new Distance<CoordinatesRectangular>(value1, distanceType1);
		to1 = new Distance<CoordinatesRectangular>(null, distanceType2);
		from2 = new Distance<CoordinatesRectangular>(value2, distanceType1);
		to2 = new Distance<CoordinatesRectangular>(null, distanceType2);
		
		rez1 = Distance.transformDistanceFromSystemToSystem(from1, to1);
		//System.out.println("rez1(" + rez1 + ") / value1(" + value1 + ")");
		rez2 = Distance.transformDistanceFromSystemToSystem(from2, to2);
		//System.out.println("rez2(" + rez2 + ") / value2(" + value2 + ")");
		
		// test
		assertNotNull(value1);
		assertNotNull(rez1);
		assertEquals(rez1, value1 * Distance.METER_TO_YARD, 0.0001);
		assertEquals(value1 * Distance.METER_TO_YARD, to1.getValue(), 0.0001);
		
		assertNull(value2);
		assertNull(rez2);
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
