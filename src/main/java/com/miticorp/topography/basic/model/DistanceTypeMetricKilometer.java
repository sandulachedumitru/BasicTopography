package com.miticorp.topography.basic.model;

public class DistanceTypeMetricKilometer extends DistanceTypeMetric {
	private Double conversionToMeter = 1000D;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}
}
