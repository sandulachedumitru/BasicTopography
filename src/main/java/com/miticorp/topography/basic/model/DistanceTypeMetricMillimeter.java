package com.miticorp.topography.basic.model;

public class DistanceTypeMetricMillimeter extends DistanceTypeMetric {
	private Double conversionToMeter = 1D / 1000;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}
}
