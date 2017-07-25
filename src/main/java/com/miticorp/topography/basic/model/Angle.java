package com.miticorp.topography.basic.model;

public class Angle {
	private static final double CENTESIMAL_TO_RADIAN = 2*Math.PI/400;
	private static final double RADIAN_TO_CENTESIMAL = 400/(2*Math.PI);
	
	private static final double CENTESIMAL_TO_HEXADECIMA = 360/400;
	private static final double HEXADECIMAL_TO_CENTESIMAL = 400/360;
	
	private static final double HEXADECIMAL_TO_RADIAN = 2*Math.PI/360;
	private static final double RADIAN_TO_HEXADECIMAL = 360/(2*Math.PI); 
	
	private double value;
	private AngleType angleType;
	
	public Angle() {}
	public Angle(double value, AngleType angleType) {
		super();
		this.value = value;
		this.angleType = angleType;
	}

	public static Double transformAngleFromSystemToSystem(AngleType fromSystem, AngleType toSystem, double value) {
		if (fromSystem instanceof AngleTypeCentesimal) {
			if (toSystem instanceof AngleTypeCentesimal) {return value;}
			else if (toSystem instanceof AngleTypeHexadecinal) {return value * CENTESIMAL_TO_HEXADECIMA;}
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
	
	public Double transformAngleToSystem2(AngleType toSystem, double value) {
		return transformAngleFromSystemToSystem(this.angleType, toSystem, value);
	}	

}
