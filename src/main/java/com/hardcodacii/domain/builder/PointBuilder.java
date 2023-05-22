package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Coordinates;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.factory.PointFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class PointBuilder {
	private final Point point = PointFactory.getDefaultInstance();

	private PointBuilder() {
	}

	public static PointBuilder getBuilder() {
		return new PointBuilder();
	}

	public Point build() {
		log.info("Create a Point with parameters: Coord=[{}], Name=[{}]", point.getCoord(), point.getName());
		return point;
	}

	public PointBuilder setCoord(Coordinates coord) {
		point.setCoord(coord);
		log.info("Set Coordinate=[{}] of Point", coord);
		return this;
	}

	public PointBuilder setName(String name) {
		point.setName(name);
		log.info("Set Name=[{}] of Point", name);
		return this;
	}
}
