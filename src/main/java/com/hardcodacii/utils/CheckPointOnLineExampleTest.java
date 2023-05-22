package com.hardcodacii.utils;

import com.hardcodacii.domain.*;
import com.hardcodacii.domain.builder.AngleBuilder;
import com.hardcodacii.domain.builder.CoordinatesRectangularBuilder;
import com.hardcodacii.domain.builder.DistanceBuilder;
import com.hardcodacii.domain.builder.PointBuilder;
import com.hardcodacii.environment.Environment;
import com.hardcodacii.service.AngleService;
import com.hardcodacii.service.CoordinatesService;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import com.hardcodacii.service.DistanceService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.ANGLE_TOLERANCE_COMPARISON;

@Slf4j
public class CheckPointOnLineExampleTest {

	private static int checkPointOnLineExampleTest(Point p1, Point p2, Point p3) {
		EAngleType angleType = Environment.getInstance().getUnits().getAngleType();

		Optional<SimplifiedCoordinatesPolar> cp_p1_p2 = CoordinatesService.calculatesPolarCoordinates(p1, p2);
		Optional<SimplifiedCoordinatesPolar> cp_p1_p3 = CoordinatesService.calculatesPolarCoordinates(p1, p3);
		Double angle = cp_p1_p2.isPresent() && cp_p1_p3.isPresent() ? cp_p1_p2.get().getTheta() - cp_p1_p3.get().getTheta() : null;

		Angle angleP2P1P3 = AngleBuilder.getBuilder()
				.setVertex(p1)
				.setPoint1(p2)
				.setPoint2(p3)
				.setName(AngleService.getNameIfPossible(p2, p1, p3))
				.build();
		Distance distanceP1P2 = DistanceBuilder.getBuilder()
				.setPointFrom(p1)
				.setPointTo(p2)
				.setName(DistanceService.getNameIfPossible(p1, p2))
				.build();
		Distance distanceP1P3 = DistanceBuilder.getBuilder()
				.setPointFrom(p1)
				.setPointTo(p3)
				.setName(DistanceService.getNameIfPossible(p1, p3))
				.build();


		log.info("Show elements");
		log.info("=============");
		log.info(angleP2P1P3.toString());
		log.info(distanceP1P2.toString());
		log.info(distanceP1P3.toString());
		log.info("Angle from diferences of directions / bearings: {} -> {}", angle.toString(), angleType);

		int LEFT = -1, RIGHT = 1, ON_LINE = 0;

		double half = 2D;
		double quadrant = 4D;
		double quadCircle = angleType.getMaxNumberOfCircleDegrees() / quadrant;
		Integer position = null;

		if (cp_p1_p2.isPresent() && cp_p1_p3.isPresent()) {
			double thetaP1P2 = cp_p1_p2.get().getTheta();
			double thetaP1P3 = cp_p1_p3.get().getTheta();
			log.info("thetaP1P2:[{}],\tthetaP1P3:[{}]", thetaP1P2, thetaP1P3);

			double rotatedThetaP1P3 = thetaP1P3 - thetaP1P2;
			if (rotatedThetaP1P3 < 0) rotatedThetaP1P3 += quadrant * quadCircle;

			// check if P3 is on P!P2 line
			if (Utils.areEqual(rotatedThetaP1P3, quadCircle * 0D, ANGLE_TOLERANCE_COMPARISON) ||
					Utils.areEqual(rotatedThetaP1P3, quadCircle * half, ANGLE_TOLERANCE_COMPARISON)) {
				position = ON_LINE;
				// check if P3 is on P1P2 right
			} else if ((0 * quadCircle < rotatedThetaP1P3) && (rotatedThetaP1P3 < half * quadCircle)) {
				position = RIGHT;
				// check if P3 is on P1P2 left
			} else if ((half * quadCircle < rotatedThetaP1P3) && (rotatedThetaP1P3 < quadrant * quadCircle)) {
				position = LEFT;
			} else {
				log.info("Imposible");
			}
		}

		return position;
	}

