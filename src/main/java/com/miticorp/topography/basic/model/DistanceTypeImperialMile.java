package com.miticorp.topography.basic.model;

public class DistanceTypeImperialMile extends DistanceTypeImperial  {
	private Double conversionToYard = 1760D;

	@Override
	public Double getConversionToYard() {
		return conversionToYard;
	}
}
