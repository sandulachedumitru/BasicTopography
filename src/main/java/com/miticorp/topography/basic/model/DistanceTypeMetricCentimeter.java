package com.miticorp.topography.basic.model;

public class DistanceTypeMetricCentimeter extends DistanceTypeMetric {
	private Double conversionToMeter = 1D / 100;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}
}
