package com.miticorp.topography.basic.model;

import java.util.Objects;

public class DistanceTypeMetricMillimeter extends DistanceTypeMetric {
	private final Double conversionToMeter = 1D / 1000;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DistanceTypeMetricMillimeter that = (DistanceTypeMetricMillimeter) o;
		return Objects.equals(conversionToMeter, that.conversionToMeter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionToMeter);
	}

	@Override
	public String toString() {
		return "DistanceTypeMetricMillimeter{" +
				"conversionToMeter=" + conversionToMeter +
				'}';
	}
}
