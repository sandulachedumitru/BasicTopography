package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.Angle;
import com.hardcodacii.domain.CoordinatesPolar;
import com.hardcodacii.domain.Distance;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */
@Slf4j
public class CoordinatesPolarFactory {
	public static CoordinatesPolar getDefaultInstance() {
		log.info("Factory of RECTANGULAR POLAR. Creates default RECTANGULAR POLAR instance ...");

		Angle angle = AngleFactory.getDefaultInstance();
		Distance distance = DistanceFactory.getDefaultInstance();

		CoordinatesPolar INSTANCE = new CoordinatesPolar(angle, distance);
		log.info("Created default POLAR COORDINATES instance: {}", INSTANCE);
		return INSTANCE;
	}
}
