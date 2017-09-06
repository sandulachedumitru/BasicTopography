package com.miticorp.topography.basic.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
 * Test class for Angle class.
 * Angle is the Base class for angular representation (angular value, angular type ex: grad, deg, rad)
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */
public class AngleTest {
	private static final double DELTA_TOLERANCE = 0.0001;
	private static final double SCALE_FACTOR_DEFAULT = 1D;

	/**
	 * Test constructor Angle()
	 */
	@Test
	public void testAngle() {
		// setup
		Angle angle = new Angle();
		
		// test
		assertTrue((angle.getValue() == null) && (angle.getAngleType() == null) && angle.isClockwise());
		
		assertNull(angle.getValue());
		assertNull(angle.getAngleType());
		assertTrue(angle.isClockwise());
		assertTrue(angle.getScaleFactor() == SCALE_FACTOR_DEFAULT);
		assertNull(angle.getName());
	}
	
	/**
	 * Test constructor Angle(Double value, AngleType angleType, boolean clockwise, double scaleFactor, String name)
	 */
	@Test
	public void testAngleDoubleAngleTypeBooleanDoubleString() {
		// setup
		AngleType angleType = new AngleTypeCentesimal();
		Double value = (new Random()).nextDouble() * angleType.getMaxNumberOfCircleDegrees();
		boolean clockwise = false;
		double scaleFactor = (new Random()).nextDouble() * 100;
		String name = "test point";
		
		Angle angle = new Angle(value, angleType, clockwise, scaleFactor, name);
		
		// test
		assertTrue(angle.getValue().equals(value));
		assertTrue(angle.getAngleType().equals(angleType));
		assertFalse(angle.isClockwise());
		assertTrue(angle.getScaleFactor() == scaleFactor);
		assertTrue(angle.getName().equals(name));
	}

	/**
	 * Test constructor Angle(Double value, AngleType angleType)
	 */
	@Test
	public void testAngleDoubleAngleType() {
		// setup
		AngleType current;
		Double value;
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(value, current);
		
		// test
		// clockwise == true by default
		assertTrue(angle.getValue().equals(value));
		assertTrue(angle.getAngleType().equals(current));
		assertTrue(angle.isClockwise());
		assertTrue(angle.getScaleFactor() == SCALE_FACTOR_DEFAULT);
		assertTrue(angle.getName() == null);
	}
	
	/**
	 * Test constructor Angle(Double value, AngleType angleType, boolean clockwize)
	 */
	@Test
	public void testAngleDoubleAngleTypeBoolean() {
		// setup
		AngleType current;
		Double value;
		boolean clockwise; // == true by default
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		clockwise = false;
		Angle angle = new Angle(value, current, clockwise);
		
		// test
		assertTrue(angle.getValue().equals(value));
		assertTrue(angle.getAngleType().equals(current));
		assertFalse(angle.isClockwise());
		assertTrue(angle.getScaleFactor() == SCALE_FACTOR_DEFAULT);
		assertTrue(angle.getName() == null);
	}

	/**
	 * Test method for Angle's transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Double value)
	 */
	@Test
	public void testTransformAngleFromSystemToSystem() {
		// setup
		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecinal();
		AngleType rad = new AngleTypeRadian();
		
		// test
		loop1(cent, cent, 1);
		loop1(cent, hex, Angle.CENTESIMAL_TO_HEXADECIMAL);
		loop1(cent, rad, Angle.CENTESIMAL_TO_RADIAN);

		loop1(hex, hex, 1);
		loop1(hex, cent, Angle.HEXADECIMAL_TO_CENTESIMAL);
		loop1(hex, rad, Angle.HEXADECIMAL_TO_RADIAN);

		loop1(rad, rad, 1);
		loop1(rad, cent, Angle.RADIAN_TO_CENTESIMAL);
		loop1(rad, hex, Angle.RADIAN_TO_HEXADECIMAL);
		
		// test null case
		Angle.transformAngleFromSystemToSystem(null, null, null);
	}
	
