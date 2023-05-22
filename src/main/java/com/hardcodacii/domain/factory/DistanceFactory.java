package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.Distance;
import com.hardcodacii.domain.Point;
import lombok.extern.slf4j.Slf4j;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class DistanceFactory {
	public static Distance getDefaultInstance() {
		Point from = PointFactory.getDefaultInstance();
		Point to = PointFactory.getDefaultInstance();
		String name = EMPTY_STRING;

		Distance INSTANCE = new Distance(from, to, name);
		log.info("Factory of DISTANCE. Created default DISTANCE instance: {}", INSTANCE);

		return INSTANCE;
	}
}
