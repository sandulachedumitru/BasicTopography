package com.hardcodacii.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public enum EDistanceType {
	IMPERIAL(
			EDistanceTypeImperial.INCH,
			EDistanceTypeImperial.YARD,
			EDistanceTypeImperial.MILE),
	METRIC(
			EDistanceTypeMetric.MILLIMETER,
			EDistanceTypeMetric.CENTIMETER,
			EDistanceTypeMetric.METER,
			EDistanceTypeMetric.KILOMETER);

	private final List<IDistanceType> distSubtypes;

	EDistanceType(IDistanceType... types) {
		distSubtypes = Arrays.asList(types);
	}

	public static void main(String[] args) {
		EDistanceType dt = EDistanceType.IMPERIAL;
		System.out.println("distType: " + dt);

		System.out.println("IMPERIAL >>> Imperial list >>> " + EDistanceType.IMPERIAL.getDistSubtypes());
		System.out.println("METRIC >>> Metric list >>> " + EDistanceType.METRIC.getDistSubtypes());

		IDistanceType idt = EDistanceTypeImperial.INCH;
		System.out.println("Interf distType: " + idt);
		System.out.println("Interf distType factor: " + idt.getConversionFactor());

		System.out.println("Interf distType factor with cast: " + ((EDistanceTypeImperial) idt).getConversionFactorToYard());
	}

	public List<IDistanceType> getDistSubtypes() {
		return distSubtypes;
	}

	public enum EDistanceTypeImperial implements IDistanceType {
		INCH(0.0277777777777778D),
		YARD(1D),
		MILE(1760D);

		private final double toYard;

		EDistanceTypeImperial(double toYard) {
			this.toYard = toYard;
		}

		public double getConversionFactorToYard() {
			return toYard;
		}

		@Override
		public double getConversionFactor() {
			return getConversionFactorToYard();
		}
	}

	public enum EDistanceTypeMetric implements IDistanceType {
		MILLIMETER(1D / 1000D),
		CENTIMETER(1D / 100D),
		METER(1D),
		KILOMETER(1000D);

		private final double toMeter;

		EDistanceTypeMetric(double toMeter) {
			this.toMeter = toMeter;
		}

		public double getConversionFactorToMeter() {
			return toMeter;
		}

		@Override
		public double getConversionFactor() {
			return getConversionFactorToMeter();
		}
	}
}
