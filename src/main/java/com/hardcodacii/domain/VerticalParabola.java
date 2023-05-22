package com.hardcodacii.domain;

import com.hardcodacii.domain.builder.CoordinatesRectangularBuilder;
import com.hardcodacii.domain.builder.PointBuilder;
import com.hardcodacii.service.PointService;
import com.hardcodacii.utils.Utils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.DISTANCE_TOLERANCE_COMPARISON;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class VerticalParabola extends Parabola {
	// quadratic function: general/standard form f(x) = ax^2+bx+c = (x−h)^2+k
	private ParabolaQuadraticFunctionGeneralForm generalForm;
	private ParabolaQuadraticFunctionStandardForm standardForm;
	private ParabolaInterceptsParameters intercepts;

	public VerticalParabola(Point P1, Point P2, Point P3) {
		this.P1 = P1;
		this.P2 = P2;
		this.P3 = P3;

		// transform point coordinates to rectangular
		Optional<Point> p1Opt = PointService.transformPointFromCoordSystemToCoordSystem(P1, CoordinatesRectangular.class);
		Optional<Point> p2Opt = PointService.transformPointFromCoordSystemToCoordSystem(P2, CoordinatesRectangular.class);
		Optional<Point> p3Opt = PointService.transformPointFromCoordSystemToCoordSystem(P3, CoordinatesRectangular.class);

		if (p1Opt.isPresent() && p2Opt.isPresent() && p3Opt.isPresent()) {
			this.P1 = p1Opt.get();
			this.P2 = p2Opt.get();
			this.P3 = p3Opt.get();

			double east1 = ((CoordinatesRectangular) P1.getCoord()).getEast();
			double north1 = ((CoordinatesRectangular) P1.getCoord()).getNorth();
			double east2 = ((CoordinatesRectangular) P2.getCoord()).getEast();
			double north2 = ((CoordinatesRectangular) P2.getCoord()).getNorth();
			double east3 = ((CoordinatesRectangular) P3.getCoord()).getEast();
			double north3 = ((CoordinatesRectangular) P3.getCoord()).getNorth();

			// CALCULATE a, b, c COEFFICIENTS from parabola Standard Form equation (y = axx + bx + c)
			double a = (east1 * (north3 - north2) + east2 * (north1 - north3) + east3 * (north2 - north1)) / ((east1 - east2) * (east1 - east3) * (east2 - east3));
			double b = (north2 - north1) / (east2 - east1) - a * (east1 + east2);
			double c = north1 - a * east1 * east1 - b * east1;
			generalForm = new ParabolaQuadraticFunctionGeneralForm(a, b, c);

			if (Utils.areEqual(a, 0D, DISTANCE_TOLERANCE_COMPARISON)) {
				log.warn("Parameter a==0 for parabola [y=axx+bx+c  a!=0] => Set a=1");
				a = 1D;
			}

			// CALCULATE h, k COEFFICIENTS from parabola Vertex Form equation (y = a(x-h)^2 + k)
			double h = (-b) / (2 * a); // axis of symmetry: x = h
			double k = (4 * a * c - b * b) / (4 * a);
			standardForm = new ParabolaQuadraticFunctionStandardForm(a, h, k);

			// p parameter = distance from Vertex to Focus == distance from Vertex to Directrix
			p = 1 / (4 * a);

			// Axis of symmetry: x = h
			axisOfSymmetry = h;

			// Focus of the parabola: F(h, k + p)
			CoordinatesRectangular focusCoord = CoordinatesRectangularBuilder.getBuilder().setEast(h).setNorth(k + p).build();
			focus = PointBuilder.getBuilder().setCoord(focusCoord).setName("Focus").build();

			// Vertex of the parabola: V(h, k)
			CoordinatesRectangular vertexCoord = CoordinatesRectangularBuilder.getBuilder().setEast(h).setNorth(k).build();
			vertex = PointBuilder.getBuilder().setCoord(vertexCoord).setName("Vertex").build();

			// VerticalParabola opens: upward / downward
			aperture = a > 0D ? EParabolaAperture.UPWARD : EParabolaAperture.DOWNWARD;

			// directrix: y = k - p
			directrix = k - p;

			// Latus rectum L = 4p
			latusRectum = Math.abs(4D * p);

			// parabola intercepts parameters
			intercepts = new ParabolaInterceptsParameters(this);
		}
	}

	@Getter
	@ToString
	// quadratic function: standard form: f(x) = a(x−h)^2 + k
	public static class ParabolaQuadraticFunctionStandardForm {
		private final Double a, h, k;

		public ParabolaQuadraticFunctionStandardForm(double a, double h, double k) {
			this.a = a;
			this.h = h;
			this.k = k;
		}

		public double getFunctionValue(double x) {
			return a * (x - h) * (x - h) + k;
		}
	}

	@Getter
	@ToString
	// quadratic function: general form: f(x) = ax^2 + bx + c
	public static class ParabolaQuadraticFunctionGeneralForm {
		private final Double a, b, c;

		public ParabolaQuadraticFunctionGeneralForm(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public double getFunctionValue(double x) {
			return a * x * x + b * x + c;
		}
	}

	@Getter
	@ToString
	public static class ParabolaInterceptsParameters {
		private final Double x1Intercepts;
		private final Double x2Intercepts;
		private final Double yIntercepts;
		private final Double axisSymmetry;

		public ParabolaInterceptsParameters(VerticalParabola parabola) {
			double a = parabola.generalForm.a;
			double b = parabola.generalForm.b;
			double c = parabola.generalForm.c;
			double axis = parabola.axisOfSymmetry;

			Double x1 = null, x2 = null, y = null;
			if (!Utils.areEqual(a, 0D, DISTANCE_TOLERANCE_COMPARISON)) {
				// x-intercepts
				double delta = b * b - 4 * a * c;
				if (delta > 0) {
					x1 = (-b + Math.sqrt(delta)) / (2 * a);
					x2 = (-b - Math.sqrt(delta)) / (2 * a);
				} else if (Utils.areEqual(delta, 0D, DISTANCE_TOLERANCE_COMPARISON)) {
					x1 = x2 = axis;
				}
				// (delta < 0) => x1, x2 not Real numbers; complex numbers; parabola == null

				// y-intercepts
				y = parabola.generalForm.getFunctionValue(0);
			}

			this.x1Intercepts = x1;
			this.x2Intercepts = x2;
			this.yIntercepts = y;
			this.axisSymmetry = axis;
		}
	}
}
