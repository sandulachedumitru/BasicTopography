package com.miticorp.topography.basic.model;

public class Angle {
	public static final double CENTESIMAL_TO_RADIAN = 2*Math.PI/400;
	public static final double RADIAN_TO_CENTESIMAL = 400/(2*Math.PI);
	
	public static final double CENTESIMAL_TO_HEXADECIMAL = 360/400;
	public static final double HEXADECIMAL_TO_CENTESIMAL = 400/360;
	
	public static final double HEXADECIMAL_TO_RADIAN = 2*Math.PI/360;
	public static final double RADIAN_TO_HEXADECIMAL = 360/(2*Math.PI); 
	
	private Double value;
	private AngleType angleType;
	
	public Angle() {}
	public Angle(Double value, AngleType angleType) {
		super();
		this.value = value;
		this.angleType = angleType;
	}

	public static void transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem) {
		if (fromSystem instanceof AngleTypeCentesimal) {
			if (toSystem instanceof AngleTypeCentesimal) {toSystem.setValue(fromSystem.getValue());}
			else if (toSystem instanceof AngleTypeHexadecinal) {toSystem.setValue(fromSystem.getValue() * CENTESIMAL_TO_HEXADECIMAL);}
			else if (toSystem instanceof AngleTypeRadian) {toSystem.setValue(fromSystem.getValue() * CENTESIMAL_TO_RADIAN);}
			else toSystem.setValue(null); 
		}
		else if (fromSystem instanceof AngleTypeHexadecinal) {
			if (toSystem instanceof AngleTypeHexadecinal) {toSystem.setValue(fromSystem.getValue());}
			else if (toSystem instanceof AngleTypeCentesimal) {toSystem.setValue(fromSystem.getValue() * HEXADECIMAL_TO_CENTESIMAL); }
			else if (toSystem instanceof AngleTypeRadian) {toSystem.setValue(fromSystem.getValue() * HEXADECIMAL_TO_RADIAN);}
			else toSystem.setValue(null);
		}
		else if (fromSystem instanceof AngleTypeRadian) {
			if (toSystem instanceof AngleTypeRadian) {toSystem.setValue(fromSystem.getValue());}
			else if (toSystem instanceof AngleTypeCentesimal) {toSystem.setValue(fromSystem.getValue() * RADIAN_TO_CENTESIMAL);}
			else if (toSystem instanceof AngleTypeHexadecinal) {toSystem.setValue(fromSystem.getValue() * RADIAN_TO_HEXADECIMAL);}
			else toSystem.setValue(null);
		}
		else toSystem.setValue(null);
	}
	
	public void transformAngleToSystem(AngleType toSystem) {
		transformAngleFromSystemToSystem(this.angleType, toSystem);
	}
	
	
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
