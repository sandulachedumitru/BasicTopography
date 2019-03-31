package com.miticorp.topography.basic.model;

public class DistanceTypeImperialYard extends DistanceTypeImperial {
	private final Double conversionToYard = 1D;

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
		DistanceTypeImperialYard other = (DistanceTypeImperialYard) obj;
		if (conversionToYard == null) {
			if (other.conversionToYard != null)
				return false;
		} else if (!conversionToYard.equals(other.conversionToYard))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DistanceTypeImperialYard{" +
				"conversionToYard=" + conversionToYard +
				'}';
	}
}
