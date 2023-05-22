package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.Point;
import lombok.extern.slf4j.Slf4j;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class PointFactory {
	public static Point getDefaultInstance() {
		CoordinatesRectangular coord = CoordinatesRectangularFactory.getDefaultInstance();
		String name = EMPTY_STRING;

		Point INSTANCE = new Point(coord, name);
		log.info("Factory of POINT. Created default POINT instance: {}", INSTANCE);

		return INSTANCE;
	}
}
