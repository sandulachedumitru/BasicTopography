package com.miticorp.topography.basic.utils;

import com.miticorp.topography.basic.model.Angle;
import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.AngleTypeRadian;
import com.miticorp.topography.basic.model.CoordinatesPolar;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.Distance;
import com.miticorp.topography.basic.model.Point;

public class Utils {
	
	// Calculate the bearing and distance
	public static <T extends CoordinatesRectangular> CoordinatesPolar calculateCoordinatePolar(Point<T> from, Point<T> to, AngleType angleType) {
		if (from != null && to != null) {
			CoordinatesPolar coordinatesPolar = new CoordinatesPolar();
			
			double deltaNorth, deltaEast;
			double north1, east1;
			double north2, east2;
			double height1, height2;
			
			north1 	= ((T) from.getCoord()).getNorth() 	* from.getScaleFactor();
			north2 	= ((T) to.getCoord()).getNorth() 	* from.getScaleFactor();
			east1 	= ((T) from.getCoord()).getEast() 	* from.getScaleFactor();
			east2 	= ((T) to.getCoord()).getEast() 	* from.getScaleFactor();
			height2	= ((T) from.getCoord()).getHeight() * from.getScaleFactor();
			height2	= ((T) to.getCoord()).getHeight()	* from.getScaleFactor();
			
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

} // class
