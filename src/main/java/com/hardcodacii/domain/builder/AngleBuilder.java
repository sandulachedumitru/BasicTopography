package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Angle;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.factory.AngleFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class AngleBuilder {
	private final Angle angle = AngleFactory.getDefaultInstance();

	private AngleBuilder() {
	}

	public static AngleBuilder getBuilder() {
		return new AngleBuilder();
	}

	public Angle build() {
		log.info("Build Angle with values: Value=[{}], Name=[{}]]", angle.getValue(), angle.getName());
		return angle;
	}

	public AngleBuilder setValue(double angleValue) {
		angle.setValue(angleValue);
		return this;
	}

	public AngleBuilder setName(String name) {
		angle.setName(name);
		return this;
	}

	public AngleBuilder setVertex(Point vertex) {
		angle.setVertex(vertex);
		return this;
	}

	public AngleBuilder setPoint1(Point point) {
		angle.setP1(point);
		return this;
	}

	public AngleBuilder setPoint2(Point point) {
		angle.setP2(point);
		return this;
	}
}
