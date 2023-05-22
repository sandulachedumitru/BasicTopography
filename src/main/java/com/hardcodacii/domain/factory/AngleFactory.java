package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.Angle;
import lombok.extern.slf4j.Slf4j;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class AngleFactory {
	public static Angle getDefaultInstance() {
		Double angleValue = 0D;
		String name = EMPTY_STRING;

		Angle INSTANCE = new Angle(angleValue, name);
		log.info("Factory of ANGLE. Created default ANGLE instance: {}", INSTANCE);
		return INSTANCE;
	}
}
