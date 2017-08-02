/**
 * 
 */
package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.function.Function;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dumitru.sandulache
 *
 */
// TODO de continuat restul metodelor din clasa de test
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
	*/
	/**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#Angle()}.
	 */
	@Test
	public void testAngle() {
		// setup
		Angle angle = new Angle();
		
		// test
		assertTrue((angle.getValue() == null) && (angle.getAngleType() == null) && (angle.isClockwize() == true));
	}

	/**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#Angle(java.lang.Double, com.miticorp.topography.basic.model.AngleType)}.
	 */
	@Test
	public void testAngleDoubleAngleType() {
		// setup
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(value, current);
		
		// test
		assertTrue((angle.getValue().equals(value)) && (angle.getAngleType().equals(current)));
	}

	/**
	 * Test method for Angle's transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Double value)
	 */
/*	@Test
	public void testTransformAngleFromSystemToSystem() {
		loop(new AngleTypeCentesimal(), new AngleTypeCentesimal(), 1);
		loop(new AngleTypeCentesimal(), new AngleTypeHexadecinal(), Angle.CENTESIMAL_TO_HEXADECIMAL);
		loop(new AngleTypeCentesimal(), new AngleTypeRadian(), Angle.CENTESIMAL_TO_RADIAN);

		loop(new AngleTypeHexadecinal(), new AngleTypeHexadecinal(), 1);
		loop(new AngleTypeHexadecinal(), new AngleTypeCentesimal(), Angle.HEXADECIMAL_TO_CENTESIMAL);
		loop(new AngleTypeHexadecinal(), new AngleTypeRadian(), Angle.HEXADECIMAL_TO_RADIAN);

		loop(new AngleTypeRadian(), new AngleTypeRadian(), 1);
		loop(new AngleTypeRadian(), new AngleTypeCentesimal(), Angle.RADIAN_TO_CENTESIMAL);
		loop(new AngleTypeRadian(), new AngleTypeHexadecinal(), Angle.RADIAN_TO_HEXADECIMAL);
	}
	
	private static void loop(AngleType from, AngleType to, double factor) {
		Angle angle;
		Double rez;
		Random random = new Random();
		double lenght, value;

		lenght = from.getMaxNumberOfCircleDegrees();
		if (!(from instanceof AngleTypeRadian)) {
			for (int i = 0; i <= lenght; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (lenght/4)) != 0D ) value += temp;
				// System.out.println(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to, value);
				assertTrue(rez.equals(value*factor));
			}

		} else {
			for (double i = 0; i <= lenght; i += (lenght / 400) ) {
				value = (double) i;
				// System.out.println(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to, value);
				assertTrue(rez.equals(value * factor));
			}
		} // else
	} // private method */
	/**
	 * Test method for Angle's transformAngleFromCurrentToAnotherSystem(AngleType toSystem)
	 */
	@Test
	public void testTransformAngleFromCurrentToAnotherSystem() {
		Angle a = new Angle();

		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecinal();
		AngleType rad = new AngleTypeRadian();
		
		Operation op = (ang, type) -> {return ang.transformAngleFromCurrentToAnotherSystem(type);};
		
		//Operation op = a::transformAngleFromCurrentToAnotherSystem;
		
		loop2(cent, cent, 1, op);
		loop2(cent, hex, Angle.CENTESIMAL_TO_HEXADECIMAL, op);
		loop2(cent, rad, Angle.CENTESIMAL_TO_RADIAN, op);

		loop2(hex, hex, 1, op);
		loop2(hex, cent, Angle.HEXADECIMAL_TO_CENTESIMAL, op);
		loop2(hex, rad, Angle.HEXADECIMAL_TO_RADIAN, op);

		loop2(rad, rad, 1, op);
		loop2(rad, cent, Angle.RADIAN_TO_CENTESIMAL, op);
		loop2(rad, hex, Angle.RADIAN_TO_HEXADECIMAL, op);
		
//		loop2(new AngleTypeCentesimal(), new AngleTypeCentesimal(), 1, op);
//		loop2(new AngleTypeCentesimal(), new AngleTypeHexadecinal(), Angle.CENTESIMAL_TO_HEXADECIMAL, op);
//		loop2(new AngleTypeCentesimal(), new AngleTypeRadian(), Angle.CENTESIMAL_TO_RADIAN, op);
//
//		loop2(new AngleTypeHexadecinal(), new AngleTypeHexadecinal(), 1, op);
//		loop2(new AngleTypeHexadecinal(), new AngleTypeCentesimal(), Angle.HEXADECIMAL_TO_CENTESIMAL, op);
//		loop2(new AngleTypeHexadecinal(), new AngleTypeRadian(), Angle.HEXADECIMAL_TO_RADIAN, op);
//
//		loop2(new AngleTypeRadian(), new AngleTypeRadian(), 1, op);
//		loop2(new AngleTypeRadian(), new AngleTypeCentesimal(), Angle.RADIAN_TO_CENTESIMAL, op);
//		loop2(new AngleTypeRadian(), new AngleTypeHexadecinal(), Angle.RADIAN_TO_HEXADECIMAL, op);
	}
	
	private interface Operation {
		
		Double execute(Angle angle, AngleType angleType);
	}
	private static void loop2(AngleType from, AngleType to, double factor, Operation op) {
		Angle angle;
		Double rez;
		Random random = new Random();
		double lenght, value;
		
		//Operation op2 = ang -> ang.transformAngleFromCurrentToAnotherSystem(to);

		lenght = from.getMaxNumberOfCircleDegrees();
		if (!(from instanceof AngleTypeRadian)) {
			for (int i = 0; i <= lenght; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (lenght/4)) != 0D ) value += temp;
				// System.out.println(value);
				angle = new Angle(value, from);
				// rez = angle.transformAngleFromCurrentToAnotherSystem(to);
				rez = op.execute(angle, to);
				assertTrue(rez.equals(value*factor));
			}

		} else {
			for (double i = 0; i <= lenght; i += (lenght / 400) ) {
				value = (double) i;
				System.out.println(value);
				angle = new Angle(value, from);
				// rez = angle.transformAngleFromCurrentToAnotherSystem(to);
				rez = op.execute(angle, to);
				assertTrue(rez.equals(value * factor));
			}
		} // else
	} // private method

	/**
	 * Test method for {@link com.miticorp.topography.basic.model.Angle#transformAngleFromAnotherToCurrentSystem(com.miticorp.topography.basic.model.AngleType, com.miticorp.topography.basic.model.Angle)}.
	 */
//	@Test
//	public void testTransformAngleFromAnotherToCurrentSystem() {
//		fail("Not yet implemented");
//	}

	/**
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
