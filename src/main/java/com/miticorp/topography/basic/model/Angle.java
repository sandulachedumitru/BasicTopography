package com.miticorp.topography.basic.model;

/**
 * 
 * Base class for angular representation (angular value, angular type ex: grad, deg, rad)
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 * 
 */
public class Angle {
	private static final AngleType angleTypeCentesimal = new AngleTypeCentesimal();
	private static final AngleType angleTypeHexadecimal = new AngleTypeHexadecinal();
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
	 * Method for transformation parameters between two angular systems
	 * @param fromSystem first system
	 * @param toSystem second system
	 * @param value angular value in first system
	 * @return angular value in second system
	 */
	public static Double transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, Double value) {
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
	 * Method for transformation parameters between two angular systems
	 * @param fromSystem first system (is embedded in Angle's angleType propertie)
	 * @param toSystem second system.
	 * Embedded Angle's value propertie is set with return angular value.
	 * So is no need, after method returned, to set value properties.
	 * @return angular value in second system
	 */
	public static Double transformAngleFromSystemToSystem(Angle fromSystem, Angle toSystem) {
		Double value;
		AngleType from, to;
		
		from = fromSystem.getAngleType();
		to = toSystem.getAngleType();
		value = fromSystem.getValue();
		
		if (from instanceof AngleTypeCentesimal) {
			if (to instanceof AngleTypeCentesimal) {}
			else if (to instanceof AngleTypeHexadecinal) {value *= CENTESIMAL_TO_HEXADECIMAL;}
			else if (to instanceof AngleTypeRadian) {value *= CENTESIMAL_TO_RADIAN;}
			else value = null;
		}
		else if (from instanceof AngleTypeHexadecinal) {
			if (to instanceof AngleTypeHexadecinal) {}
			else if (to instanceof AngleTypeCentesimal) {value *= HEXADECIMAL_TO_CENTESIMAL;}
			else if (to instanceof AngleTypeRadian) {value *= HEXADECIMAL_TO_RADIAN;}
			else value = null;
		}
		else if (from instanceof AngleTypeRadian) {
			if (to instanceof AngleTypeRadian) {}
			else if (to instanceof AngleTypeCentesimal) {value *= RADIAN_TO_CENTESIMAL;}
			else if (to instanceof AngleTypeHexadecinal) {value *= RADIAN_TO_HEXADECIMAL;}
			else value = null;
		}
		else value = null;
		
		toSystem.setValue(value);
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
		return transformAngleFromSystemToSystem(angle.getAngleType(), this.angleType, angle.getValue());
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angleType == null) ? 0 : angleType.hashCode());
		result = prime * result + (clockwize ? 1231 : 1237);
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
		if (clockwize != other.clockwize)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
