package com.hardcodacii.environment;

import com.hardcodacii.domain.Units;
import com.hardcodacii.domain.factory.UnitsFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a singleton
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Setter
@Getter
public final class Environment {
	private Units units = UnitsFactory.getDefaultInstance();

	private Environment() {
	}

	public static Environment getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private static class SingletonHelper {
		private static final Environment INSTANCE = new Environment();
	}
}
