package com.miticorp.topography.basic.model;

public class BearingRectangular<T extends CoordinatesRectangular> extends Angle {
	private Point from;
	private Point to;
	
	// Constructors
	public BearingRectangular(Point from, Point to, AngleType angleType) {
		super();
		this.from = from;
		this.to = to;
		setAngleType(angleType);
		if (from != null && to != null)
			setValue(calculateBearingRectangularByPoints(from, to));
		else setValue(null);
	}
	public BearingRectangular(Double value, AngleType angleType) {
		super(value, angleType);
	}
	
	// Getters and Setters
	public Point getFrom() {
		return from;
	}
	public void setFrom(Point from) {
		this.from = from;
		setValue(calculateBearingRectangularByPoints(from, to));
	}
	
	public Point getTo() {
		return to;
	}
	public void setTo(Point to) {
		this.to = to;
		setValue(calculateBearingRectangularByPoints(from, to));
	}
	
	// Calculate the bearing
	@SuppressWarnings("unchecked")
	private Double calculateBearingRectangularByPoints(Point from, Point to) {
		if (from != null && to != null) {
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			
			north1 	= ((T) from.getCoord()).getNorth();
			north2 	= ((T) to.getCoord()).getNorth();
			east1 	= ((T) from.getCoord()).getEast();
			east2 	= ((T) to.getCoord()).getEast();
			
			deltaNorth 	= north2 - north1;
			deltaEast 	= east2 - east1;
			
			double theta, omega;
			
			AngleType angleType = getAngleType();
			// AngleType angleTypeRadian = new AngleTypeRadian();
			// angleType.transformAngleToSystem(angleTypeRadian);
			
			
			if ((deltaNorth > 0) && (deltaEast >= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega + 0;
				theta = omega * angleType.getRadianByTransformationFactor() + angleType.getMaxNumberOfCircleDegrees();
			}
			else if ((deltaNorth <= 0) && (deltaEast > 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega + 100;
			}
			else if ((deltaNorth < 0) && (deltaEast <= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega + 200;
			}
			else if ((deltaNorth >= 0) && (deltaEast < 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega + 300;
			}
					
		}
		else return null;
		return null;
	}
}
