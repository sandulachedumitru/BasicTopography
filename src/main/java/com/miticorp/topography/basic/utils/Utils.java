package com.miticorp.topography.basic.utils;

import java.util.Random;

import com.miticorp.topography.basic.model.Angle;
import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.AngleTypeRadian;
import com.miticorp.topography.basic.model.CoordinatesPolar;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.Distance;
import com.miticorp.topography.basic.model.Point;

public class Utils {
	
	// Calculates the bearing and distance (polar coordinates) from rectangular coordinates
	public static <T extends CoordinatesRectangular> CoordinatesPolar calculatesPolarfromRectangularCoordinates(Point<T> from, Point<T> to, AngleType angleType) {
		if (from != null && to != null) {
			CoordinatesPolar coordinatesPolar = new CoordinatesPolar();
			
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			double height1, height2;
			
			if ( (T) from.getCoord() == null ) return null;
			else if ( (((T) from.getCoord()).getNorth() == null)
			|| (((T) from.getCoord()).getEast() == null)
			|| (((T) from.getCoord()).getHeight() == null) ) return null;
			if ( (T) to.getCoord() == null ) return null;
			else if ( (((T) to.getCoord()).getNorth() == null)
			|| (((T) to.getCoord()).getEast() == null)
			|| (((T) to.getCoord()).getHeight() == null) ) return null;
			
			north1 	= ((T) from.getCoord()).getNorth() 	* from.getScaleFactor();
			north2 	= ((T) to.getCoord()).getNorth() 	* to.getScaleFactor();
			east1 	= ((T) from.getCoord()).getEast() 	* from.getScaleFactor();
			east2 	= ((T) to.getCoord()).getEast() 	* to.getScaleFactor();
			height1	= ((T) from.getCoord()).getHeight() * from.getScaleFactor();
			height2	= ((T) to.getCoord()).getHeight()	* to.getScaleFactor();
			
			deltaNorth 	= north2 - north1;
			deltaEast 	= east2 - east1;
			
			// Calculate angle
			double theta = 0D, omega = 0D;
			
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
        String str = sb.toString();
        return str;
    }
	
	public static String getRandString() {
		return getRandString(100);
	}

} // class
