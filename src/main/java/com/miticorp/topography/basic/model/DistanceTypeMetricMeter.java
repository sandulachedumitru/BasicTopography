package com.miticorp.topography.basic.model;

public class DistanceTypeMetricMeter extends DistanceTypeMetric {
	private Double conversionToMeter = 1D;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}
}
