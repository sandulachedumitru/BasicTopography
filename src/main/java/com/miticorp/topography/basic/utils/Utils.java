package com.miticorp.topography.basic.utils;

import com.miticorp.topography.basic.model.*;

import java.util.Random;

public class Utils {
	
	// Calculates the bearing and distance (polar coordinates) from rectangular coordinates
	public static CoordinatesPolar calculatesPolarFromRectangularCoordinates(Point from, Point to, AngleType angleType) {
		if ((from != null && to != null) && (from.getCoord() instanceof CoordinatesRectangular && to.getCoord() instanceof CoordinatesRectangular)) {
			CoordinatesPolar coordinatesPolar = new CoordinatesPolar();
			
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			double height1, height2;

			CoordinatesRectangular fromCoord 	= (CoordinatesRectangular) from.getCoord();
			CoordinatesRectangular toCoord 		= (CoordinatesRectangular) to.getCoord();

			if ( fromCoord == null ) return null;
			else if ( 	(fromCoord.getNorth() == null)
			|| 			(fromCoord.getEast() == null)
			|| 			(fromCoord.getHeight() == null) ) return null;
			if ( toCoord == null ) return null;
			else if ( 	(toCoord.getNorth() == null)
			|| 			(toCoord.getEast() == null)
			|| 			(toCoord.getHeight() == null) ) return null;
			
			double scaleFactorFrom = from.getScale() == null ? new Scale().getScaleFactor() : from.getScale().getScaleFactor();
			double scaleFactorTo = to.getScale() == null ? new Scale().getScaleFactor() : to.getScale().getScaleFactor();

			north1 	= fromCoord.getNorth() 	* scaleFactorFrom;
			north2 	= toCoord.getNorth() 	* scaleFactorTo;
			east1 	= fromCoord.getEast() 	* scaleFactorFrom;
			east2 	= toCoord.getEast() 	* scaleFactorTo;
			height1	= fromCoord.getHeight() * scaleFactorFrom;
			height2	= toCoord.getHeight()	* scaleFactorTo;
			
			deltaNorth 	= north2 - north1;
			deltaEast 	= east2 - east1;
			
			// Calculate angle
			double theta = 0D, omega;
			
			AngleType angleTypeRadian = new AngleTypeRadian();
			double quadrant = angleType.getMaxNumberOfCircleDegrees() / 4;
			
			if ((deltaNorth > 0) && (deltaEast >= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 0 * quadrant;
			}
			else if ((deltaNorth <= 0) && (deltaEast > 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 1 * quadrant;
			}
			else if ((deltaNorth < 0) && (deltaEast <= 0)) {
				omega = Math.atan(deltaEast/deltaNorth);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 2 * quadrant;
			}
			else if ((deltaNorth >= 0) && (deltaEast < 0)) {
				omega = Math.atan(deltaNorth/deltaEast);
				theta = omega * angleTypeRadian.getAngleChangeSystemFactor(angleType) + 3 * quadrant;
			}
			
			// Calculate distance
			double distance = Math.sqrt(Math.pow(deltaNorth, 2) + Math.pow(deltaEast, 2));
			
			// Prepare return
			Angle angleRez = new Angle(theta, angleType, true, null, null);
			Distance distanceRez = new Distance();
			
			distanceRez.setValue(distance);
			distanceRez.setFrom(from);
			distanceRez.setTo(to);
			coordinatesPolar.setAngle(angleRez);
			coordinatesPolar.setDistance(distanceRez);
			
			return coordinatesPolar;
		}
		else return null;
	} // method
	
	public static String getRandString(int length) {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphaNumeric.length());
            sb.append(alphaNumeric.charAt(index));
        }
        return sb.toString();
    }
	
	public static String getRandString() {
		return getRandString(100);
	}

} // class
