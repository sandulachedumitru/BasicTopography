package com.miticorp.topography.basic.model;

public class DistanceTypeImperialYard extends DistanceTypeImperial {
	private Double conversionToYard = 1D;

	@Override
	public Double getConversionToYard() {
		return conversionToYard;
	}
}
