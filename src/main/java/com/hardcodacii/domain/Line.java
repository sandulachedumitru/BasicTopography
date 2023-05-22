package com.hardcodacii.domain;

import com.hardcodacii.domain.builder.*;
import com.hardcodacii.service.PointService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Base class for linear representation
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Line extends GeometricElements {
	// fields exposed by Line / Direction
	private Point p1, p2;
	private LineQuadraticFunctionGeneralForm generalForm;

	// Constructors
	public Line(Point p1, Point p2, String name) {
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
		calculateLineQuadraticFunctionGeneralFormAndFormatP1AndP2(p1, p2);
	}

	public Line(LineQuadraticFunctionGeneralForm generalForm, String name) {
		this.name = name;
		this.generalForm = generalForm;
		calculateP1AndP2BasedOnQuadraticFunction(generalForm);
	}

	// metthods
	private void calculateLineQuadraticFunctionGeneralFormAndFormatP1AndP2(Point p1, Point p2) {
		Optional<Point> p1Opt = PointService.transformPointFromCoordSystemToCoordSystem(p1, CoordinatesRectangular.class);
		Optional<Point> p2Opt = PointService.transformPointFromCoordSystemToCoordSystem(p2, CoordinatesRectangular.class);

		if (p1Opt.isPresent() && p2Opt.isPresent()) {
			this.p1 = p1Opt.get();
			this.p2 = p2Opt.get();

			CoordinatesRectangular coord = (CoordinatesRectangular) this.p1.getCoord();
			double east1 = coord.getEast();
			double north1 = coord.getNorth();
			double east2 = coord.getEast();
			double north2 = coord.getNorth();

			double m = (north2 - north1) / (east2 - east1); // m = (N2 - N1) / (E2 - E1); m = tan(alfa) = (y2 - y1) / (x2 - x1)
			double n = north1 - m * east1; // n = N1 - m * E1 = N2 - m * E2

			generalForm = new LineQuadraticFunctionGeneralForm(m, n);
		}
	}

	private void calculateP1AndP2BasedOnQuadraticFunction(LineQuadraticFunctionGeneralForm generalForm) {
		if (generalForm != null && generalForm.getM() != null && generalForm.getN() != null) {
			double east1 = 0;
			double north1 = 0;
			double east2 = east1 + 1;
			double north2 = generalForm.getNorthBasedOnEast(east2);

			CoordinatesRectangular coordRect1 = CoordinatesRectangularBuilder.getBuilder().setEast(east1).setNorth(north1).build();
			p1 = PointBuilder.getBuilder().setCoord(coordRect1).build();

			CoordinatesRectangular coordRect2 = CoordinatesRectangularBuilder.getBuilder().setEast(east2).setNorth(north2).build();
			p2 = PointBuilder.getBuilder().setCoord(coordRect2).build();
		}
	}

	public CoordinatesPolar getPolarCoordinates() {
		Angle angleP2P1P = AngleBuilder.getBuilder()
				.setName(name)
				.setVertex(p1)
				.setPoint1(p1)
				.setPoint2(p2)
				.build();
		Distance distanceP1P2 = DistanceBuilder.getBuilder()
				.setPointFrom(p1)
				.setPointTo(p2)
				.setName(name)
				.build();

		CoordinatesPolar coord = CoordinatesPolarBuilder.getBuilder().setAngle(angleP2P1P).setDistance(distanceP1P2).build();
		log.info("Get line's polar coordinate: {}", coord);
		return coord;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
		calculateLineQuadraticFunctionGeneralFormAndFormatP1AndP2(p1, p2);
	}

	public void setP2(Point p2) {
		this.p2 = p2;
		calculateLineQuadraticFunctionGeneralFormAndFormatP1AndP2(p1, p2);
	}

	public void setGeneralForm(LineQuadraticFunctionGeneralForm generalForm) {
		this.generalForm = generalForm;
		if (generalForm == null) calculateLineQuadraticFunctionGeneralFormAndFormatP1AndP2(p1, p2);
		else calculateP1AndP2BasedOnQuadraticFunction(generalForm);
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	public static class LineQuadraticFunctionGeneralForm {
		// quadratic function: general form f(x) = mx + n (north = m * east + n)
		private Double m, n; // m = slope = tan(alfa)

		public Double getNorthBasedOnEast(Double east) {
			if (east != null && m != null && n != null) return m * east + n;
			else return null;
		}

		public Double getEastBasedOnNorth(Double north) {
			if (north != null && m != null && n != null) return (north - n) / m;
			else return null;
		}
	}
}
