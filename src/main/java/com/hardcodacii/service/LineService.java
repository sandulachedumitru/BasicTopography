package com.hardcodacii.service;

import com.hardcodacii.domain.CoordinatesPolar;
import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.Line;
import com.hardcodacii.domain.Point;
import com.hardcodacii.environment.Environment;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import com.hardcodacii.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.ANGLE_TOLERANCE_COMPARISON;
import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

@Slf4j
public final class LineService {
	// this class is not designed to be instantiated
	private LineService() {
	}

	/**
	 * Check if point P is on the Line
	 *
	 * @param line  Line object
	 * @param point Point object
	 * @return true if point is on the line; false otherwise
	 */
	public static Optional<Boolean> isPointOnLine(Line line, Point point) {
		if (line != null && point != null) {

			Boolean isPointOnLine = null;
			Point from = line.getP1();
			Point to = line.getP2();

			if (from.getCoord() instanceof CoordinatesRectangular && to.getCoord() instanceof CoordinatesRectangular) {
				isPointOnLine = isPointOnLineCoordsRectangular(line, point);
			} else if (from.getCoord() instanceof CoordinatesPolar && to.getCoord() instanceof CoordinatesPolar) {
				isPointOnLine = isPointOnLineCoordsPolar(line, point);
			} else {
				log.error("FROM[{}] coordinates type != TO[{}] coordonates type", from.getCoord().getClass(), to.getCoord().getClass());
			}

			return Optional.ofNullable(isPointOnLine);
		}

		log.info("from == null OR to == null OR from.getCoord() == null OR to.getCoord() == null");
		return Optional.ofNullable(null);
	}

	private static boolean isPointOnLineCoordsRectangular(Line line, Point point) {
		if (point == null || line == null) {
			if (point == null) log.error("Point is {}", point);
			if (line == null) log.error("Line is {}", line);

			return false;
		}

		Point p1 = line.getP1();
		Point p2 = line.getP2();
		double half = 2D;

		CoordinatesPolar coord = line.getPolarCoordinates();
		double thetaP1P2 = coord.getAngle().getValue();
		double thetaP1P = 0D;
		double thetaP2P = 0D;

		Optional<SimplifiedCoordinatesPolar> opt1 = CoordinatesService.calculatesPolarCoordinates(p1, point);
		if (opt1.isPresent()) thetaP1P = opt1.get().getTheta();
		Optional<SimplifiedCoordinatesPolar> opt2 = CoordinatesService.calculatesPolarCoordinates(p2, point);
		if (opt2.isPresent()) thetaP2P = opt2.get().getTheta();

		double halfCircle = Environment.getInstance().getUnits().getAngleType().getMaxNumberOfCircleDegrees() / half; // ex: [CEN] 200/2; [HEX] 180/2, [RAD] PI - angleValue

		double antiThetaP1P = thetaP1P + halfCircle >= halfCircle * half ? thetaP1P - halfCircle : thetaP1P + halfCircle;
		double antiThetaP2P = thetaP2P + halfCircle >= halfCircle * half ? thetaP2P - halfCircle : thetaP2P + halfCircle;

		if (
				(Utils.areEqual(thetaP1P2, thetaP1P, ANGLE_TOLERANCE_COMPARISON) || Utils.areEqual(thetaP1P2, antiThetaP1P, ANGLE_TOLERANCE_COMPARISON))
						&&
						(Utils.areEqual(thetaP1P2, thetaP2P, ANGLE_TOLERANCE_COMPARISON) || Utils.areEqual(thetaP1P2, antiThetaP2P, ANGLE_TOLERANCE_COMPARISON))
		) {
			log.info("Point[{}] is on Line[{}]", point, line);
			return true;
		}

		log.info("Point[{}] isn't on Line[{}]", point, line);
		return false;
	}

	private static boolean isPointOnLineCoordsPolar(Line line, Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Create name from P1 and P2 of line
	 *
	 * @param line P1P2 line
	 * @return concatenates name of P1 and name of P2, otherwise empty string
	 */
	public static String getNameIfPossible(Line line) {
		if (line != null
				&& line.getP1() != null && line.getP2() != null
				&& line.getP1().getName() != null && line.getP2().getName() != null)
			return line.getP1().getName() + line.getP2().getName();
		return EMPTY_STRING;
	}

	public static String getNameIfPossible(Point p1, Point p2) {
		if (p1 != null && p2 != null
				&& p1.getName() != null && p2.getName() != null)
			return p1.getName() + p2.getName();
		return EMPTY_STRING;
	}
}
