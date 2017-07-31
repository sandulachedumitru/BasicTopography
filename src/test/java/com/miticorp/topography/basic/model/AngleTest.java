/**
 * 
 */
package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dumitru.sandulache
 *
 */
public class AngleTest {
	private AngleType from;
	private AngleType to;
	private AngleType current;
	private Double value;
	private boolean clockwize; // = true;
	


/*	*//**
	 * @throws java.lang.Exception
	 *//*
	@Before
	public void setUp() throws Exception {
		clockwize = true;
		
	}

	*//**
	 * @throws java.lang.Exception
	 *//*
	@After
	public void tearDown() throws Exception {
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#Angle()}.
	 *//*
	@Test
	public void testAngle() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#Angle(java.lang.Double, com.miticorp.topography.basic.model.AngleType)}.
	 */
	@Test
	public void testAngleDoubleAngleType() {
		// setup
		value = 123.456;
		current = new AngleTypeCentesimal();
		Angle angle = new Angle(value, current);
		
		// test
		assertTrue((angle.getValue().equals(value)) && (angle.getAngleType().equals(current)));
	}

	/**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#transformAngleFromSystemToSystem(com.miticorp.topography.basic.model.AngleType, com.miticorp.topography.basic.model.AngleType, com.miticorp.topography.basic.model.Angle)}.
	 */
	@Test
	public void testTransformAngleFromSystemToSystem() {
		// loop(new AngleTypeCentesimal(), new AngleTypeHexadecinal(), Angle.CENTESIMAL_TO_RADIAN);
		
		Angle angle;
		Double rez;
		Random random = new Random();
		double lenght;
		
		// 1. setup
		from = new AngleTypeCentesimal();
		to = new AngleTypeCentesimal();
		// 1. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value));
		}
		
		// 2. setup
		from = new AngleTypeCentesimal();
		to = new AngleTypeHexadecinal();
		// 2. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value*Angle.CENTESIMAL_TO_HEXADECIMAL));
		}
		
		// 3. setup
		from = new AngleTypeCentesimal();
		to = new AngleTypeRadian();
		// 3. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value*Angle.CENTESIMAL_TO_RADIAN));
		}
		
		// 4. setup
		from = new AngleTypeHexadecinal();
		to = new AngleTypeHexadecinal();
		// 4. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value));
		}
		
		// 5. setup
		from = new AngleTypeHexadecinal();
		to = new AngleTypeCentesimal();
		// 5. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value * Angle.HEXADECIMAL_TO_CENTESIMAL));
		}
		
		// 6. setup
		from = new AngleTypeHexadecinal();
		to = new AngleTypeRadian();
		// 6. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp = random.nextInt(99999); temp /= 10000;
			if ((value % (lenght/4)) != 0D ) value += temp;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value * Angle.HEXADECIMAL_TO_RADIAN));
		}
		
		// 7. setup
		from = new AngleTypeRadian();
		to = new AngleTypeRadian();
		// 7. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (double i = 0; i <= lenght; i += (lenght / 400) ) {
			value = (double) i;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value));
		}
		
		// 8. setup
		from = new AngleTypeRadian();
		to = new AngleTypeCentesimal();
		// 8. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (double i = 0; i <= lenght; i += (lenght / 400) ) {
			value = (double) i;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value * Angle.RADIAN_TO_CENTESIMAL));
		}
		
		// 8. setup
		from = new AngleTypeRadian();
		to = new AngleTypeHexadecinal();
		// 8. test all values cases;
		lenght = from.getMaxNumberOfCircleDegrees();
		for (double i = 0; i <= lenght; i += (lenght / 400) ) {
			value = (double) i;
			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value * Angle.RADIAN_TO_HEXADECIMAL));
		}
		

	}
	
	private static void loop(AngleType from, AngleType to, double factor) {
		Angle angle;
		Double rez;
		Random random = new Random();
		double lenght, value;

		lenght = from.getMaxNumberOfCircleDegrees();
		for (int i = 0; i <= lenght; i += 10 ) {
			value = (double) i;
			double temp;
			if (!(from instanceof AngleTypeRadian)) {
				temp = random.nextInt(99999); temp /= 10000; 
				if ((value % (lenght/4)) != 0D ) value += temp;
			}

			// System.out.println(value);
			angle = new Angle(value, from);
			rez = Angle.transformAngleFromSystemToSystem(from, to, angle);
			assertTrue(rez.equals(value * factor));
		}
	}

/*	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#transformAngleFromCurrentToAnotherSystem(com.miticorp.topography.basic.model.AngleType, com.miticorp.topography.basic.model.Angle)}.
	 *//*
	@Test
	public void testTransformAngleFromCurrentToAnotherSystem() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#transformAngleFromAnotherToCurrentSystem(com.miticorp.topography.basic.model.AngleType, com.miticorp.topography.basic.model.Angle)}.
	 *//*
	@Test
	public void testTransformAngleFromAnotherToCurrentSystem() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#getValue()}.
	 *//*
	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#setValue(java.lang.Double)}.
	 *//*
	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#getAngleType()}.
	 *//*
	@Test
	public void testGetAngleType() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#setAngleType(com.miticorp.topography.basic.model.AngleType)}.
	 *//*
	@Test
	public void testSetAngleType() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#isClockwize()}.
	 *//*
	@Test
	public void testIsClockwize() {
		fail("Not yet implemented");
	}

	*//**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#setClockwize(boolean)}.
	 *//*
	@Test
	public void testSetClockwize() {
		fail("Not yet implemented");
	}
*/
}
