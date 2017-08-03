package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

public class Distance<T extends CoordinatesRectangular> {
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
	
	public static Double transformDistanceFromSystemToSystem(DistanceType fromSystem, DistanceType toSystem, Double value) {
		DistanceTypeMetric metricKm = new DistanceTypeMetricKilometer();
		DistanceTypeMetric metricM = new DistanceTypeMetricMeter();
		DistanceTypeMetric metricCm = new DistanceTypeMetricCentimeter();
		DistanceTypeMetric metricMm = new DistanceTypeMetricMillimeter();
		
		DistanceTypeImperial imperialMile = new DistanceTypeImperialMile();
		DistanceTypeImperial imperialYard = new DistanceTypeImperialYard();
		DistanceTypeImperial imperialInch = new DistanceTypeImperialInch();
		
		if (fromSystem instanceof DistanceTypeMetricKilometer) {
			if (toSystem instanceof DistanceTypeMetricKilometer) {return value;}
			else if (toSystem instanceof DistanceTypeMetricMeter) {return value * metricKm.getConversionToMeter();}
			else if (toSystem instanceof DistanceTypeMetricCentimeter) {return value
					* metricKm.getConversionToMeter()
					* (1 /  metricCm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeMetricMillimeter) {return value
					* metricKm.getConversionToMeter()
					* (1 / metricMm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeImperialMile) {return value * KM_TO_MILE;}
			else if (toSystem instanceof DistanceTypeImperialYard) {return value
					* KM_TO_MILE
					* imperialMile.getConversionToYard();}
			else if (toSystem instanceof DistanceTypeImperialInch) {return value
					* KM_TO_MILE
					* imperialMile.getConversionToYard()
					* (1 / imperialInch.getConversionToYard());}
			else return null; 
		}
		else if (fromSystem instanceof DistanceTypeMetricMeter) {
			if (toSystem instanceof DistanceTypeMetricMeter) {return value;}
			else if (toSystem instanceof DistanceTypeMetricKilometer) {return value * (1 / metricKm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeMetricCentimeter) {return value * ( 1 / metricCm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeMetricMillimeter) {return value * (1 /metricMm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeImperialMile) {return value 
					* METER_TO_YARD 
					* (1 / imperialMile.getConversionToYard());}
			else if (toSystem instanceof DistanceTypeImperialYard) {return value * METER_TO_YARD;}
			else if (toSystem instanceof DistanceTypeImperialInch) {return value
					* METER_TO_YARD
					* ( 1 / imperialInch.getConversionToYard());}
		}
		else if (fromSystem instanceof DistanceTypeImperialYard) {
			if (toSystem instanceof DistanceTypeImperialYard) {return value;}
			else if (toSystem instanceof DistanceTypeImperialMile) {return value * (1 / imperialMile.getConversionToYard());}
			else if (toSystem instanceof DistanceTypeImperialInch) {return value * (1 / imperialInch.getConversionToYard());}
			else if (toSystem instanceof DistanceTypeMetricKilometer) {return value 
					* YARD_TO_METER * (1 / metricKm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeMetricMeter) {return value * YARD_TO_METER;}
			else if (toSystem instanceof DistanceTypeMetricCentimeter) {return value
					* YARD_TO_METER
					* (1 / metricCm.getConversionToMeter());}
			else if (toSystem instanceof DistanceTypeMetricMillimeter) {return value
					* YARD_TO_METER
					* (1 / metricMm.getConversionToMeter());}
		}
		// TODO not finished but method will be refactored
		else return null;
		
		// TODO checkout the returned value
		return null;
	}
	
	// method used by constructors
	private void setDistanceValue(Point<T> from, Point<T> to) {
		CoordinatesPolar coordinatesPolar = Utils.calculateCoordinatePolar(from, to, new AngleTypeRadian()); 
		this.value = coordinatesPolar.getDistance().getValue();
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
