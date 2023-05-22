package com.hardcodacii.web.service.subservice;

import com.hardcodacii.domain.Point;
import com.hardcodacii.service.AngleService;
import com.hardcodacii.service.CoordinatesService;
import com.hardcodacii.service.PointService;
import com.hardcodacii.utils.Utils;
import com.hardcodacii.web.dto.PointsX3DTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.DISTANCE_TOLERANCE_COMPARISON;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 * See Market.dwg file for more info
 * CASE 1 : d1 > 0 && d2 > 0 && (d1 + d2 == c) && d1 != a && d2 != b)
 * CASE 2 : d1 < 0 && d2 > 0 && (d2 - |d1| == c) && d1 != a && d2 != b)
 * CASE 3 : d1 > 0 && d2 < 0 && (d1 - |d2| == c) && d1 != a && d2 != b)
 * CASE 4 : d1 == 0 && d2 == c && a != 0
 * CASE 5 : d1 == c && d2 == 0 && b != 0
 * CASE 6 : ((d1 == a && d2 == b) || (d1 == b && d2 == a)) && d1 != 0 && d2 != 0
 * CASE 7 : d1 < 0 && d2 > 0 && (d2 == c + |d1|)
 * CASE 8 : d1 > 0 && d2 < 0 && (d1 == c + |d2|)
 * CASE 9 : b == 0 && a == c
 * CASE 10 : a == 0 && b == c
 * CASE 11 : c == 0 && a == b
 * CASE 12 : a == 0 && b == 0 && c == 0
 */

@Slf4j
public class PerpendicularFromPointToLine {

