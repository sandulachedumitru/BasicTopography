package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.Units;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitsFactory {
	public static Units getDefaultInstance() {
		Units INSTANCE = new Units();

		log.info("Factory of UNITS. Created default UNITS instance: {}", INSTANCE);
		return INSTANCE;
	}

}
