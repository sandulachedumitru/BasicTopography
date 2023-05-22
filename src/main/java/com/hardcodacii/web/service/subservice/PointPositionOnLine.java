package com.hardcodacii.web.service.subservice;

import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.Units;
import com.hardcodacii.environment.Environment;
import com.hardcodacii.service.CoordinatesService;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import com.hardcodacii.utils.Utils;
import com.hardcodacii.web.dto.PointsX3DTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.ANGLE_TOLERANCE_COMPARISON;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class PointPositionOnLine {
	public static Optional<Integer> checkPointPositionOnLine(PointsX3DTO dto) {
		if (dto == null) {
			log.error("Error processing Point P3 and Line P1P2");
			return Optional.empty();
		}

		Point p1 = dto.getP1();
		Point p2 = dto.getP2();
		Point p3 = dto.getP3();

		Optional<SimplifiedCoordinatesPolar> cp_p1_p2 = CoordinatesService.calculatesPolarCoordinates(p1, p2);
		Optional<SimplifiedCoordinatesPolar> cp_p1_p3 = CoordinatesService.calculatesPolarCoordinates(p1, p3);

		int LEFT = -1, RIGHT = 1, ON_LINE = 0;
		double half = 2D, quadrant = 4D;

		Units units = Environment.getInstance().getUnits();
		double halfCircle = units.getAngleType().getMaxNumberOfCircleDegrees() / half;
		double quadCircle = units.getAngleType().getMaxNumberOfCircleDegrees() / quadrant;

		Integer position = null;

		if (cp_p1_p2.isPresent() && cp_p1_p3.isPresent()) {
			double thetaP1P2 = cp_p1_p2.get().getTheta();
			double thetaP1P3 = cp_p1_p3.get().getTheta();
			log.info("thetaP1P2:[{}],\tthetaP1P3:[{}]", thetaP1P2, thetaP1P3);

			double rotatedThetaP1P3 = thetaP1P3 - thetaP1P2;
			if (rotatedThetaP1P3 < 0) rotatedThetaP1P3 += quadrant * quadCircle;

			// check if P3 is on P!P2 line
			if (Utils.areEqual(rotatedThetaP1P3, quadCircle * 0D, ANGLE_TOLERANCE_COMPARISON) ||
					Utils.areEqual(rotatedThetaP1P3, quadCircle * half, ANGLE_TOLERANCE_COMPARISON)) {
				position = ON_LINE;
				// check if P3 is on P1P2 right
			} else if ((0 * quadCircle < rotatedThetaP1P3) && (rotatedThetaP1P3 < half * quadCircle)) {
				position = RIGHT;
				// check if P3 is on P1P2 left
			} else if ((half * quadCircle < rotatedThetaP1P3) && (rotatedThetaP1P3 < quadrant * quadCircle)) {
				position = LEFT;
			} else {
				log.info("Imposible");
			}
		}

		if (position == LEFT) log.info("Point P3=[{}] is in LEFT of P1P2", p3);
		else if (position == ON_LINE) log.info("Point P3=[{}] is on the line P1P2", p3);
		else if (position == RIGHT) log.info("Point P3[{}] is in RIGHT of P1P2", p3);
		else log.error("Error processing Point P3=[{}] and Line P1P2", p3);
		return Optional.ofNullable(position);
	}
}
