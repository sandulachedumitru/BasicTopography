package com.hardcodacii.service;

import com.hardcodacii.domain.*;
import com.hardcodacii.domain.builder.*;
import com.hardcodacii.environment.Environment;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * this class is not design to be instantiated
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class PointService {
	// this class is not designed to be instantiated
	private PointService() {
	}

	public static Optional<Point> transformPointFromCoordSystemToCoordSystem(Point point, Class<? extends Coordinates> toSystem) {
		if (point == null) {
			log.error("Point is null. The coordinates could not be calculated");
			return Optional.empty();
		}

		Class<? extends Coordinates> fromSystem = point.getCoord().getClass();
		Class<CoordinatesRectangular> coordRect = CoordinatesRectangular.class;
		Class<CoordinatesPolar> coordPolar = CoordinatesPolar.class;
		Point newPoint = null;

		if (fromSystem != null && toSystem != null) {
			if (fromSystem.equals(coordRect)) {
				if (toSystem.equals(coordRect)) {
					newPoint = point;
				} else if (toSystem.equals(coordPolar)) {
					newPoint = fromRectangularToPolarCoordinates(point);
				} else {
					log.error("Target coordinates system unknown: {}", toSystem);
				}
			} else if (fromSystem.equals(coordPolar)) {
				if (toSystem.equals(coordRect)) {
					newPoint = fromPolarToRectangularCoordinates(point);
				} else if (toSystem.equals(coordPolar)) {
					newPoint = point;
				} else {
					log.error("Target coordinates system unknown: {}", toSystem);
				}
			} else {
				log.error("Source coordinates system (fromSystem) unknown: {}", fromSystem);
			}
		}

		log.info("Reference point: {}", point);
		log.info("The new point: {}", newPoint);

		return Optional.ofNullable(newPoint);
	}

	private static Point fromPolarToRectangularCoordinates(Point point) {
		if (point == null) {
			log.error("Point is null. The rectangular coordinates could not be calculated");
			return null;
		}

		if (point.getCoord() instanceof CoordinatesPolar coord) {
			Double angle = coord.getAngle().getValue();
			Double distance = coord.getDistance().getValue();

			CoordinatesRectangular coordOrigin = CoordinatesRectangularBuilder.getBuilder().setEast(0).setNorth(0).setHeight(0).build();
			Point pointOrigin = PointBuilder.getBuilder().setCoord(coordOrigin).build();

			Optional<Point> pointOps = calculateNewPointFromKnownPoint(pointOrigin, angle, distance);
			return pointOps.orElse(null);
		} else {
			log.error("Point coordinates are not polar");
			return null;
		}
	}

	private static Point fromRectangularToPolarCoordinates(Point point) {
		if (point == null) {
			log.error("Point is null. The polar coordinates could not be calculated");
			return null;
		}

		if (point.getCoord() instanceof CoordinatesRectangular) {
			CoordinatesRectangular coordOrigin = CoordinatesRectangularBuilder.getBuilder().setEast(0).setNorth(0).setHeight(0).build();
			Point pointOrigin = PointBuilder.getBuilder().setCoord(coordOrigin).build();

			Optional<CoordinatesService.SimplifiedCoordinatesPolar> simplifiedCoordinatesPolarOpt = CoordinatesService.calculatesPolarCoordinates(point, pointOrigin);
			if (simplifiedCoordinatesPolarOpt.isPresent()) {
				CoordinatesService.SimplifiedCoordinatesPolar simplifiedCoordinatesPolar = simplifiedCoordinatesPolarOpt.get();
				Angle angle = AngleBuilder.getBuilder().setValue(simplifiedCoordinatesPolar.getTheta()).build();
				Distance distance = DistanceBuilder.getBuilder().setValue(simplifiedCoordinatesPolar.getDistance()).build();
				CoordinatesPolar polarCoord = CoordinatesPolarBuilder.getBuilder().setAngle(angle).setDistance(distance).build();
				point.setCoord(polarCoord);
				return point;
			} else {
				log.error("The polar coordinates could not be calculated");
				return null;
			}
		} else {
			log.error("Point coordinates are not rectangular");
			return null;
		}
	}

	public static Optional<Point> calculateNewPointFromKnownPoint(Point point, double theta, double appliedDistance) {
		if (point == null || point.getCoord() == null || (!(point.getCoord() instanceof CoordinatesRectangular))) {
			log.error("Point:null OR point's coordinates:null OR coordinates are not of type:CoordinatesRectangular. The polar coordinates could not be calculated");
			return Optional.empty();
		}

		double Np = ((CoordinatesRectangular) point.getCoord()).getNorth();
		double Ep = ((CoordinatesRectangular) point.getCoord()).getEast();
		double Hp = ((CoordinatesRectangular) point.getCoord()).getHeight();

		EAngleType angleTypeRadian = EAngleType.RADIAN;
		EAngleType angleType = Environment.getInstance().getUnits().getAngleType();

		double thetaRadian = theta * angleType.getFactorToChangeAngleToSystem(angleTypeRadian);

		double N = Np + appliedDistance * Math.cos(thetaRadian);
		double E = Ep + appliedDistance * Math.sin(thetaRadian);
		double H = Hp;

		CoordinatesRectangular coords = CoordinatesRectangularBuilder.getBuilder().setNorth(N).setEast(E).setHeight(H).build();

		return Optional.of(PointBuilder.getBuilder().setCoord(coords).setName("Perpendicular").build());
	}
}
