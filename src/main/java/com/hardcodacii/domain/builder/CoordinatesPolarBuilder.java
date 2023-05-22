package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.Angle;
import com.hardcodacii.domain.CoordinatesPolar;
import com.hardcodacii.domain.Distance;
import com.hardcodacii.domain.factory.CoordinatesPolarFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class CoordinatesPolarBuilder {
	private final CoordinatesPolar coordPolar = CoordinatesPolarFactory.getDefaultInstance();

	private CoordinatesPolarBuilder() {
	}

	public static CoordinatesPolarBuilder getBuilder() {
		return new CoordinatesPolarBuilder();
	}

	public CoordinatesPolar build() {
		log.info("Build polar coordinate with values: Angle[{}], Distance[{}]", coordPolar.getAngle(), coordPolar.getDistance());
		return coordPolar;
	}

	public CoordinatesPolarBuilder setAngle(Angle angle) {
		coordPolar.setAngle(angle);
		log.info("Set Angle[{}] in polar coordinate", angle);
		return this;
	}

	public CoordinatesPolarBuilder setDistance(Distance distance) {
		coordPolar.setDistance(distance);
		log.info("Set Distance[{}] in polar coordinate", distance);
		return this;
	}
}
