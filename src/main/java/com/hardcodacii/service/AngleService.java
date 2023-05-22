package com.hardcodacii.service;

import com.hardcodacii.domain.Angle;
import com.hardcodacii.domain.EAngleType;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.Units;
import com.hardcodacii.environment.Environment;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;
import static com.hardcodacii.environment.CommonConstants.INVALID_ELEMENT;

/**
 * this class is not designed to be instantiated
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class AngleService {
	// Transformation parameters between two angular systems
	private static final EAngleType angleTypeCentesimal = EAngleType.CENTESIMAL;
	private static final EAngleType angleTypeHexadecimal = EAngleType.HEXADEDECIMAL;
	private static final EAngleType angleTypeRadian = EAngleType.RADIAN;
	private static final double CENTESIMAL_TO_RADIAN = angleTypeCentesimal.getFactorToChangeAngleToSystem(angleTypeRadian);
	private static final double RADIAN_TO_CENTESIMAL = angleTypeRadian.getFactorToChangeAngleToSystem(angleTypeCentesimal);
	private static final double CENTESIMAL_TO_HEXADECIMAL = angleTypeCentesimal.getFactorToChangeAngleToSystem(angleTypeHexadecimal);
	private static final double HEXADECIMAL_TO_CENTESIMAL = angleTypeHexadecimal.getFactorToChangeAngleToSystem(angleTypeCentesimal);
	private static final double HEXADECIMAL_TO_RADIAN = angleTypeHexadecimal.getFactorToChangeAngleToSystem(angleTypeRadian);
	private static final double RADIAN_TO_HEXADECIMAL = angleTypeRadian.getFactorToChangeAngleToSystem(angleTypeHexadecimal);
	// this class is not designed to be instantiated
	private AngleService() {
	}

	/**
	 * Method for transformation parameters between two angular systems
	 *
	 * @param fromSystem first system
	 * @param toSystem   second system
	 * @param value      angular value in first system
	 * @return angular value in second system
	 */
	public static Optional<Double> transformAngleFromSystemToSystem(EAngleType fromSystem, EAngleType toSystem, Double value) {
		if ((fromSystem != null) && (toSystem != null) && (value != null)) {
			if (fromSystem == EAngleType.CENTESIMAL) {
				if (toSystem == EAngleType.CENTESIMAL) {
					return Optional.of(value);
				} else if (toSystem == EAngleType.HEXADEDECIMAL) {
					return Optional.of(value * CENTESIMAL_TO_HEXADECIMAL);
				} else if (toSystem == EAngleType.RADIAN) {
					return Optional.of(value * CENTESIMAL_TO_RADIAN);
				}
			} else if (fromSystem == EAngleType.HEXADEDECIMAL) {
				if (toSystem == EAngleType.HEXADEDECIMAL) {
					return Optional.of(value);
				} else if (toSystem == EAngleType.CENTESIMAL) {
					return Optional.of(value * HEXADECIMAL_TO_CENTESIMAL);
				} else if (toSystem == EAngleType.RADIAN) {
					return Optional.of(value * HEXADECIMAL_TO_RADIAN);
				}
			} else if (fromSystem == EAngleType.RADIAN) {
				if (toSystem == EAngleType.RADIAN) {
					return Optional.of(value);
				} else if (toSystem == EAngleType.CENTESIMAL) {
					return Optional.of(value * RADIAN_TO_CENTESIMAL);
				} else if (toSystem == EAngleType.HEXADEDECIMAL) {
					return Optional.of(value * RADIAN_TO_HEXADECIMAL);
				}
			}
		}

		log.error("One of the parameters is null");
		return Optional.ofNullable(INVALID_ELEMENT);
	}

	/**
	 * Method that calculates the angle (P1, V, P2)
	 *
	 * @param vertex point V, the vertex of angle
	 * @param point1 point P1
	 * @param point2 point P2
	 * @return value of angle; could be null is certain conditions are not met; resulted angle is always >= 0
	 */
	public static Optional<Double> getAngleValue(Point vertex, Point point1, Point point2) {
		if (vertex != null
				&& point1 != null
				&& point2 != null
				&& vertex.getCoord() != null
				&& point1.getCoord() != null
				&& point2.getCoord() != null) {

			Optional<SimplifiedCoordinatesPolar> coordPolar1 = CoordinatesService.calculatesPolarCoordinates(vertex, point1);
			Optional<SimplifiedCoordinatesPolar> coordPolar2 = CoordinatesService.calculatesPolarCoordinates(vertex, point2);

			Double value1 = coordPolar1.isPresent() ? coordPolar1.get().getTheta() : INVALID_ELEMENT;
			Double value2 = coordPolar2.isPresent() ? coordPolar2.get().getTheta() : INVALID_ELEMENT;

			if (value1 != INVALID_ELEMENT && value2 != INVALID_ELEMENT) {
				return Optional.of(Math.abs(value1 - value2)); // angle >= 0
			}
			return Optional.empty();
		}

		log.error("Value of angle [p1, vertex, p2] could not be calculated. At least one of the parameters [p1, vertex, p2] is null");
		return Optional.empty();
	}

	/**
	 * Method that calculates the value of angle defined by (P1, V, P2) and units
	 *
	 * @param angle defined by its parameters
	 * @return value of angle; could be null is certain conditions are not met; resulted angle is always >= 0
	 */
	public static Optional<Double> getAngleValue(Angle angle) {
		return getAngleValue(angle.getVertex(), angle.getP1(), angle.getP2());
	}

	/**
	 * Method that calculates the value of opposite angle (P1, V, P2); ex: if angle type is HEXADECIMAL[0..360] and angle = 270 then opposite angle = 90
	 *
	 * @param vertex point V, the vertex of angle
	 * @param point1 point1 point P1
	 * @param point2 point2 point P2
	 * @return value of opposite angle; could be null is certain conditions are not met; resulted angle is always >= 0
	 */
	public static Optional<Double> getOppositeAngleValue(Point vertex, Point point1, Point point2) {
		Optional<Double> opt = getAngleValue(vertex, point1, point2);
		Units units = Environment.getInstance().getUnits();
		return Optional.ofNullable(opt.isPresent() ? units.getAngleType().getMaxNumberOfCircleDegrees() - opt.get() : null);
	}

	/**
	 * Method that calculates the value of opposite angle (P1, V, P2); ex: if angle type is HEXADECIMAL[0..360] and angle = 270 then opposite angle = 90
	 *
	 * @param angle the angle at which the opposite value is calculated
	 * @return value of opposite angle; could be null is certain conditions are not met; resulted angle is always >= 0
	 */
	public static Optional<Double> getOppositeAngleValue(Angle angle) {
		return getOppositeAngleValue(angle.getVertex(), angle.getP1(), angle.getP2());
	}

	public static double getOppositeAngleValue(double theta) {
		EAngleType angleType = Environment.getInstance().getUnits().getAngleType();
		double halfCircle = angleType.getMaxNumberOfCircleDegrees() / 2D;

		double antiTheta = theta + halfCircle;
		if (antiTheta >= halfCircle * 2D) antiTheta = theta - halfCircle;

		return antiTheta;
	}

	/**
	 * @param vertex point V, the vertex of angle
	 * @param p1     point P1
	 * @param p2     point P2
	 * @return angle name P1VP2 or empty string ""
	 */
	public static String getNameIfPossible(Point vertex, Point p1, Point p2) {
		if (vertex != null && p1 != null && p2 != null
				&& vertex.getName() != null && p1.getName() != null && p2.getName() != null)
			return p1.getName() + vertex.getName() + p2.getName();
		return EMPTY_STRING;
	}

	public static String getNameIfPossible(Angle angle) {
		Point vertex = angle.getVertex();
		Point p1 = angle.getP1();
		Point p2 = angle.getP2();

		return getNameIfPossible(vertex, p1, p2);
	}
}
