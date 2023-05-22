package com.hardcodacii.environment;

import com.hardcodacii.domain.EAngleType;
import com.hardcodacii.domain.EDistanceType;
import com.hardcodacii.domain.EDistanceType.EDistanceTypeMetric;
import com.hardcodacii.domain.Point;

/**
 * Singleton class
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public final class CommonConstants {
	public static final Double INVALID_ELEMENT = null;
	public static final Point INVALID_POINT = null;
	public static final String EMPTY_STRING = "";
	// tolerance units comparison
	public static final double ZERO_TOLERANCE_COMPARISON = 0D;
	public static final double DOUBLE_TOLERANCE_COMPARISON = 1E-6; // 0.000001
	public static final double ANGLE_TOLERANCE_COMPARISON = 5E-4; // 0.0005
	public static final double DISTANCE_TOLERANCE_COMPARISON = 5E-5; // 0.00005
	// units defaults
	public static final EAngleType DEFAULT_ANGLE_TYPE = EAngleType.CENTESIMAL;
	public static final EDistanceTypeMetric DEFAULT_DISTANCE_TYPE = EDistanceTypeMetric.METER;
	public static final EDistanceType DEFAULT_GENERAL_DISTANCE_TYPE = EDistanceType.METRIC;
	public static final boolean DEFAULT_CLOCKWISE = true;
	private CommonConstants() {
	}

	public static CommonConstants getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private static class SingletonHelper {
		private static final CommonConstants INSTANCE = new CommonConstants();
	}

}
