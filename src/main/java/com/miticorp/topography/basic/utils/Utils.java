package com.miticorp.topography.basic.utils;

import com.miticorp.topography.basic.model.*;

import java.util.Random;

public class Utils {
	
	// Calculates the bearing and distance (polar coordinates) from rectangular coordinates
	public static <T extends CoordinatesRectangular> CoordinatesPolar calculatesPolarfromRectangularCoordinates(Point<T> from, Point<T> to, AngleType angleType) {
		if (from != null && to != null) {
			CoordinatesPolar coordinatesPolar = new CoordinatesPolar();
			
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			double height1, height2;
			
			if ( from.getCoord() == null ) return null;
			else if ( 	(from.getCoord().getNorth() == null)
			|| 			(from.getCoord().getEast() == null)
			|| 			(from.getCoord().getHeight() == null) ) return null;
			if ( to.getCoord() == null ) return null;
			else if ( 	(to.getCoord().getNorth() == null)
			|| 			(to.getCoord().getEast() == null)
			|| 			(to.getCoord().getHeight() == null) ) return null;
			
			north1 	= from.getCoord().getNorth() 	* from.getScaleFactor();
			north2 	= to.getCoord().getNorth() 		* to.getScaleFactor();
			east1 	= from.getCoord().getEast() 	* from.getScaleFactor();
			east2 	= to.getCoord().getEast() 		* to.getScaleFactor();
			height1	= from.getCoord().getHeight() 	* from.getScaleFactor();
			height2	= to.getCoord().getHeight()		* to.getScaleFactor();
			
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
			Angle angleRez = new Angle(theta, angleType);
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
