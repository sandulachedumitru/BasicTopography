package com.hardcodacii.service;

import com.hardcodacii.domain.Distance;
import com.hardcodacii.domain.EDistanceType.EDistanceTypeImperial;
import com.hardcodacii.domain.EDistanceType.EDistanceTypeMetric;
import com.hardcodacii.domain.IDistanceType;
import com.hardcodacii.domain.Point;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;
import static com.hardcodacii.environment.CommonConstants.INVALID_ELEMENT;

/**
 * this class is not designed to be instantiated
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */
@Slf4j
public final class DistanceService {
	public static final double METER_TO_YARD = 1.093613D;
	public static final double YARD_TO_METER = 1 / METER_TO_YARD;
	public static final double KM_TO_MILE = 0.621371D;
	public static final double MILE_TO_KM = 1 / KM_TO_MILE;
	public static final double CM_TO_INCH = 0.393701D;
	public static final double INCH_TO_CM = 1 / CM_TO_INCH;
	// Transformation parameters between two distances measurement unit systems
	private static final EDistanceTypeMetric metricKm = EDistanceTypeMetric.KILOMETER;
	private static final EDistanceTypeMetric metricCm = EDistanceTypeMetric.CENTIMETER;
	private static final EDistanceTypeMetric metricMm = EDistanceTypeMetric.MILLIMETER;
	private static final EDistanceTypeImperial imperialMile = EDistanceTypeImperial.MILE;
	private static final EDistanceTypeImperial imperialInch = EDistanceTypeImperial.INCH;
	// this class is not designed to be instantiated
	private DistanceService() {
	}

	/**
	 * Method for transformation parameters between two distance systems
	 * (ex: metric to imperial, meter to yard, km to mm, yard to inch, etc)
	 *
	 * @param fromSystem first system witch must be transformed in second system
	 * @param toSystem   second system witch will be transformed from first system
	 * @param value      value of the first distance system
	 * @return value transformed from first to second distance system
	 */
	public static Optional<Double> transformDistanceFromSystemToSystem(IDistanceType fromSystem, IDistanceType toSystem, Double value) {
		if ((fromSystem != null) && (toSystem != null) && (value != null) && (value >= 0)) {
			if (fromSystem instanceof EDistanceTypeMetric) {
				double toMeterValue = value * fromSystem.getConversionFactor();
				return Optional.ofNullable(transformFromMeter(toSystem, toMeterValue));
			} else if (fromSystem instanceof EDistanceTypeImperial) {
				double toYardValue = value * fromSystem.getConversionFactor();
				return Optional.ofNullable(transformFromYard(toSystem, toYardValue));
			}
		}

		log.error("The transformation from {} into {} could not be done", fromSystem, toSystem);
		return Optional.empty();
	}

	// method used by "Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value)"
	private static Double transformFromMeter(IDistanceType toSystem, Double value) {
		if (toSystem == EDistanceTypeMetric.METER) {
			return value;
		} else if (toSystem == EDistanceTypeMetric.KILOMETER) {
			return value * (1 / metricKm.getConversionFactorToMeter());
		} else if (toSystem == EDistanceTypeMetric.CENTIMETER) {
			return value * (1 / metricCm.getConversionFactorToMeter());
		} else if (toSystem == EDistanceTypeMetric.MILLIMETER) {
			return value * (1 / metricMm.getConversionFactorToMeter());
		} else if (toSystem == EDistanceTypeImperial.YARD) {
			return value * METER_TO_YARD;
		} else if (toSystem == EDistanceTypeImperial.MILE) {
			return value * METER_TO_YARD * (1 / imperialMile.getConversionFactorToYard());
		} else if (toSystem == EDistanceTypeImperial.INCH) {
			return value * METER_TO_YARD * (1 / imperialInch.getConversionFactorToYard());
		} else {
			log.error("The metric transformation into {} could not be done", toSystem);
			return INVALID_ELEMENT;
		}
	}

	// method used by "Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value)"
	private static Double transformFromYard(IDistanceType toSystem, Double value) {
		if (toSystem == EDistanceTypeImperial.YARD) {
			return value;
		} else if (toSystem == EDistanceTypeImperial.MILE) {
			return value * (1 / imperialMile.getConversionFactorToYard());
		} else if (toSystem == EDistanceTypeImperial.INCH) {
			return value * (1 / imperialInch.getConversionFactorToYard());
		} else if (toSystem == EDistanceTypeMetric.METER) {
			return value * YARD_TO_METER;
		} else if (toSystem == EDistanceTypeMetric.KILOMETER) {
			return value * YARD_TO_METER * (1 / metricKm.getConversionFactorToMeter());
		} else if (toSystem == EDistanceTypeMetric.CENTIMETER) {
			return value * YARD_TO_METER * (1 / metricCm.getConversionFactorToMeter());
		} else if (toSystem == EDistanceTypeMetric.MILLIMETER) {
			return value * YARD_TO_METER * (1 / metricMm.getConversionFactorToMeter());
		} else {
			log.error("The imperial transformation into {} could not be done", toSystem);
			return INVALID_ELEMENT;
		}
	}

	/**
	 * Method that calculate distance from 2 points
	 *
	 * @param from start point of distance
	 * @param to   end point of distance
	 */
	public static Optional<Double> getDistanceValue(Point from, Point to) {
		if (from != null && to != null && from.getCoord() != null && to.getCoord() != null) {
			Optional<SimplifiedCoordinatesPolar> coordPolar = CoordinatesService.calculatesPolarCoordinates(from, to);
			if (coordPolar.isPresent()) return Optional.of(coordPolar.get().getDistance());
		}

		log.error("The value of distance could not be calculated");
		return Optional.empty();
	}


	/**
	 * automatically gives a name to distance base on P1 and P2
	 *
	 * @param from point P1
	 * @param to   point P2
	 * @return distance name P1P2 or empty string ""
	 */
	public static String getNameIfPossible(Point from, Point to) {
		if (from != null && to != null && from.getName() != null && to.getName() != null)
			return from.getName() + from.getName();
		return EMPTY_STRING;
	}

	/**
	 * automatically gives a name to distance base on distance object
	 *
	 * @param distance
	 * @return distance name P1P2 or empty string ""
	 */
	public static String getNameIfPossible(Distance distance) {
		if (distance != null) {
			return getNameIfPossible(distance.getFrom(), distance.getTo());
		}
		return EMPTY_STRING;
	}
}