	private static void loop1(AngleType from, AngleType to, double factor) {
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
	} // private method
	
	
	/**
	 * Test method for Angle's transformAngleFromSystemToSystem(Angle fromSystem, Angle toSystem)
	 */
	@Test
	public void testTransformAngleFromSystemToSystem2() {
		// setup
		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecinal();
		AngleType rad = new AngleTypeRadian();
		
		Angle angleCent = new Angle(null, cent);
		Angle angleHex = new Angle(null, hex);
		Angle angleRad = new Angle(null, rad);
		
		// test
		loop3(angleCent, angleCent, 1);
		loop3(angleCent, angleHex, Angle.CENTESIMAL_TO_HEXADECIMAL);
		loop3(angleCent, angleRad, Angle.CENTESIMAL_TO_RADIAN);

		loop3(angleHex, angleHex, 1);
		loop3(angleHex, angleCent, Angle.HEXADECIMAL_TO_CENTESIMAL);
		loop3(angleHex, angleRad, Angle.HEXADECIMAL_TO_RADIAN);

		loop3(angleRad, angleRad, 1);
		loop3(angleRad, angleCent, Angle.RADIAN_TO_CENTESIMAL);
		loop3(angleRad, angleHex, Angle.RADIAN_TO_HEXADECIMAL);
		
		// test null case
		Angle.transformAngleFromSystemToSystem(null, null);
	}
	
