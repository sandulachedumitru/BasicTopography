package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.CoordinatesRectangular;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class CoordinatesRectangularFactory {
	public static CoordinatesRectangular getDefaultInstance() {
		CoordinatesRectangular INSTANCE = new CoordinatesRectangular(0, 0, 0);

		log.info("Factory of RECTANGULAR COORDINATES. Created default RECTANGULAR COORDINATES instance: {}", INSTANCE);
		return INSTANCE;
	}
}
