package com.hardcodacii.service;

import com.hardcodacii.domain.*;
import com.hardcodacii.domain.builder.CoordinatesRectangularBuilder;
import com.hardcodacii.domain.builder.PointBuilder;
import com.hardcodacii.environment.Environment;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

/**
 * this class is not designed to be instantiated
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class CoordinatesService {
	// this class is not designed to be instantiated
	private CoordinatesService() {
	}

	/**
	 * Calculates the bearing and distance (simplified polar coordinates) from P1 to P2 based on type of angle
	 *
	 * @param from point P1
	 * @param to   point P2
	 * @return distance and direction
	 */
	public static Optional<SimplifiedCoordinatesPolar> calculatesPolarCoordinates(Point from, Point to) {
		if (from != null && to != null && from.getCoord() != null && to.getCoord() != null) {

			SimplifiedCoordinatesPolar simplifiedCoordinatesPolar = null;

			if (from.getCoord() instanceof CoordinatesRectangular && to.getCoord() instanceof CoordinatesRectangular) {
				simplifiedCoordinatesPolar = calculatesPolarFromRectangularCoordinates(from, to);
			} else if (from.getCoord() instanceof CoordinatesPolar && to.getCoord() instanceof CoordinatesPolar) {
				simplifiedCoordinatesPolar = calculatesPolarFromPolarCoordinates(from, to);
			} else {
				log.error("FROM[{}] coordinates type != TO[{}] coordonates type", from.getCoord().getClass(), to.getCoord().getClass());
			}

			return Optional.ofNullable(simplifiedCoordinatesPolar);
		}

		log.info("from == null OR to == null OR from.getCoord() == null OR to.getCoord() == null");
		return Optional.empty();
	}

	// Calculates the bearing and distance (polar coordinates) from rectangular coordinates
	private static SimplifiedCoordinatesPolar calculatesPolarFromRectangularCoordinates(Point from, Point to) {
		double deltaNorth, deltaEast;
		double north1, east1;
		double north2, east2;
		double height1, height2;

		CoordinatesRectangular fromCoord = (CoordinatesRectangular) from.getCoord();
		CoordinatesRectangular toCoord = (CoordinatesRectangular) to.getCoord();

		if (fromCoord == null || toCoord == null) return null;

		north1 = fromCoord.getNorth();
		north2 = toCoord.getNorth();
		east1 = fromCoord.getEast();
		east2 = toCoord.getEast();
		height1 = fromCoord.getHeight();
		height2 = toCoord.getHeight();

		deltaNorth = north2 - north1;
		deltaEast = east2 - east1;

		// Calculate angle; this value will remain unchanged if FROM point == TO point => theta == 0 and distance == 0
		double theta = 0D, omega = 0D;

		EAngleType angleTypeRadian = EAngleType.RADIAN;
		Units units = Environment.getInstance().getUnits();
		double quadrant = units.getAngleType().getMaxNumberOfCircleDegrees() / 4;

		if ((deltaNorth > 0) && (deltaEast >= 0)) {
			omega = Math.atan(deltaEast / deltaNorth);
			theta = Math.abs(omega) * angleTypeRadian.getFactorToChangeAngleToSystem(units.getAngleType()) + 0 * quadrant;
		} else if ((deltaNorth <= 0) && (deltaEast > 0)) {
			omega = Math.atan(deltaNorth / deltaEast);
			theta = Math.abs(omega) * angleTypeRadian.getFactorToChangeAngleToSystem(units.getAngleType()) + 1 * quadrant;
		} else if ((deltaNorth < 0) && (deltaEast <= 0)) {
			omega = Math.atan(deltaEast / deltaNorth);
			theta = Math.abs(omega) * angleTypeRadian.getFactorToChangeAngleToSystem(units.getAngleType()) + 2 * quadrant;
		} else if ((deltaNorth >= 0) && (deltaEast < 0)) {
			omega = Math.atan(deltaNorth / deltaEast);
			theta = Math.abs(omega) * angleTypeRadian.getFactorToChangeAngleToSystem(units.getAngleType()) + 3 * quadrant;
		}

		// Calculate distance
		double distance = Math.sqrt(Math.pow(deltaNorth, 2) + Math.pow(deltaEast, 2));

		// create name
		String name = (from.getName() != null && to.getName() != null) ? from.getName() + to.getName() : EMPTY_STRING;

		// return
		SimplifiedCoordinatesPolar coord = new SimplifiedCoordinatesPolar();
		coord.distance = distance;
		coord.theta = theta;
		coord.name = name;

		return coord;
	}

	private static SimplifiedCoordinatesPolar calculatesPolarFromPolarCoordinates(Point from, Point to) {
		CoordinatesPolar coordFrom = (CoordinatesPolar) from.getCoord();
		CoordinatesPolar coordTo = (CoordinatesPolar) to.getCoord();

		double distanceFrom = coordFrom.getDistance().getValue();
		double thetaFrom = coordFrom.getAngle().getValue();
		Double thetaRadianFrom = thetaToRadian(thetaFrom);
		if (thetaRadianFrom == null) return null;

		double thetaTo = coordTo.getAngle().getValue();
		double distanceTo = coordTo.getDistance().getValue();
		Double thetaRadianTo = thetaToRadian(thetaTo);
		if (thetaRadianTo == null) return null;

		double eastFrom = distanceFrom * Math.sin(thetaRadianFrom);
		double northFrom = distanceFrom * Math.cos(thetaRadianFrom);

		double eastTo = distanceTo * Math.sin(thetaRadianTo);
		double northTo = distanceTo * Math.cos(thetaRadianTo);

		CoordinatesRectangular coordRectFrom = CoordinatesRectangularBuilder.getBuilder().setEast(eastFrom).setNorth(northFrom).build();
		CoordinatesRectangular coordRectTo = CoordinatesRectangularBuilder.getBuilder().setEast(eastTo).setNorth(northTo).build();
		Point fromRect = PointBuilder.getBuilder().setCoord(coordRectFrom).setName("P1").build();
		Point toRect = PointBuilder.getBuilder().setCoord(coordRectTo).setName("P2").build();

		return calculatesPolarFromRectangularCoordinates(fromRect, toRect);
	}

	private static Double thetaToRadian(double theta) {
		EAngleType angleType = Environment.getInstance().getUnits().getAngleType();
		Optional<Double> thetaRadianOps = AngleService.transformAngleFromSystemToSystem(angleType, EAngleType.RADIAN, theta);
		if (thetaRadianOps.isEmpty()) return null;
		else return thetaRadianOps.get();
	}

	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	public static class SimplifiedCoordinatesPolar {
		private double theta;
		private double distance;
		private String name;

		private SimplifiedCoordinatesPolar() {
		}
	}
}
