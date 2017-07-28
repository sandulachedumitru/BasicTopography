package com.miticorp.topography.basic.model;

public class Angle {
	private static final AngleType angleTypeCentesimal = new AngleTypeCentesimal();
	private static final AngleType angleTypeHexadecimal = new AngleTypeHexadecinal();
	private static final AngleType angleTypeRadian = new AngleTypeRadian();
	
	public static final double CENTESIMAL_TO_RADIAN = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_CENTESIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double CENTESIMAL_TO_HEXADECIMAL = angleTypeCentesimal.getAngleChangeSystemFactor(angleTypeHexadecimal);
	public static final double HEXADECIMAL_TO_CENTESIMAL = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeCentesimal);
	
	public static final double HEXADECIMAL_TO_RADIAN = angleTypeHexadecimal.getAngleChangeSystemFactor(angleTypeRadian);
	public static final double RADIAN_TO_HEXADECIMAL = angleTypeRadian.getAngleChangeSystemFactor(angleTypeHexadecimal);
	
	private Double value;
	private AngleType angleType;
	
	public Angle() {}
	public Angle(Double value, AngleType angleType) {
		super();
		this.value = value;
		this.angleType = angleType;
	}

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
	
	public Double transformAngleToSystem(AngleType toSystem, Angle angle) {
		return transformAngleFromSystemToSystem(this.angleType, toSystem, angle);
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
}
