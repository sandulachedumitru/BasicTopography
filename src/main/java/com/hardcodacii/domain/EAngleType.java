package com.hardcodacii.domain;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public enum EAngleType {
	CENTESIMAL(400D),
	HEXADEDECIMAL(360D),
	RADIAN(2 * Math.PI);

	private final double degrees;

	EAngleType(double maxDegrees) {
		degrees = maxDegrees;
	}

	public double getMaxNumberOfCircleDegrees() {
		return degrees;
	}

	public double getFactorToChangeAngleToSystem(EAngleType type) {
		return type.getMaxNumberOfCircleDegrees() / getMaxNumberOfCircleDegrees();
	}
}
