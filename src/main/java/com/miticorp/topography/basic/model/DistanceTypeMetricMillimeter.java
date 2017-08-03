package com.miticorp.topography.basic.model;

public class DistanceTypeMetricMillimeter extends DistanceTypeMetric {
	private Double conversionToMeter = (double) (1 / (1000));

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}
}
