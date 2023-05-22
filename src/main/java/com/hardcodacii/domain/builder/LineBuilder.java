package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Line;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.factory.LineFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class LineBuilder {
	private final Line line = LineFactory.getDefaultInstance();

	private LineBuilder() {
	}

	public static LineBuilder getBuilder() {
		return new LineBuilder();
	}

	public Line build() {
		log.info("Build LINE with values: Point1[{}], Point2[{}], Name[{}]", line.getP1(), line.getP2(), line.getName());
		return line;
	}

	public LineBuilder setP1(Point p1) {
		Point POld = line.getP1();
		line.setP1(p1);
		log.info("Set Point1 of LINE. P1 has been modified from {} into {}", POld, p1);
		return this;
	}

	public LineBuilder setP2(Point p2) {
		Point POld = line.getP2();
		line.setP2(p2);
		log.info("Set Point2 of LINE. P2 has been modified from {} into {}", POld, p2);
		return this;
	}

	public LineBuilder setLineQuadraticFunctionGeneralForm(Line.LineQuadraticFunctionGeneralForm generalForm) {
		Line.LineQuadraticFunctionGeneralForm generalFormOld = line.getGeneralForm();
		line.setGeneralForm(generalForm);
		log.info("Set Line Quadratic Function General Form. It has been modified from {} into {}", generalFormOld, generalForm);
		return this;
	}

	public LineBuilder setName(String name) {
		String nameOld = line.getName();
		line.setName(name);
		log.info("Set Name of LINE. Name has been modified from {} into {}", nameOld, name);
		return this;
	}
}