	public static Optional<Point> getPerpendicularFromPointToLine(PointsX3DTO pointsX3DTO) {
		if (pointsX3DTO == null) {
			log.error("Cannot calculate the perpendicular from Point P3 to the Line P1P2 [pointsX3DTO is null]");
			return Optional.empty();
		}

		Point P1 = pointsX3DTO.getP1();
		Point P2 = pointsX3DTO.getP2();
		Point P = pointsX3DTO.getP3();

		double thetaP1P2, distanceP1P2;
		Optional<CoordinatesService.SimplifiedCoordinatesPolar> optP1P2 = CoordinatesService.calculatesPolarCoordinates(P1, P2);
		if (optP1P2.isPresent()) {
			thetaP1P2 = optP1P2.get().getTheta();
			distanceP1P2 = optP1P2.get().getDistance();
		} else return Optional.empty();

		double thetaP1P, distanceP1P;
		Optional<CoordinatesService.SimplifiedCoordinatesPolar> optP1P = CoordinatesService.calculatesPolarCoordinates(P1, P);
		if (optP1P.isPresent()) {
			thetaP1P = optP1P.get().getTheta();
			distanceP1P = optP1P.get().getDistance();
		} else return Optional.empty();

		double thetaP2P, distanceP2P;
		Optional<CoordinatesService.SimplifiedCoordinatesPolar> optP2P = CoordinatesService.calculatesPolarCoordinates(P2, P);
		if (optP2P.isPresent()) {
			thetaP2P = optP2P.get().getTheta();
			distanceP2P = optP2P.get().getDistance();
		} else return Optional.empty();

		double a = distanceP2P;
		double b = distanceP1P;
		double c = distanceP1P2;

		if (areEqual(a, 0D)) a = 0;
		if (areEqual(b, 0D)) b = 0;
		if (areEqual(c, 0D)) c = 0;

		if (a < 0 || b < 0 || c < 0) {
			log.error("Distance/Side of a triangle cannot be negative: a={}, b={}, c={}", a, b, c);
			return Optional.empty();
		}

		// it's important that distance (the side of the triangle that is opposite the angle C)  "c" != 0 because of the formula for "d1"
		if (c == 0) {
			// CASE 12 : a == 0 && b == 0 && c == 0
			if (a == 0 && b == 0) {
				log.error("CASE 12 : Cannot calculate the perpendicular to the line. P1, P2, P3 have same coordinates");
				return Optional.empty();
			}
			// CASE 11 : c == 0 && a == b
			else if (areEqual(a, b)) {
				log.error("CASE 11 : Cannot calculate the perpendicular to the line. P1 and P2 have same coordinates");
				return Optional.empty();
			}

			log.error("Cannot calculate the perpendicular to the line. Point P1 and P2 have the same coordinates.");
			return Optional.empty();
		}

		double d1 = (a * a - b * b + c * c) / (2 * c);
		double d2 = c - d1;

		if (areEqual(d1, 0D)) d1 = 0;
		if (areEqual(d2, 0D)) d2 = 0;

		// CASE 1 : d1 > 0 && d2 > 0 && (d1 + d2 == c) && d1 != a && d2 != b)
		if (d1 > 0 && d2 > 0 && areEqual(d1 + d2, c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 1 : The perpendicular from point P is inside of the segment P1P2");
			return PointService.calculateNewPointFromKnownPoint(P1, thetaP1P2, d2);
		}
		// CASE 2 : d1 < 0 && d2 > 0 && (d2 - |d1| == c) && d1 != a && d2 != b)
		else if (d1 < 0 && d2 > 0 && areEqual(d2 - Math.abs(d1), c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 2 : The perpendicular from point P is outside of the segment P1P2, near P2");
			return PointService.calculateNewPointFromKnownPoint(P1, thetaP1P2, d2);
		}
		// CASE 3 :  d1 > 0 && d2 < 0 && (d1 - |d2| == c) && d1 != a && d2 != b)
		else if (d1 > 0 && d2 < 0 && areEqual(d1 - Math.abs(d2), c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 3 : The perpendicular from point P is outside of the segment P1P2, near P1");
			return PointService.calculateNewPointFromKnownPoint(P1, AngleService.getOppositeAngleValue(thetaP1P2), Math.abs(d2));
		}
		// CASE 4 : d1 == 0 && d2 == c && a != 0
		else if (d1 == 0 && areEqual(d2, c) && a != 0) {
			log.info("CASE 4 : The perpendicular from the point P is P2. It's a right-angle triangle. ");
			return Optional.ofNullable(P2);
		}
		// CASE 5 : d1 == c && d2 == 0 && b != 0
		else if (areEqual(d1, c) && d2 == 0 && b != 0) {
			log.info("CASE 5 : The perpendicular from the point P is P1. It's a right-angle triangle. ");
			return Optional.of(P1);
		}
		// CASE 6 : ((d1 == a && d2 == b) || (d1 == b && d2 == a)) && d1 != 0 && d2 != 0
		else if (((areEqual(d1, a) && areEqual(d2, b)) || (areEqual(d1, b) && areEqual(d2, a))) && d1 != 0 && d2 != 0) {
			log.error("CASE 6 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, inside P1P2");
			return Optional.empty();
		}
		// CASE 7 : d1 < 0 && d2 > 0 && (d2 == c + |d1|)
		else if (d1 < 0 && d2 > 0 && areEqual(d2, c + Math.abs(d1))) {
			log.error("CASE 7 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, but outside, near P2");
			return Optional.empty();
		}
		// CASE 8 : d1 > 0 && d2 < 0 && (d1 == c + |d2|)
		else if (d1 > 0 && d2 < 0 && areEqual(d1, c + Math.abs(d2))) {
			log.error("CASE 8 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, but outside, near P1");
			return Optional.empty();
		}
		// CASE 9 : b == 0 && a == c
		else if (b == 0 && areEqual(a, c)) {
			log.error("CASE 9 : Cannot calculate the perpendicular to the line. The point P is P1");
			return Optional.empty();
		}
		// CASE 10 : a == 0 && b == c
		else if (a == 0 && areEqual(b, c)) {
			log.error("CASE 10 : Cannot calculate the perpendicular to the line. The point P is P2");
			return Optional.empty();
		}

		log.error("Impossible case : Cannot calculate the perpendicular to the line");
		return Optional.empty();
	}

	private static boolean areEqual(double val1, double val2) {
		return Utils.areEqual(val1, val2, DISTANCE_TOLERANCE_COMPARISON);
	}
}