	private void loop3(Angle from, Angle to, double factor) {
		Double rez;
		Random random = new Random();
		double lenght, value;

		lenght = from.getAngleType().getMaxNumberOfCircleDegrees();
		if (!(from.getAngleType() instanceof AngleTypeRadian)) {
			for (int i = 0; i <= lenght; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (lenght/4)) != 0D ) value += temp;
				// System.out.println(value);
				from.setValue(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to);
				assertTrue(rez.equals(value*factor));
				assertTrue(rez.equals(to.getValue()));
			}

		} else {
			for (double i = 0; i <= lenght; i += (lenght / 400) ) {
				value = (double) i;
				// System.out.println(value);
				from.setValue(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to);
				assertTrue(rez.equals(value * factor));
				assertTrue(rez.equals(to.getValue()));
			}
		} // else
	} // private method
	
	/**
	 * Test method for Angle's transformAngleFromCurrentToAnotherSystem(AngleType toSystem)
	 */
	@Test
	public void testTransformAngleFromCurrentToAnotherSystem() {
		// setup
		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecinal();
		AngleType rad = new AngleTypeRadian();
		
		// test
		loop2(cent, cent, 1);
		loop2(cent, hex, Angle.CENTESIMAL_TO_HEXADECIMAL);
		loop2(cent, rad, Angle.CENTESIMAL_TO_RADIAN);

		loop2(hex, hex, 1);
		loop2(hex, cent, Angle.HEXADECIMAL_TO_CENTESIMAL);
		loop2(hex, rad, Angle.HEXADECIMAL_TO_RADIAN);

		loop2(rad, rad, 1);
		loop2(rad, cent, Angle.RADIAN_TO_CENTESIMAL);
		loop2(rad, hex, Angle.RADIAN_TO_HEXADECIMAL);
		
		// test null case
		assertNull(new Angle().transformAngleFromCurrentToAnotherSystem(null));
	}

	private static void loop2(AngleType from, AngleType to, double factor) {
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
				angle = new Angle(value, from);
				rez = angle.transformAngleFromCurrentToAnotherSystem(to);
				assertTrue(rez.equals(value*factor));
			}

		} else {
			for (double i = 0; i <= lenght; i += (lenght / 400) ) {
				value = (double) i;
				// System.out.println(value);
				angle = new Angle(value, from);
				rez = angle.transformAngleFromCurrentToAnotherSystem(to);
				assertTrue(rez.equals(value * factor));
			}
		} // else
	} // private method
	
	/**
	 * Test method for Angle's transformAngleFromAnotherToCurrentSystem (Angle angle)
	 */
	@Test
	public void testTransformAngleFromAnotherToCurrentSystem() {
		// setup
		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecinal();
		AngleType rad = new AngleTypeRadian();
		
		Angle angleCent = new Angle(null, cent);
		Angle angleHex = new Angle(null, hex);
		Angle angleRad = new Angle(null, rad);
		
		// test
		loop4(angleCent, angleCent, 1);
		loop4(angleHex, angleCent, Angle.HEXADECIMAL_TO_CENTESIMAL);
		loop4(angleRad, angleCent, Angle.RADIAN_TO_CENTESIMAL);
		
		loop4(angleHex, angleHex, 1);
		loop4(angleCent, angleHex, Angle.CENTESIMAL_TO_HEXADECIMAL);
		loop4(angleRad, angleHex, Angle.RADIAN_TO_HEXADECIMAL);
		
		loop4(angleRad, angleRad, 1);
		loop4(angleCent, angleRad, Angle.CENTESIMAL_TO_RADIAN);
		loop4(angleHex, angleRad, Angle.HEXADECIMAL_TO_RADIAN);
		
		// test null case
		assertNull(new Angle().transformAngleFromAnotherToCurrentSystem(null));
	}
	
	private void loop4(Angle another, Angle current, double factor) {
		Double rez;
		Random random = new Random();
		double lenght, value;

		lenght = another.getAngleType().getMaxNumberOfCircleDegrees();
		if (!(another.getAngleType() instanceof AngleTypeRadian)) {
			for (int i = 0; i <= lenght; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (lenght/4)) != 0D ) value += temp;
				// System.out.println(value);
				another.setValue(value);
				rez = current.transformAngleFromAnotherToCurrentSystem(another);
				// System.out.println(value + " --> " + value*factor + " --> " + rez);
				assertTrue(rez.equals(value*factor));
				assertTrue(rez.equals(current.getValue()));
			}

		} else {
			for (double i = 0; i <= lenght; i += (lenght / 400) ) {
				value = (double) i;
				// System.out.println(value);
				another.setValue(value);
				rez = current.transformAngleFromAnotherToCurrentSystem(another);
				// System.out.println(value + " --> " + value*factor + " --> " + rez);
				assertTrue(rez.equals(value * factor));
				assertTrue(rez.equals(current.getValue()));
			}
		} // else
	} // private method

	/**
	 * Test method for Angle's getValue()
	 */
	@Test
	public void testGetValue() {
		// setup
		AngleType current;
		Double value;
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(value, current);
		
		// test
		assertTrue(value.equals(angle.getValue()));
	}

	/**
	 * Test method for Angle's setValue(Double value)
	 */
	@Test
	public void testSetValue() {
		// setup
		AngleType current;
		Double value;
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(null, current);
		angle.setValue(value);
		
		// test
		assertTrue(value.equals(angle.getValue()));
	}

	/**
	 * Test method for Angle's getAngleType()
	 */
	@Test
	public void testGetAngleType() {
		// setup
		AngleType current;
		
		current = new AngleTypeCentesimal();
		Angle angle = new Angle(null, current);
		
		// test
		assertTrue(angle.getAngleType().equals(current));
	}

	/**
	 * Test method for Angle's setAngleType(AngleType angleType)
	 */
	@Test
	public void testSetAngleType() {
		// setup
		AngleType current;
		
		current = new AngleTypeCentesimal();
		Angle angle = new Angle();
		angle.setAngleType(current);
		
		// test
		assertTrue(angle.getAngleType().equals(current));
	}

	/**
	 * Test method for Angle's isClockwise()
	 */
	@Test
	public void testIsClockwise() {
		// setup
		Angle angleTrue = new Angle();
		Angle angleFalse = new Angle(null, null, false);
		
		// test
		assertTrue(angleTrue.isClockwise());
		assertFalse(angleFalse.isClockwise());
	}

	/**
	 * Test method for Angle's setClockwise(boolean clockwise)
	 */
	@Test
	public void testSetClockwise() {
		// setup
		Angle angleTrue = new Angle();
		Angle angleFalse = new Angle(); angleFalse.setClockwise(false);
		
		// test
		assertTrue(angleTrue.isClockwise());
		assertFalse(angleFalse.isClockwise());
	}
	
	/**
	 * Test method for Angle's equals(Object obj)
	 */
	@Test
	public void testEquals() {
		// setup
		AngleType angle = new AngleTypeCentesimal();
		Random random = new Random();
		Double value = random.nextDouble()*angle.getMaxNumberOfCircleDegrees();
		
		Angle angle1 = new Angle();
		Angle angle2 = new Angle();
		Angle angle3 = new Angle(value, angle);
		Angle angle4 = new Angle(value, angle, false);
		Angle angle5 = new Angle(value*2, angle);
		Angle angle6 = new Angle(value, new AngleTypeHexadecinal());
		
		// test
		assertTrue(angle1.equals(angle1));
		assertTrue(angle1.equals(angle2));
		
		assertFalse(angle1.equals(angle3));
		assertFalse(angle3.equals(angle4));
		assertFalse(angle3.equals(angle5));
		assertFalse(angle3.equals(angle6));
	}
	
	// TODO implements test for hashcode()
}
