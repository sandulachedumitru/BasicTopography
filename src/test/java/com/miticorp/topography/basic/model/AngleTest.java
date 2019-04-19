package com.miticorp.topography.basic.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test class for Angle class.
 * Angle is the Base class for angular representation (angular value, angular type ex: grad, deg, rad)
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */
public class AngleTest {
	private static final double DELTA_TOLERANCE = 0.0001;
	private static final double SCALE_FACTOR_DEFAULT = (new GeometricElements() {
		@Override public int hashCode() {return 0;}
		@Override public boolean equals(Object obj) {return false;}
		@Override public String  toString() {return "";}
	}).getScale().getScaleFactor();

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
		assertTrue(angle.getScale().getScaleFactor() == SCALE_FACTOR_DEFAULT);
		assertNull(angle.getName());
	}
	
	/**
	 * Test constructor Angle(Double value, AngleType angleType, boolean clockwise, double scaleFactor, String name)
	 */
	@Test
	public void testAngleDoubleAngleTypeBooleanDoubleString() {
		// setup
		AngleType angleType = new AngleTypeCentesimal();
		Scale scale = new Scale();
		Double value = (new Random()).nextDouble() * angleType.getMaxNumberOfCircleDegrees();
		boolean clockwise = false;
		double scaleFactor = (new Random()).nextDouble() * 100;
		scale.setScaleFactor(scaleFactor);
		String name = "test point";
		
		Angle angle = new Angle(value, angleType, clockwise, scale, name);
		
		// test
		assertTrue(angle.getValue().equals(value));
		assertTrue(angle.getAngleType().equals(angleType));
		assertFalse(angle.isClockwise());
		assertTrue(angle.getScale().getScaleFactor() == scaleFactor);
		assertTrue(angle.getName().equals(name));
	}

	/**
	 * Test method for Angle's transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Double value)
	 */
	@Test
	public void testTransformAngleFromSystemToSystem() {
		// setup
		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecimal();
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
		double length, value;

		length = from.getMaxNumberOfCircleDegrees();
		if (!(from instanceof AngleTypeRadian)) {
			for (int i = 0; i <= length; i += 10 ) {
				value = i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (length/4)) != 0D ) value += temp;
				// System.out.println(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to, value);
				assertTrue(rez.equals(value*factor));
			}

		} else {
			for (double i = 0; i <= length; i += (length / 400) ) {
				value = i;
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
		boolean clockwise = true;

		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecimal();
		AngleType rad = new AngleTypeRadian();
		
		Angle angleCent = new Angle(null, cent, clockwise, null, null);
		Angle angleHex = new Angle(null, hex, clockwise, null, null);
		Angle angleRad = new Angle(null, rad, clockwise, null, null);
		
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
		double length, value;

		length = from.getAngleType().getMaxNumberOfCircleDegrees();
		if (!(from.getAngleType() instanceof AngleTypeRadian)) {
			for (int i = 0; i <= length; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (length/4)) != 0D ) value += temp;
				// System.out.println(value);
				from.setValue(value);
				rez = Angle.transformAngleFromSystemToSystem(from, to);
				assertTrue(rez.equals(value*factor));
				assertTrue(rez.equals(to.getValue()));
			}

		} else {
			for (double i = 0; i <= length; i += (length / 400) ) {
				value = i;
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
		AngleType hex = new AngleTypeHexadecimal();
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
		double length, value;
		boolean clockwise = false;

		length = from.getMaxNumberOfCircleDegrees();
		if (!(from instanceof AngleTypeRadian)) {
			for (int i = 0; i <= length; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (length/4)) != 0D ) value += temp;
				// System.out.println(value);
				angle = new Angle(value, from, clockwise, null, null);
				rez = angle.transformAngleFromCurrentToAnotherSystem(to);
				assertTrue(rez.equals(value*factor));
			}

		} else {
			for (double i = 0; i <= length; i += (length / 400) ) {
				value = i;
				// System.out.println(value);
				angle = new Angle(value, from, clockwise, null, null);
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
		boolean clockwise = false;

		AngleType cent = new AngleTypeCentesimal();
		AngleType hex = new AngleTypeHexadecimal();
		AngleType rad = new AngleTypeRadian();
		
		Angle angleCent = new Angle(null, cent, clockwise, null, null);
		Angle angleHex = new Angle(null, hex, clockwise, null, null);
		Angle angleRad = new Angle(null, rad, clockwise, null, null);
		
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
		double length, value;

		length = another.getAngleType().getMaxNumberOfCircleDegrees();
		if (!(another.getAngleType() instanceof AngleTypeRadian)) {
			for (int i = 0; i <= length; i += 10 ) {
				value = (double) i;
				double temp = random.nextInt(99999); temp /= 10000;
				if ((value % (length/4)) != 0D ) value += temp;
				// System.out.println(value);
				another.setValue(value);
				rez = current.transformAngleFromAnotherToCurrentSystem(another);
				// System.out.println(value + " --> " + value*factor + " --> " + rez);
				assertTrue(rez.equals(value*factor));
				assertTrue(rez.equals(current.getValue()));
			}

		} else {
			for (double i = 0; i <= length; i += (length / 400) ) {
				value = i;
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
		boolean clockwise = false;
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(value, current, clockwise, null, null);
		
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
		boolean clockwise = false;
		
		Random random = new Random();
		current = new AngleTypeCentesimal();
		value = random.nextDouble()*current.getMaxNumberOfCircleDegrees();
		Angle angle = new Angle(null, current, clockwise, null, null);
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
		boolean clockwise = false;
		
		current = new AngleTypeCentesimal();
		Angle angle = new Angle(null, current, clockwise, null, null);
		
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
		Angle angleFalse = new Angle(null, null, false, null, null);
		
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

	@Test
	public void testGetScaleFactor() {
		// setup
		Double scaleFactor = SCALE_FACTOR_DEFAULT;
		Angle angle = new Angle();

		// test
		assertEquals(scaleFactor, angle.getScale().getScaleFactor(), DELTA_TOLERANCE);
	}

	@Test
	public void testSetScaleFactor() {
		// setup
		Angle angle = new Angle();
		Double scaleFactor = SCALE_FACTOR_DEFAULT * 2 + 1;
		angle.getScale().setScaleFactor(scaleFactor);

		// test
		assertEquals(scaleFactor, angle.getScale().getScaleFactor(), DELTA_TOLERANCE);
	}

	@Test
	public void testGetScale() {
		// setup
		Angle angle = new Angle();

		// test
		assertNotNull(angle.getScale());
	}

	@Test
	public void testSetSclale() {
		// setup
		Angle angle1 = new Angle();
		Angle angle2 = new Angle();
		Scale scale = new Scale(SCALE_FACTOR_DEFAULT * (new Random().nextDouble()) + 1);

		angle1.setScale(scale);

		// test
		assertNotNull(angle1);
		assertNotEquals(angle1, angle2);
	}

	/**
	 * Test method for Angle's getName()
	 */
	@Test
	public void testGetName() {
		// setup
		String name = "angle name";
		boolean flag = (new Angle()).isClockwise();
		Angle angle = new Angle(null, null, !flag, null, name);

		// test
		assertEquals(name, angle.getName());
	}

	/**
	 * Test method for Angle's setName(String name)
	 */
	@Test
	public void testSetName() {
		// setup
		String name1 = "angle name1", name2 = "angle name2";
		boolean flag = (new Angle()).isClockwise();
		Angle angle = new Angle(null, null, !flag, null, name1);
		angle.setName(name2);

		// test
		assertEquals(name2, angle.getName());
	}
	
	/**
	 * Test method for Angle's equals(Object obj)
	 */
	@Test
	public void testEquals() {
		// setup
		AngleType angleCente = new AngleTypeCentesimal();
		AngleType angleHexa = new AngleTypeHexadecimal();
		Random random = new Random();
		Double value = random.nextDouble()*angleCente.getMaxNumberOfCircleDegrees();
		boolean clockwise = true;
		
		Angle angle1 = new Angle();
		Angle angle2 = new Angle();
		Angle angle3 = new Angle(value, angleCente, clockwise, null, null);
		Angle angle4 = new Angle(value, angleCente, !clockwise, null, null);
		Angle angle5 = new Angle(value*2, angleCente, clockwise, null, null);
		Angle angle6 = new Angle(value, angleHexa, clockwise, null, null);
		
		// test
		assertTrue(angle1.equals(angle2));
		assertTrue(angle3.equals(angle4));
		
		assertFalse(angle1.equals(angle3));
		assertFalse(angle3.equals(angle5));
		assertFalse(angle3.equals(angle6));
		assertFalse(angle5.equals(angle6));
	}
	
	/**
	 * Test method for Angle's hashCode()
	 */
	@Test
	public void testHashCode() {
		// setup
		boolean clockwise = true;

		AngleType angleCente = new AngleTypeCentesimal();
		AngleType angleHexa = new AngleTypeHexadecimal();
		Double value = (new Random()).nextDouble() * angleCente.getMaxNumberOfCircleDegrees();
		
		Angle angle1 = new Angle();
		Angle angle2 = new Angle();
		Angle angle3 = new Angle(value, angleCente, clockwise, null, null);
		Angle angle4 = new Angle(value, angleCente, !clockwise, null, null);
		Angle angle5 = new Angle(value*2, angleCente, clockwise, null, null);
		Angle angle6 = new Angle(value, angleHexa, clockwise, null, null);
		
		// test
		assertEquals(angle1.hashCode(), angle1.hashCode());
		assertEquals(angle1.hashCode(), angle2.hashCode());
		
		assertNotEquals(angle1.hashCode(), angle3.hashCode());
		assertNotEquals(angle3.hashCode(), angle4.hashCode());
		assertNotEquals(angle3.hashCode(), angle5.hashCode());
		assertNotEquals(angle3.hashCode(), angle6.hashCode());
	}
}