	public static void main(String[] args) {
		int result;

		log.info("******************************");
		log.info("* Prepare set of points ..... *");
		log.info("******************************");

		String name1 = "A";
		double east1 = 7.78D;
		double north1 = -1.86D;
		CoordinatesRectangular coord1 = CoordinatesRectangularBuilder.getBuilder().setEast(east1).setNorth(north1).build();
		Point A = PointBuilder.getBuilder().setCoord(coord1).setName(name1).build();
		System.out.println();

		String name2 = "B";
		double east2 = 16.420D;
		double north2 = 4.540D;
		CoordinatesRectangular coord2 = CoordinatesRectangularBuilder.getBuilder().setEast(east2).setNorth(north2).build();
		Point B = PointBuilder.getBuilder().setCoord(coord2).setName(name2).build();
		System.out.println();

		String name3 = "C";
		double east3 = 12.000D;
		double north3 = 0.020D;
		CoordinatesRectangular coord3 = CoordinatesRectangularBuilder.getBuilder().setEast(east3).setNorth(north3).build();
		Point C = PointBuilder.getBuilder().setCoord(coord3).setName(name3).build();
		System.out.println();

		String name4 = "D";
		double east4 = -0.860D;
		double north4 = -8.260D;
		CoordinatesRectangular coord4 = CoordinatesRectangularBuilder.getBuilder().setEast(east4).setNorth(north4).build();
		Point D = PointBuilder.getBuilder().setCoord(coord4).setName(name4).build();
		System.out.println();

		String name5 = "E";
		double east5 = -0.5441D;
		double north5 = -15.6828D;
		CoordinatesRectangular coord5 = CoordinatesRectangularBuilder.getBuilder().setEast(east5).setNorth(north5).build();
		Point E = PointBuilder.getBuilder().setCoord(coord5).setName(name5).build();
		System.out.println();

		String name6 = "F";
		double east6 = -3.0340D;
		double north6 = 5.5604D;
		CoordinatesRectangular coord6 = CoordinatesRectangularBuilder.getBuilder().setEast(east6).setNorth(north6).build();
		Point F = PointBuilder.getBuilder().setCoord(coord6).setName(name6).build();
		System.out.println();

		String name7 = "G";
		double east7 = 14.3864D;
		double north7 = 10.9766D;
		CoordinatesRectangular coord7 = CoordinatesRectangularBuilder.getBuilder().setEast(east7).setNorth(north7).build();
		Point G = PointBuilder.getBuilder().setCoord(coord7).setName(name7).build();
		System.out.println();

		String name8 = "H";
		double east8 = 14.0617D;
		double north8 = 2.7931D;
		CoordinatesRectangular coord8 = CoordinatesRectangularBuilder.getBuilder().setEast(east8).setNorth(north8).build();
		Point H = PointBuilder.getBuilder().setCoord(coord8).setName(name8).build();
		System.out.println();

		String name9 = "I";
		double east9 = 3.0837D;
		double north9 = -5.3387D;
		CoordinatesRectangular coord9 = CoordinatesRectangularBuilder.getBuilder().setEast(east9).setNorth(north9).build();
		Point I = PointBuilder.getBuilder().setCoord(coord9).setName(name9).build();
		System.out.println();

		String name10 = "J";
		double east10 = 0.1261D;
		double north10 = 8.4728D;
		CoordinatesRectangular coord10 = CoordinatesRectangularBuilder.getBuilder().setEast(east10).setNorth(north10).build();
		Point J = PointBuilder.getBuilder().setCoord(coord10).setName(name10).build();
		System.out.println();

		String name11 = "K";
		double east11 = 14.8261D;
		double north11 = -11.3722D;
		CoordinatesRectangular coord11 = CoordinatesRectangularBuilder.getBuilder().setEast(east11).setNorth(north11).build();
		Point K = PointBuilder.getBuilder().setCoord(coord11).setName(name11).build();
		System.out.println();

		log.info("*********************************************");
		log.info("* Check if C is on the RIGHT of the line AB *");
		log.info("*********************************************");
		result = checkPointOnLineExampleTest(A, B, C);
		if (result <= 0) log.error("Wrong position");
		else log.info("Good {}", result);
		System.out.println();

		log.info("*********************************************");
		log.info("* Check if E is on the RIGHT of the line AB *");
		log.info("*********************************************");
		result = checkPointOnLineExampleTest(A, B, E);
		if (result <= 0) log.error("Wrong position");
		else log.info("Good {}", result);
		System.out.println();

		log.info("********************************************");
		log.info("* Check if F is on the LEFT of the line AB *");
		log.info("********************************************");
		result = checkPointOnLineExampleTest(A, B, F);
		if (result >= 0) log.error("Wrong position");
		else log.info("Good {}", result);
		System.out.println();

		log.info("********************************************");
		log.info("* Check if G is on the LEFT of the line AB *");
		log.info("********************************************");
		result = checkPointOnLineExampleTest(A, B, G);
		if (result >= 0) log.error("Wrong position");
		else log.info("Good {}", result);
		System.out.println();


		//****************************************

		log.info("********************************");
		log.info("* Check if H is on the line AB *");
		log.info("********************************");
		result = checkPointOnLineExampleTest(A, B, H);
		if (result != 0) log.error("Wrong position: {}", result);
		else log.info("Good {}", result);
		System.out.println();

		log.info("********************************");
		log.info("* Check if I is on the line AB *");
		log.info("********************************");
		result = checkPointOnLineExampleTest(A, B, I);
		if (result != 0) log.error("Wrong position: {}", result);
		else log.info("Good {}", result);
		System.out.println();

		log.info("********************************");
		log.info("* Check if J is on the line AB *");
		log.info("********************************");
		result = checkPointOnLineExampleTest(A, B, J);
		if (result >= 0) log.error("Wrong position: {}", result);
		else log.info("Good {}", result);
		System.out.println();

		log.info("********************************");
		log.info("* Check if K is on the line AB *");
		log.info("********************************");
		result = checkPointOnLineExampleTest(A, B, K);
		if (result <= 0) log.error("Wrong position: {}", result);
		else log.info("Good {}", result);
		System.out.println();
	}
}
