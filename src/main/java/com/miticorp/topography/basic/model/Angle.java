package com.miticorp.topography.basic.model;

/**
 * 
 * Base class for angular reprezentation (angular value, angular type ex: grad, deg, rad)
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 * 
 */
public class Angle {
	private static final AngleType angleTypeCentesimal = new AngleTypeCentesimal();
	private static final AngleType angleTypeHexadecimal = new AngleTypeHexadecinal();
	private static final AngleType angleTypeRadian = new AngleTypeRadian();
	
	// Tronsformation parameters between two angular systems
	public static final double CENTESIMAL_TO_RADIAN = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_CENTESIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double CENTESIMAL_TO_HEXADECIMAL = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeHexadecimal);
	public static final double HEXADECIMAL_TO_CENTESIMAL = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double HEXADECIMAL_TO_RADIAN = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_HEXADECIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeHexadecimal);
	
	// Fields exposed by Angle by getters and setters
	private Double value;
	private AngleType angleType;
	private boolean clockwize = true; // true by default
	
	// Constructors
	public Angle() {}
	public Angle(Double value, AngleType angleType, boolean clockwize) {
		super();
		this.value = value;
		this.angleType = angleType;
		this.clockwize = clockwize;
	}
	public Angle(Double value, AngleType angleType) {
		super();
		this.value = value;
		this.angleType = angleType;
	}

	/**
	 * Method for tronsformation parameters between two angular systems
	 * @param fromSystem first system
	 * @param toSystem second system
	 * @param angle angular value in first system
	 * @return angular value in second system
	 */
	public static Double transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Angle angle) {
		Double value = angle.getValue();
		if (fromSystem instanceof AngleTypeCentesimal) {
			if (toSystem instanceof AngleTypeCentesimal) {return value;}
			else if (toSystem instanceof AngleTypeHexadecinal) {return value * CENTESIMAL_TO_HEXADECIMAL;}
			else if (toSystem instanceof AngleTypeRadian) {return value * CENTESIMAL_TO_RADIAN;}
			else return null; 
		}
		else if (fromSystem instanceof AngleTypeHexadecinal) {
			if (toSystem instanceof AngleTypeHexadecinal) {return value;}
			else if (toSystem instanceof AngleTypeCentesimal) {return value * HEXADECIMAL_TO_CENTESIMAL; }
			else if (toSystem instanceof AngleTypeRadian) {return value * HEXADECIMAL_TO_RADIAN;}
			else return null;
		}
		else if (fromSystem instanceof AngleTypeRadian) {
			if (toSystem instanceof AngleTypeRadian) {return value;}
			else if (toSystem instanceof AngleTypeCentesimal) {return value * RADIAN_TO_CENTESIMAL;}
			else if (toSystem instanceof AngleTypeHexadecinal) {return value* RADIAN_TO_HEXADECIMAL;}
			else return null;
		}
		else return null;
	}
	
	/**
	 * Method for tronsformation parameters between current angular system and another angular systems
	 * @param toSystem target system
	 * @param angle angular value in current system
	 * @return angular value in target system
	 */
	public Double transformAngleFromCurrentToAnotherSystem(AngleType toSystem, Angle angle) {
		return transformAngleFromSystemToSystem(this.angleType, toSystem, angle);
	}
	
	/**
	 * Method for tronsformation parameters between an angular system and current angular systems
	 * @param fromSystem source system
	 * @param angle angular value in source system
	 * @return angular value in current system
	 */
	public Double transformAngleFromAnotherToCurrentSystem (AngleType fromSystem, Angle angle) {
		return transformAngleFromSystemToSystem(fromSystem, this.angleType, angle);
	}
	
	
	// Getters and Setters
	public synchronized Double getValue() {
		return value;
	}
	public synchronized void setValue(Double value) {
		this.value = value;
	}
	public synchronized AngleType getAngleType() {
		return angleType;
	}
	public synchronized void setAngleType(AngleType angleType) {
		this.angleType = angleType;
	}
	public synchronized boolean isClockwize() {
		return clockwize;
	}
	public synchronized void setClockwize(boolean clockwize) {
		this.clockwize = clockwize;
	}
}
