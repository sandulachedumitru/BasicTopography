package com.miticorp.topography.basic.model;

import java.util.Objects;

public class DistanceTypeMetricCentimeter extends DistanceTypeMetric {
	private final Double conversionToMeter = 1D / 100;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DistanceTypeMetricCentimeter that = (DistanceTypeMetricCentimeter) o;
		return Objects.equals(conversionToMeter, that.conversionToMeter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionToMeter);
	}

	@Override
	public String toString() {
		return "DistanceTypeMetricCentimeter{" +
				"conversionToMeter=" + conversionToMeter +
				'}';
	}
}
