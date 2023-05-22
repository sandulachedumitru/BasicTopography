package com.hardcodacii.utils;

import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.builder.CoordinatesRectangularBuilder;
import com.hardcodacii.domain.builder.PointBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class ParabolaTest {
	public static void main(String[] args) {
		log.info("*******************************************************");
		log.info("* Prepare set[1] of points to create the PARABOLA ... *");
		log.info("*******************************************************");

		CoordinatesRectangular coordA1 = CoordinatesRectangularBuilder.getBuilder().setEast(-3.54).setNorth(21.42).build();
		Point A1 = PointBuilder.getBuilder().setCoord(coordA1).setName("A1").build();
		System.out.println();

		CoordinatesRectangular coordB1 = CoordinatesRectangularBuilder.getBuilder().setEast(10.04).setNorth(1.93).build();
		Point B1 = PointBuilder.getBuilder().setCoord(coordB1).setName("B1").build();
		System.out.println();

		CoordinatesRectangular coordC1 = CoordinatesRectangularBuilder.getBuilder().setEast(-8.08).setNorth(10.35).build();
		Point C1 = PointBuilder.getBuilder().setCoord(coordC1).setName("C1").build();
		System.out.println();

		CoordinatesRectangular coordD1 = CoordinatesRectangularBuilder.getBuilder().setEast(12.000D).setNorth(0.020D).build();
		Point D1 = PointBuilder.getBuilder().setCoord(coordD1).setName("D1").build();
		System.out.println();

		calculateParabolaProperties(A1, B1, C1, D1);

		log.info("*******************************************************");
		log.info("* Prepare set[2] of points to create the PARABOLA ... *");
		log.info("*******************************************************");

		CoordinatesRectangular coordA2 = CoordinatesRectangularBuilder.getBuilder().setEast(-3.54D).setNorth(21.42D).build();
		Point A2 = PointBuilder.getBuilder().setCoord(coordA2).setName("A2").build();
		System.out.println();

		CoordinatesRectangular coordB2 = CoordinatesRectangularBuilder.getBuilder().setEast(10.04D).setNorth(1.93D).build();
		Point B2 = PointBuilder.getBuilder().setCoord(coordB2).setName("B2").build();
		System.out.println();

		CoordinatesRectangular coordC2 = CoordinatesRectangularBuilder.getBuilder().setEast(-8.08D).setNorth(10.35D).build();
		Point C2 = PointBuilder.getBuilder().setCoord(coordC2).setName("C2").build();
		System.out.println();

		CoordinatesRectangular coordD2 = CoordinatesRectangularBuilder.getBuilder().setEast(-9.68D).setNorth(30.22D).build();
		Point D2 = PointBuilder.getBuilder().setCoord(coordD2).setName("D2").build();
		System.out.println();

		calculateParabolaProperties(A2, B2, C2, D2);

		log.info("*******************************************************");
		log.info("* Prepare set[3] of points to create the PARABOLA ... *");
		log.info("*******************************************************");

		CoordinatesRectangular coordA3 = CoordinatesRectangularBuilder.getBuilder().setEast(-18.9171).setNorth(22.8927).build();
		Point A3 = PointBuilder.getBuilder().setCoord(coordA3).setName("A3").build();
		System.out.println();

		CoordinatesRectangular coordB3 = CoordinatesRectangularBuilder.getBuilder().setEast(23.3298).setNorth(22.8927).build();
		Point B3 = PointBuilder.getBuilder().setCoord(coordB3).setName("B3").build();
		System.out.println();

		CoordinatesRectangular coordC3 = CoordinatesRectangularBuilder.getBuilder().setEast(2.2063).setNorth(40.3530).build();
		Point C3 = PointBuilder.getBuilder().setCoord(coordC3).setName("C3").build();
		System.out.println();

		CoordinatesRectangular coordD3 = CoordinatesRectangularBuilder.getBuilder().setEast(33.4520).setNorth(1.0844).build();
		Point D3 = PointBuilder.getBuilder().setCoord(coordD3).setName("D3").build();
		System.out.println();

		calculateParabolaProperties(A3, B3, C3, D3);
	}

	private static void calculateParabolaProperties(Point P1, Point P2, Point P3, Point P4) {
		double east1 = ((CoordinatesRectangular) P1.getCoord()).getEast();
		double north1 = ((CoordinatesRectangular) P1.getCoord()).getNorth();

		double east2 = ((CoordinatesRectangular) P2.getCoord()).getEast();
		double north2 = ((CoordinatesRectangular) P2.getCoord()).getNorth();

		double east3 = ((CoordinatesRectangular) P3.getCoord()).getEast();
		double north3 = ((CoordinatesRectangular) P3.getCoord()).getNorth();

		double east4 = ((CoordinatesRectangular) P4.getCoord()).getEast();
		double north4 = ((CoordinatesRectangular) P4.getCoord()).getNorth();

		log.info("----------------------------------------------------------------------------------------------");
		log.info("|   CALCULATE a, b, c COEFFICIENTS from parabola Standard Form equation (y = axx + bx + c)   |");
		log.info("----------------------------------------------------------------------------------------------");
		double a = (east1 * (north3 - north2) + east2 * (north1 - north3) + east3 * (north2 - north1)) / ((east1 - east2) * (east1 - east3) * (east2 - east3));
		double b = (north2 - north1) / (east2 - east1) - a * (east1 + east2);
		double c = north1 - a * east1 * east1 - b * east1;

		if (a == 0) return;

		log.info("VerticalParabola equation: y = ({}}xx + ({})x + ({})", format(a), format(b), format(c));
		System.out.println();

		log.info("-----------------------------------------------------------------------------------------");
		log.info("|   CALCULATE h, k COEFFICIENTS from parabola Vertex Form equation (y = a(x-h)^2 + k)   |");
		log.info("-----------------------------------------------------------------------------------------");
		double h = (-b) / (2 * a); // axis of symmetry: x = h
		double k = (4 * a * c - b * b) / (4 * a);
		double p = 1 / (4 * a);

		// Focus of the parabola: F(h, k + p)
		double eastF = h;
		double northF = k + p;

		// Vertex of the parabola: V(h, k)
		double eastV = h;
		double northV = k;

		// VerticalParabola opens: upward / downward
		String open = a > 0 ? "upward" : "downward";

		// directrix: y = k - p
		double directrix = k - p;

		// Latus rectum L = 4p
		double latusRectum = Math.abs(4 * p);

		// OUTSIDE = -1, INSIDE = 1, ON_PARABOLA = 0
		int position = getPointPositionRelatedWithParabola(P4, a, b, c);

		log.info("VerticalParabola equation: y = ({})(x-({}))^2 + ({})", format(a), format(h), format(k));
		System.out.println();

		log.info("VerticalParabola open {}", open);
		log.info("p = {}", format(p));
		log.info("Directrix: \ty = k - p\ty = {}", format(directrix));
		log.info("Axis of symmetry\tx = h\tx = {}", format(h));
		log.info("Latus rectum\tL = 4p\tL = {}", format(latusRectum));
		log.info("Focus of the parabola: F(h, k + p)\tF({}, {})", format(eastF), format(northF));
		log.info("Vertex of the parabola: V(h, k)\tV({}, {})", format(eastV), format(northV));
		log.info("xyIntercepts: {}", xyIntercepts(a, b, c));
		log.info("Position of P related with parabola: {}", position);
		System.out.println();
	}

	private static double parabolaGeneralFormQuadraticFunction(double x, double a, double b, double c) {
		return a * x * x + b * x + c;
	}

	private static double parabolaStandardFormQuadraticFunction(double x, double a, double h, double k) {
		return a * (x - h) * (x - h) + k;
	}

	private static Integer getPointPositionRelatedWithParabola(Point P, double a, double b, double c) {
		int OUTSIDE = -1, INSIDE = 1, ON_PARABOLA = 0;
		double NorthP = ((CoordinatesRectangular) P.getCoord()).getNorth(); // Y axis
		double EastP = ((CoordinatesRectangular) P.getCoord()).getEast(); // x axis

		// n = NorthP,  m = tan(0) = 0 ; line (y = mx + n) which intersects parabola is parallel to axis O-East (OX) for rectangular parabola (y = axx + bx + c) which is opens UpWard / DownWard
		double n = NorthP;
		double m = 0;

		SqrtResponse response = rectangularParabolaAndLineIntersection(m, n, a, b, c);

		if (response.getX1() != null && response.getX2() != null) {
			double solutionX1 = Math.abs(response.getX1() - response.getAxis());
			double solutionX2 = Math.abs(response.getX2() - response.getAxis());
			if (solutionX1 != solutionX2) return null; // TODO implements areEquals
		}

		if (response.getDelta() > 0) {
			double parabolaAxis = -b / (2 * a);
			double d1 = Math.abs(parabolaAxis - response.getX1()); // distance from VerticalParabola Axis (parallel with OX = O-East) to Line Intersection
			double d2 = Math.abs(parabolaAxis - EastP); // distance from VerticalParabola Axis (parallel with OX = O-East) to Px = EastP
			double delatD1D2 = d2 - d1;

			if (delatD1D2 > 0) return OUTSIDE;
			else if (delatD1D2 == 0) return ON_PARABOLA;
			else return INSIDE;
		} else if (response.getDelta() == 0) {
			double parabolaAxis = -b / (2 * a);
			double d1 = 0; // distance from VerticalParabola Axis (parallel with OX = O-East) to Line Intersection
			double d2 = Math.abs(parabolaAxis - EastP); // distance from VerticalParabola Axis (parallel with OX = O-East) to Px = EastP
			double deltaD1D2 = d2 - d1;

			if (deltaD1D2 > 0) return OUTSIDE;
			else if (deltaD1D2 == 0) return ON_PARABOLA;
			else return null; // not possible
		} else {
			return OUTSIDE;
		}
	}

	private static SqrtResponse rectangularParabolaAndLineIntersection(double m, double n, double a, double b, double c) {
		SqrtResponse response = new SqrtResponse();
		double x1, x2, y1, y2;
		double delta = (b - m) * (b - m) - 4 * a * (c - n);
		double axis = (-b + m) / (2 * a);

		if (delta > 0) {
			x1 = (-b + m + Math.sqrt(delta)) / (2 * a);
			x2 = (-b + m - Math.sqrt(delta)) / (2 * a);
		} else if (delta == 0) { // TODO implements areEquals
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

	private static ParabolaInterceptsParameters xyIntercepts(double a, double b, double c) {
		double x1, x2, y;

		// x-intercepts
		double delta = b * b - 4 * a * c;
		double axis = -b / (2 * a);
		if (delta > 0) {
			x1 = (-b + Math.sqrt(delta)) / (2 * a);
			x2 = (-b - Math.sqrt(delta)) / (2 * a);
		} else if (delta == 0) { // TODO implements areEquals
			x1 = x2 = axis;
		} else {
			// x1, x2 not Real numbers; complex numbers; parabola == null
			return null;
		}

		// y-intercepts
		y = parabolaGeneralFormQuadraticFunction(0, a, b, c);

		ParabolaInterceptsParameters parabola = new ParabolaInterceptsParameters();
		parabola.setX1Intercepts(x1);
		parabola.setX2Intercepts(x2);
		parabola.setYIntercepts(y);
		parabola.setAxisSymmetry(axis);

		return parabola;
	}

	private static double format(Double val) {
		int precision = 4;
		return Utils.roundAndFormat(val, precision);
	}

	@Getter
	@Setter
	@ToString
	private static class SqrtResponse {
		private Double x1, x2, y1, y2, axis, delta;
	}

	@Getter
	@Setter
	@ToString
	private static class ParabolaInterceptsParameters {
		private Double x1Intercepts;
		private Double x2Intercepts;
		private Double yIntercepts;
		private Double axisSymmetry;
	}

}
