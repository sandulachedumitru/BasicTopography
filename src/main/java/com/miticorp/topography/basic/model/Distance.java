package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.utils.Utils;

/**
 * 
 * Base class for distance representation (distance value, distance point1/point2, distance type (ex; metric, imperial)).
 * Distance extends Shape because a distance is a line between two points.
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 * 
 */
public class Distance extends GeometricElements {
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
	private Point<?> from;
	private Point<?> to;
	private Double value;
	// The distance measurement unit (mm, cm, m, km, inch, yard, mile)
	// Meter by default
	private DistanceType distanceType = new DistanceTypeMetricMeter();
	
	// Constructors
	public Distance(Point<?> from, Point<?> to, DistanceType distanceType, double scaleFactor, String name) {
		super();
		this.from = from;
		this.to = to;
		this.distanceType = distanceType;
		this.scaleFactor = scaleFactor;
		this.name = name;
		setDistanceValue(from, to);
	}
	/**
	 * value property will be automatically calculated from points coordinates if possible.
	 * @param from start point of the distance
	 * @param to end point of the distance
	 * @param distanceType type of distance (ex: metric, imperial).
	 */
	public Distance(Point<?> from, Point<?> to, DistanceType distanceType) {
		super();
		this.from = from;
		this.to = to;
		this.distanceType = distanceType;
		setDistanceValue(from, to);
	}
	
	/**
	 * value property will be automatically calculated from points coordinates if possible. DistanceType property is of type DistanceTypeMetricMeter by default.
	 * @param from start point of the distance
	 * @param to end point of the distance
	 */
	public Distance(Point<?> from, Point<?> to) {
		super();
		this.from = from;
		this.to = to;
		setDistanceValue(from, to);
	}
	
	/**
	 * Constructor for Distance class.
	 * @param value value of distance
	 * @param distanceType type of distance (ex: metric, imperial).
	 */
	public Distance(Double value, DistanceType distanceType) {
		super();
		this.value = value;
		this.distanceType = distanceType;
	}
	
	/**
	 * Constructor for Distance class. DistanceType property is of type DistanceTypeMetricMeter by default.
	 * @param value value of distance
	 */
	public Distance(Double value) {
		super();
		this.value = value;
	}
	
	/**
	 * Default constructor for Distance class.
	 */
	public Distance() {
		super();
	}
	
	// method used by constructors
	@SuppressWarnings("unchecked")
	private void setDistanceValue(Point<?> from, Point<?> to) {
		if (from != null && to != null && from.getCoord() != null && to.getCoord() != null)
			if (from.getCoord().getClass().equals(CoordinatesRectangular.class) && to.getCoord().getClass().equals(CoordinatesRectangular.class)) {
				CoordinatesPolar coordinatesPolar = Utils.calculatesPolarfromRectangularCoordinates((Point<CoordinatesRectangular>) from, (Point<CoordinatesRectangular>) to, new AngleTypeRadian()); 
				if (coordinatesPolar != null) this.value = coordinatesPolar.getDistance().getValue();
			} else if (from.getCoord().getClass().equals(CoordinatesGeographic.class) && to.getCoord().getClass().equals(CoordinatesGeographic.class)) {
				// TODO implements geographic calculation in Utils class
				/*
				 * nu asa se calculeaza este doar pentru clasa de test. Trebuie implementat adevaratul calcul.
				 */
				CoordinatesPolar coordinatesPolar = Utils.calculatesPolarfromRectangularCoordinates((Point<CoordinatesRectangular>) from, (Point<CoordinatesRectangular>) to, new AngleTypeRadian()); 
				if (coordinatesPolar != null) this.value = coordinatesPolar.getDistance().getValue();
			} else if (from.getCoord().getClass().equals(CoordinatesPolar.class) && to.getCoord().getClass().equals(CoordinatesPolar.class)) {
				// TODO implements polar calculation in Utils class
				/*
				 * nu asa se calculeaza este doar pentru clasa de test. Trebuie implementat adevaratul calcul.
				 */
				CoordinatesPolar coordinatesPolar = Utils.calculatesPolarfromRectangularCoordinates((Point<CoordinatesRectangular>) from, (Point<CoordinatesRectangular>) to, new AngleTypeRadian()); 
				if (coordinatesPolar != null) this.value = coordinatesPolar.getDistance().getValue();
			}
	}
	
	/**
	 * Method for transformation parameters between two distance systems 
	 * (ex: metric to imperial, meter to yard, km to mm, yard to inch, etc)
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
	 * (ex: metric to imperial, meter to yard, km to mm, yard to inch, etc)
	 * @param from first distance witch contain value and must be transformed in another unit system
	 * @param to second distance witch will be transformed from first
	 * @return value of the transformed value parameter
	 */
	public static Double transformDistanceFromSystemToSystem(Distance from, Distance to) {
		if ((from != null) && (to != null)) {
			DistanceType fromSystem = from.getDistanceType();
			DistanceType toSystem = to.getDistanceType();
			Double value = from.getValue();
			value = transformDistanceFromSystemToSystem(fromSystem, toSystem, value);
			to.setValue(value);
			
			return value;
		}
		return null;
	}
	
	/**
	 * Method for transformation parameters between current to another unit distance system 
	 * (ex: metric to imperial, meter to yard, km to mm, yard to inch, etc)
	 * @param another target unit distance system
	 * @return distance value in target unit system
	 */
	public Double transformDistanceFromCurrentToAnotherUnitSystem(DistanceType another) {
		return transformDistanceFromSystemToSystem(this.getDistanceType(), another, this.getValue());
	}
	
	/**
	 * Method for transformation parameters between another to current distance system 
	 * (ex: metric to imperial, meter to yard, km to mm, yard to inch, etc)
	 * @param another target unit distance system
	 * @return distance value in current distance system
	 */
	public Double transformDistanceFromAnotherToCurrentUnitSystem(Distance another) {
		if (another != null) {
			return transformDistanceFromSystemToSystem(another.getDistanceType(), this.getDistanceType(), another.getValue());
		}
		return null;
	}
	

	// Getters and Setters
	public synchronized Point<?> getFrom() {
		return from;
	}
	public synchronized void setFrom(Point<?> from) {
		this.from = from;
	}
	public synchronized Point<?> getTo() {
		return to;
	}
	public synchronized void setTo(Point<?> to) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distanceType == null) ? 0 : distanceType.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	/**
	 * Two distances are equal if their value and distance type (ex: metric or imperial) are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (distanceType == null) {
			if (other.distanceType != null)
				return false;
		} else if (!distanceType.equals(other.distanceType))
			return false;
//		if (from == null) {
//			if (other.from != null)
//				return false;
//		} else if (!from.equals(other.from))
//			return false;
//		if (to == null) {
//			if (other.to != null)
//				return false;
//		} else if (!to.equals(other.to))
//			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
