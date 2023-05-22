package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Distance;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.factory.DistanceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class DistanceBuilder {
	private final Distance distance = DistanceFactory.getDefaultInstance();

	private DistanceBuilder() {
	}

	public static DistanceBuilder getBuilder() {
		return new DistanceBuilder();
	}

	public Distance build() {
		log.info("Build Distance with values: PointFrom[{}], PointTo[{}], Value[{}], Name[{}]", distance.getFrom(), distance.getTo(), distance.getValue(), distance.getName());
		return distance;
	}

	public DistanceBuilder setPointFrom(Point from) {
		distance.setFrom(from);
		return this;
	}

	public DistanceBuilder setPointTo(Point to) {
		distance.setTo(to);
		return this;
	}

	public DistanceBuilder setValue(double value) {
		distance.setValue(value);
		return this;
	}

	public DistanceBuilder setName(String name) {
		distance.setName(name);
		log.info("Set Name of Distance to {}", name);
		return this;
	}
}
