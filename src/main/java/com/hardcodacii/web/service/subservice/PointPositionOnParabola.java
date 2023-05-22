package com.hardcodacii.web.service.subservice;

import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.Parabola;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.VerticalParabola;
import com.hardcodacii.domain.builder.VerticalParabolaBuilder;
import com.hardcodacii.service.PointService;
import com.hardcodacii.utils.Utils;
import com.hardcodacii.web.dto.PointsX3DTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.DISTANCE_TOLERANCE_COMPARISON;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class PointPositionOnParabola {
	public static Optional<EPointParabolaPosition> checkPointPositionOnParabola(PointsX3DTO dto, Point P) {
		if (dto == null || dto.getP1() == null || dto.getP2() == null || dto.getP3() == null || P == null) {
			log.error("Checking point position relative with parabola: FAILED [dto/P1/P2/P3/P: null]");
			return Optional.empty();
		}

		Optional<VerticalParabola> parabolaOpt = createVerticalParabola(dto);
		if (parabolaOpt.isPresent()) {
			VerticalParabola parabola = parabolaOpt.get();
			return checkPointPositionRelativeToVerticalParabola(parabola, P);
		}
		return Optional.empty();
	}

	public static Optional<EPointParabolaPosition> checkPointPositionOnParabola(VerticalParabola parabola, Point P) {
		if (parabola == null || P == null) {
			log.error("Checking point position relative with parabola: FAILED [parabola/P: null]");
			return Optional.empty();
		}

		return checkPointPositionRelativeToVerticalParabola(parabola, P);
	}

	public static Optional<VerticalParabola> createVerticalParabola(PointsX3DTO dto) {
		if (dto == null || dto.getP1() == null || dto.getP2() == null || dto.getP3() == null) {
			log.error("Checking point position relative with parabola: FAILED [dto/P1/P2/P3: null]");
			return Optional.empty();
		}

		return VerticalParabolaBuilder.getBuilder().setP1(dto.getP1()).setP2(dto.getP2()).setP3(dto.getP3()).build();
	}

	private static Optional<EPointParabolaPosition> checkPointPositionRelativeToVerticalParabola(Parabola parabola, Point point) {
		log.info("Getting the position of point relative with parabola...");

		if (!(parabola instanceof VerticalParabola) || point == null) {
			log.error("Getting the position of point relative with parabola: FAILED [parabola or/and point: null]");
			return Optional.empty();
		}

		Optional<Point> pointOpt = PointService.transformPointFromCoordSystemToCoordSystem(point, CoordinatesRectangular.class);
		if (pointOpt.isPresent()) {
			Point P = pointOpt.get();

			EPointParabolaPosition OUTSIDE = EPointParabolaPosition.OUTSIDE, INSIDE = EPointParabolaPosition.INSIDE, ON_PARABOLA = EPointParabolaPosition.ON_PARABOLA;

			VerticalParabola.ParabolaQuadraticFunctionGeneralForm generalForm = ((VerticalParabola) parabola).getGeneralForm();
			double a = generalForm.getA();
			double b = generalForm.getB();
			double c = generalForm.getC();
			if (areEqual(a, 0D)) return Optional.empty();

			CoordinatesRectangular ccordRect = (CoordinatesRectangular) P.getCoord();
			double NorthP = ccordRect.getNorth(); // Y axis
			double EastP = ccordRect.getEast(); // x axis

			// n = NorthP,  m = tan(0) = 0 ; line (y = mx + n) which intersects parabola is parallel to axis O-East (OX) for rectangular parabola (y = axx + bx + c) which is opens UpWard / DownWard
			double n = NorthP;
			double m = 0;

			SqrtResponse response = rectangularParabolaAndLineIntersection(m, n, a, b, c);

			if (response.getX1() != null && response.getX2() != null) {
				double solutionX1 = Math.abs(response.getX1() - response.getAxis());
				double solutionX2 = Math.abs(response.getX2() - response.getAxis());
				if (!areEqual(solutionX1, solutionX2)) return Optional.empty();
			}

			if (response.getDelta() > 0) {
				double parabolaAxis = -b / (2 * a);
				double d1 = Math.abs(parabolaAxis - response.getX1()); // distance from VerticalParabola Axis (parallel with OX = O-East) to Line Intersection
				double d2 = Math.abs(parabolaAxis - EastP); // distance from VerticalParabola Axis (parallel with OX = O-East) to Px = EastP
				double delatD1D2 = d2 - d1;

				if (format(delatD1D2) > 0D) return Optional.of(OUTSIDE);
				else if (areEqual(delatD1D2, 0D)) return Optional.of(ON_PARABOLA);
				else return Optional.of(INSIDE);
			} else if (areEqual(response.getDelta(), 0D)) {
				double parabolaAxis = -b / (2 * a);
				double d1 = 0; // distance from VerticalParabola Axis (parallel with OX = O-East) to Line Intersection
				double d2 = Math.abs(parabolaAxis - EastP); // distance from VerticalParabola Axis (parallel with OX = O-East) to Px = EastP
				double deltaD1D2 = d2 - d1;

				if (format(deltaD1D2) > 0) return Optional.of(OUTSIDE);
				else if (areEqual(deltaD1D2, 0D)) return Optional.of(ON_PARABOLA);
				else return Optional.empty(); // not possible
			} else {
				return Optional.of(OUTSIDE);
			}
		} else {
			log.error("Getting the position of point relative with parabola: FAILED [unknown point coordinates: {}]", point.getCoord().getClass());
			return Optional.empty();
		}
	}

	private static SqrtResponse rectangularParabolaAndLineIntersection(double m, double n, double a, double b, double c) {
		SqrtResponse response = new SqrtResponse();
		double x1, x2, y1, y2;
		double delta = (b - m) * (b - m) - 4 * a * (c - n);
		double axis = (-b + m) / (2 * a);

		if (format(delta) > 0) {
			x1 = (-b + m + Math.sqrt(delta)) / (2 * a);
			x2 = (-b + m - Math.sqrt(delta)) / (2 * a);
		} else if (areEqual(delta, 0D)) {
			x1 = x2 = axis;
		} else {
			// x1, x2 not Real numbers; complex numbers; parabola == null
			response.setAxis(axis);
			response.setDelta(delta);
			return response;
		}

		y1 = m * x1 + n;
		y2 = m * x2 + n;

		response.setX1(x1);
		response.setX2(x2);
		response.setY1(y1);
		response.setY2(y2);
		response.setAxis(axis);
		response.setDelta(delta);

		return response;
	}

	private static double format(Double val) {
		int precision = 4;
		return Utils.roundAndFormat(val, precision);
	}

	private static boolean areEqual(double val1, double val2) {
		return Utils.areEqual(val1, val2, DISTANCE_TOLERANCE_COMPARISON);
	}

	public enum EPointParabolaPosition {
		OUTSIDE(-1),
		ON_PARABOLA(0),
		INSIDE(1);

		private final int position;

		EPointParabolaPosition(int position) {
			this.position = position;
		}

		public int getPosition() {
			return position;
		}
	}

	@Getter
	@Setter
	@ToString
	private static class SqrtResponse {
		private Double x1, x2, y1, y2, axis, delta;
	}
}
