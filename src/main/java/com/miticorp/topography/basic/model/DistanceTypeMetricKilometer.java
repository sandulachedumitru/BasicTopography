package com.miticorp.topography.basic.model;

public class DistanceTypeMetricKilometer extends DistanceTypeMetric {
	private final Double conversionToMeter = 1000D;

	@Override
	public Double getConversionToMeter() {
		return conversionToMeter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversionToMeter == null) ? 0 : conversionToMeter.hashCode());
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
		DistanceTypeMetricKilometer other = (DistanceTypeMetricKilometer) obj;
		if (conversionToMeter == null) {
			if (other.conversionToMeter != null)
				return false;
		} else if (!conversionToMeter.equals(other.conversionToMeter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DistanceTypeMetricKilometer{" +
				"conversionToMeter=" + conversionToMeter +
				'}';
	}
}
