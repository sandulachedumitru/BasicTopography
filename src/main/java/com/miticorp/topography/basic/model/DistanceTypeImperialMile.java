package com.miticorp.topography.basic.model;

public class DistanceTypeImperialMile extends DistanceTypeImperial  {
	private final Double conversionToYard = 1760D;

	@Override
	public Double getConversionToYard() {
		return conversionToYard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversionToYard == null) ? 0 : conversionToYard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DistanceTypeImperialMile other = (DistanceTypeImperialMile) obj;
		if (conversionToYard == null) {
			if (other.conversionToYard != null)
				return false;
		} else if (!conversionToYard.equals(other.conversionToYard))
			return false;
		return true;
	}
}
