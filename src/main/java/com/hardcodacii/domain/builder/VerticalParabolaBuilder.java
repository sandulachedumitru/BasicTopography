package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.VerticalParabola;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class VerticalParabolaBuilder {
	Point P1, P2, P3;

	private VerticalParabolaBuilder() {
	}

	public static VerticalParabolaBuilder getBuilder() {
		return new VerticalParabolaBuilder();
	}

	public Optional<VerticalParabola> build() {
		if (P1 != null && P2 != null && P3 != null) {
			log.info("Creating Parabola [y=axx+bx+c] from 3 points: SUCCESSFUL [P1=({}), P2=({}), P3=({})]", P1, P2, P3);
			return Optional.of(new VerticalParabola(P1, P2, P3));
		} else {
			log.error("Creating parabola from 3 points: FAILED [P1/P2/P3: null]");
			return Optional.empty();
		}
	}

	public VerticalParabolaBuilder setP1(Point p1) {
		P1 = p1;
		return this;
	}

	public VerticalParabolaBuilder setP2(Point p2) {
		P2 = p2;
		return this;
	}

	public VerticalParabolaBuilder setP3(Point p3) {
		P3 = p3;
		return this;
	}
}
