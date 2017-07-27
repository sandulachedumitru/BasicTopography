package com.miticorp.topography.basic.model;

public class BearingRectangular extends Angle {
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
	private <T extends CoordinatesRectangular> Double calculateBearingRectangularByPoints(Point from, Point to) {
		if (from != null && to != null) {
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			double height1, height2;
			
			north1 	= ((T) from.getCoord()).getNorth();
			north2 	= ((T) to.getCoord()).getNorth();
			east1 	= ((T) from.getCoord()).getEast();
			east2 	= ((T) to.getCoord()).getEast();
			height2	= ((T) from.getCoord()).getHeight();
			height2	= ((T) to.getCoord()).getHeight();
			
			deltaNorth 	= north2 - north1;
			deltaEast 	= east2 - east1;
			
			double theta = 0D, omega;
			
			AngleType angleType = getAngleType();
			AngleType angleTypeRadian = new AngleTypeRadian();
			double cadran = angleType.getMaxNumberOfCircleDegrees() / 4;
			
			if ((deltaNorth > 0) && (deltaEast >= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 0 * cadran;
			}
			else if ((deltaNorth <= 0) && (deltaEast > 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 1 * cadran;
			}
			else if ((deltaNorth < 0) && (deltaEast <= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 2 * cadran;
			}
			else if ((deltaNorth >= 0) && (deltaEast < 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 3 * cadran;
			}
			
			return theta;
		}
		else return null;
	}
}
