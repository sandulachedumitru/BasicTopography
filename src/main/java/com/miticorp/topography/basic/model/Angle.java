package com.miticorp.topography.basic.model;

/**
 * 
 * Base class for angular representation (angular value, angular type ex: grad, deg, rad)
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 * 
 */
public class Angle extends GeometricElements {
	private static final AngleType angleTypeCentesimal = new AngleTypeCentesimal();
	private static final AngleType angleTypeHexadecimal = new AngleTypeHexadecimal();
	private static final AngleType angleTypeRadian = new AngleTypeRadian();
	
	// Transformation parameters between two angular systems
	public static final double CENTESIMAL_TO_RADIAN = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_CENTESIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double CENTESIMAL_TO_HEXADECIMAL = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeHexadecimal);
	public static final double HEXADECIMAL_TO_CENTESIMAL = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double HEXADECIMAL_TO_RADIAN = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_HEXADECIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeHexadecimal);
	
	// Fields exposed by Angle by getters and setters
	private Double value;
	private AngleType angleType;
	private boolean clockwise = true; // true by default
	
	// Constructors
	public Angle() {}
	public Angle(Double value, AngleType angleType, boolean clockwise, double scaleFactor, String name) {
		super();
		this.value = value;
		this.angleType = angleType;
		this.clockwise = clockwise;
		this.scaleFactor = scaleFactor;
		this.name = name;
	}
	public Angle(Double value, AngleType angleType, boolean clockwise) {
		super();
		this.value = value;
		this.angleType = angleType;
		this.clockwise = clockwise;
	}
	public Angle(Double value, AngleType angleType) {
		super();
		this.value = value;
		this.angleType = angleType;
	}

	/**
	 * Method for transformation parameters between two angular systems
	 * @param fromSystem first system
	 * @param toSystem second system
	 * @param value angular value in first system
	 * @return angular value in second system
	 */
	public static Double transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Double value) {
		if ((fromSystem != null) && (toSystem != null)) {
			if (fromSystem instanceof AngleTypeCentesimal) {
				if (toSystem instanceof AngleTypeCentesimal) {return value;}
				else if (toSystem instanceof AngleTypeHexadecimal) {return value * CENTESIMAL_TO_HEXADECIMAL;}
				else if (toSystem instanceof AngleTypeRadian) {return value * CENTESIMAL_TO_RADIAN;}
			}
			else if (fromSystem instanceof AngleTypeHexadecimal) {
				if (toSystem instanceof AngleTypeHexadecimal) {return value;}
				else if (toSystem instanceof AngleTypeCentesimal) {return value * HEXADECIMAL_TO_CENTESIMAL; }
				else if (toSystem instanceof AngleTypeRadian) {return value * HEXADECIMAL_TO_RADIAN;}
			}
			else if (fromSystem instanceof AngleTypeRadian) {
				if (toSystem instanceof AngleTypeRadian) {return value;}
				else if (toSystem instanceof AngleTypeCentesimal) {return value * RADIAN_TO_CENTESIMAL;}
				else if (toSystem instanceof AngleTypeHexadecimal) {return value* RADIAN_TO_HEXADECIMAL;}
			}
		}
		return null;
	}
	
	/**
	 * Method for transformation parameters between two angular systems
	 * @param fromSystem first system (is embedded in Angle's angleType propertie)
	 * @param toSystem second system.
	 * Embedded Angle's value propertie is set with return angular value.
	 * So is no need, after method returned, to set value properties.
	 * @return angular value in second system
	 */
	public static Double transformAngleFromSystemToSystem(Angle fromSystem, Angle toSystem) {
		Double value = null;
		AngleType from, to;
		
		if ((fromSystem != null) && (toSystem != null)) {
			from = fromSystem.getAngleType();
			to = toSystem.getAngleType();
			value = fromSystem.getValue();
			
			if ((from != null) && (to != null) && (value != null))
				value = transformAngleFromSystemToSystem(from, to, value);
			
			toSystem.setValue(value);
		}

		return value;
	}
	
	/**
	 * Method for transformation parameters between current angular system and another angular systems
	 * @param toSystem target system
	 * @return angular value in target system
	 */
	public Double transformAngleFromCurrentToAnotherSystem(AngleType toSystem) {
		return transformAngleFromSystemToSystem(this.angleType, toSystem, this.getValue());
	}
	
	/**
	 * Method for transformation parameters between an angular system and current angular systems
	 * @param angle angular value in source system
	 * @return angular value in current system
	 */
	public Double transformAngleFromAnotherToCurrentSystem (Angle angle) {
		return transformAngleFromSystemToSystem(angle, this);
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
	public synchronized boolean isClockwise() {
		return clockwise;
	}
	public synchronized void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angleType == null) ? 0 : angleType.hashCode());
		result = prime * result + (clockwise ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Angle other = (Angle) obj;
		if (angleType == null) {
			if (other.angleType != null)
				return false;
		} else if (!angleType.equals(other.angleType))
			return false;
		if (clockwise != other.clockwise)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
