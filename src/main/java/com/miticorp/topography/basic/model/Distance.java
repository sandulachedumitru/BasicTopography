package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

public class Distance<T extends CoordinatesRectangular> {
	private static final DistanceTypeMetric metricKm = new DistanceTypeMetricKilometer();
	private static final DistanceTypeMetric metricCm = new DistanceTypeMetricCentimeter();
	private static final DistanceTypeMetric metricMm = new DistanceTypeMetricMillimeter();
	
	private static final DistanceTypeImperial imperialMile = new DistanceTypeImperialMile();
	private static final DistanceTypeImperial imperialInch = new DistanceTypeImperialInch();
	
	// Transformation parameters between two distances measurement unit systems
	public static final double METER_TO_YARD = 1.093613D;
	public static final double YARD_TO_METER = 1 /METER_TO_YARD;
	
	public static final double KM_TO_MILE = 0.621371D;
	public static final double MILE_TO_KM = 1 / KM_TO_MILE;
	
	public static final double CM_TO_INCH = 0.393701D;
	public static final double INCH_TO_CM = 1 / CM_TO_INCH;
	
	// Fields exposed by Distance by getters and setters
	private Point<T> from;
	private Point<T> to;
	private Double value;
	// The distance measurement unit (mm, cm, m, km, inch, yard, mile)
	// Meter by default
	private DistanceType distanceType = new DistanceTypeMetricMeter();
	
	// Constructors
	public Distance(Point<T> from, Point<T> to, DistanceType distanceType) {
		super();
		this.from = from;
		this.to = to;
		this.distanceType = distanceType;
		if (from != null && to != null) setDistanceValue(from, to);
	}
	public Distance(Point<T> from, Point<T> to) {
		super();
		this.from = from;
		this.to = to;
		if (from != null && to != null) setDistanceValue(from, to);
	}
	public Distance(Double value, DistanceType distanceType) {
		super();
		this.value = value;
		this.distanceType = distanceType;
	}
	public Distance(Double value) {
		super();
		this.value = value;
	}
	public Distance() {
		super();
	}
	
	// method used by constructors
	private void setDistanceValue(Point<T> from, Point<T> to) {
		CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, new AngleTypeRadian()); 
		this.value = coordinatesPolar.getDistance().getValue();
	}
	
	/**
	 * Method for transformation parameters between two distance systems
	 * @param fromSystem first system witch must be transformed in second system
	 * @param toSystem second system witch will be transformed from first system
	 * @param value value of the first distance system
	 * @return value transformed from first to second distance system
	 */
	public static Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value) {
		if ((fromSystem != null) && (toSystem != null) && (value != null)) {
			if (fromSystem instanceof DistanceTypeMetric) {
				Double toMeterValue = value * ((DistanceTypeMetric) fromSystem).getConversionToMeter(); 
				return transformFromMeter(toSystem, toMeterValue);
			}
			else if (fromSystem instanceof DistanceTypeImperial) {
				Double toYardValue = value * ((DistanceTypeImperial) fromSystem).getConversionToYard();
				return transformFromYard(toSystem, toYardValue);
			}
		}
		return null;
	}

	// method used by "Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value)"
	private static Double transformFromMeter(DistanceType toSystem, Double value) {
		if (toSystem instanceof DistanceTypeMetricMeter) {return value;}
		else if (toSystem instanceof DistanceTypeMetricKilometer) {return value * (1 / metricKm.getConversionToMeter());}
		else if (toSystem instanceof DistanceTypeMetricCentimeter) {return value * (1 / metricCm.getConversionToMeter());}
		else if (toSystem instanceof DistanceTypeMetricMillimeter) {return value * (1 /metricMm.getConversionToMeter());}
		else if (toSystem instanceof DistanceTypeImperialYard) {return value 
				* METER_TO_YARD;}
		else if (toSystem instanceof DistanceTypeImperialMile) {return value 
				* METER_TO_YARD 
				* (1 / imperialMile.getConversionToYard());}
		else if (toSystem instanceof DistanceTypeImperialInch) {return value
				* METER_TO_YARD
				* (1 / imperialInch.getConversionToYard());}
		return null;
	}
	
	// method used by "Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value)"
	private static Double transformFromYard(DistanceType toSystem, Double value) {
		if (toSystem instanceof DistanceTypeImperialYard) {return value;}
		else if (toSystem instanceof DistanceTypeImperialMile) {return value * (1 / imperialMile.getConversionToYard());}
		else if (toSystem instanceof DistanceTypeImperialInch) {return value * (1 / imperialInch.getConversionToYard());}
		else if (toSystem instanceof DistanceTypeMetricMeter) {return value
				* YARD_TO_METER;}
		else if (toSystem instanceof DistanceTypeMetricKilometer) {return value 
				* YARD_TO_METER * (1 / metricKm.getConversionToMeter());}
		else if (toSystem instanceof DistanceTypeMetricCentimeter) {return value
				* YARD_TO_METER
				* (1 / metricCm.getConversionToMeter());}
		else if (toSystem instanceof DistanceTypeMetricMillimeter) {return value
				* YARD_TO_METER
				* (1 / metricMm.getConversionToMeter());}
		return null;
	}
	
	/**
	 * Method for transformation parameters between two distance systems
	 * @param from first distance witch contain value and must be transformed in another unit system
	 * @param to second distance witch will be transformed from first
	 * @param value value of the first distance expressed in a unit system
	 * @return value of the transformed value parameter
	 */
	public static Double transformDistanceFromSystemToSystem(Distance<?> from, Distance<?> to, Double value) {
		if ((from != null) && (to != null) && (from.getValue() != null)) {
			DistanceType fromSystem = from.getDistanceType();
			DistanceType toSystem = to.getDistanceType();
			value = from.getValue();
			return transformDistanceFromSystemToSystem(fromSystem, toSystem, value);
		}
		return null;
	}
	
	// TODO javadoc
	public Double transformDistanceFromCurrentToAnotherUnitSystem(DistanceType another) {
		if ((this.getValue() != null) && (this.getDistanceType() != null) && (another != null))
			return transformDistanceFromSystemToSystem(this.getDistanceType(), another, this.getValue());
		return null;
	}
	
	// TODO javadoc
	public Double transformDistanceFromAnotherToCurrentUnitSystem(Distance<?> another) {
		if ((another != null) && (another.getValue() != null) && (another.getDistanceType() != null) && (this.getDistanceType() != null))
			return transformDistanceFromSystemToSystem(another.getDistanceType(), this.getDistanceType(), another.getValue());
		return null;
	}
	

	// Getters and Setters
	public synchronized Point<T> getFrom() {
		return from;
	}
	public synchronized void setFrom(Point<T> from) {
		this.from = from;
	}
	public synchronized Point<T> getTo() {
		return to;
	}
	public synchronized void setTo(Point<T> to) {
		this.to = to;
	}
	public synchronized Double getValue() {
		return value;
	}
	public synchronized void setValue(Double value) {
		this.value = value;
	}
	public synchronized DistanceType getDistanceType() {
		return distanceType;
	}
	public synchronized void setDistanceType(DistanceType distanceType) {
		this.distanceType = distanceType;
	}
}
