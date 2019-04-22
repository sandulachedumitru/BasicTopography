package com.miticorp.topography.basic.model;

import java.util.Objects;

public class DistanceTypeImperialInch extends DistanceTypeImperial  {
	private final Double conversionToYard = 0.0277777777777778D;

	@Override
	public Double getConversionToYard() {
		return conversionToYard;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DistanceTypeImperialInch that = (DistanceTypeImperialInch) o;
		return Objects.equals(conversionToYard, that.conversionToYard);
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionToYard);
	}

	@Override
	public String toString() {
		return "DistanceTypeImperialInch{" +
				"conversionToYard=" + conversionToYard +
				'}';
	}
}
