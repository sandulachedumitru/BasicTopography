package com.miticorp.topography.basic.model;

public class DistanceTypeImperialInch extends DistanceTypeImperial  {
	private Double conversionToYard = 0.0277777777777778D;

	@Override
	public Double getConversionToYard() {
		return conversionToYard;
	}
}
