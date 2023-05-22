package com.hardcodacii.utils;

import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.Line;
import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.builder.CoordinatesRectangularBuilder;
import com.hardcodacii.domain.builder.LineBuilder;
import com.hardcodacii.domain.builder.PointBuilder;
import com.hardcodacii.service.AngleService;
import com.hardcodacii.service.CoordinatesService;
import com.hardcodacii.service.CoordinatesService.SimplifiedCoordinatesPolar;
import com.hardcodacii.service.LineService;
import com.hardcodacii.service.PointService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.hardcodacii.environment.CommonConstants.DISTANCE_TOLERANCE_COMPARISON;
import static com.hardcodacii.environment.CommonConstants.INVALID_POINT;

@Slf4j
public class PerpendicularFromPointToLineExampleTest {

	public static void main(String[] args) {
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

		// case 1

		String nameA1 = "A1";
		double eastA1 = 161.0075D;
		double northA1 = 1.2111D;
		CoordinatesRectangular coordA1 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA1).setNorth(northA1).build();
		Point A1 = PointBuilder.getBuilder().setCoord(coordA1).setName(nameA1).build();
		System.out.println();

		String nameB1 = "B1";
		double eastB1 = 189.1491D;
		double northB1 = 1.3189D;
		CoordinatesRectangular coordB1 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB1).setNorth(northB1).build();
		Point B1 = PointBuilder.getBuilder().setCoord(coordB1).setName(nameB1).build();
		System.out.println();

		String nameC1 = "C1";
		double eastC1 = 184.4717D;
		double northC1 = 14.0863D;
		CoordinatesRectangular coordC1 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC1).setNorth(northC1).build();
		Point C1 = PointBuilder.getBuilder().setCoord(coordC1).setName(nameC1).build();
		System.out.println();

		// case 2

		String nameA2 = "A2";
		double eastA2 = 212.1234D;
		double northA2 = -0.2021D;
		CoordinatesRectangular coordA2 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA2).setNorth(northA2).build();
		Point A2 = PointBuilder.getBuilder().setCoord(coordA2).setName(nameA2).build();
		System.out.println();

		String nameB2 = "B2";
		double eastB2 = 235.8511D;
		double northB2 = -7.2103D;
		CoordinatesRectangular coordB2 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB2).setNorth(northB2).build();
		Point B2 = PointBuilder.getBuilder().setCoord(coordB2).setName(nameB2).build();
		System.out.println();

		String nameC2 = "C2";
		double eastC2 = 252.9573D;
		double northC2 = 8.2654D;
		CoordinatesRectangular coordC2 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC2).setNorth(northC2).build();
		Point C2 = PointBuilder.getBuilder().setCoord(coordC2).setName(nameC2).build();
		System.out.println();

		// case 3

		String nameA3 = "A3";
		double eastA3 = 235.5925D;
		double northA3 = -60.0835D;
		CoordinatesRectangular coordA3 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA3).setNorth(northA3).build();
		Point A3 = PointBuilder.getBuilder().setCoord(coordA3).setName(nameA3).build();
		System.out.println();

		String nameB3 = "B3";
		double eastB3 = 252.2058D;
		double northB3 = -52.1771D;
		CoordinatesRectangular coordB3 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB3).setNorth(northB3).build();
		Point B3 = PointBuilder.getBuilder().setCoord(coordB3).setName(nameB3).build();
		System.out.println();

		String nameC3 = "C3";
		double eastC3 = 217.0437D;
		double northC3 = -39.6512D;
		CoordinatesRectangular coordC3 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC3).setNorth(northC3).build();
		Point C3 = PointBuilder.getBuilder().setCoord(coordC3).setName(nameC3).build();
		System.out.println();

		// case 4

		String nameA4 = "A4";
		double eastA4 = 295.0246D;
		double northA4 = -5.1796D;
		CoordinatesRectangular coordA4 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA4).setNorth(northA4).build();
		Point A4 = PointBuilder.getBuilder().setCoord(coordA4).setName(nameA4).build();
		System.out.println();

		String nameB4 = "B4";
		double eastB4 = 323.6762D;
		double northB4 = -5.1796D;
		CoordinatesRectangular coordB4 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB4).setNorth(northB4).build();
		Point B4 = PointBuilder.getBuilder().setCoord(coordB4).setName(nameB4).build();
		System.out.println();

		String nameC4 = "C4";
		double eastC4 = 323.6762D;
		double northC4 = 32.5201D;
		CoordinatesRectangular coordC4 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC4).setNorth(northC4).build();
		Point C4 = PointBuilder.getBuilder().setCoord(coordC4).setName(nameC4).build();
		System.out.println();

		// case 5

		String nameA5 = "A5";
		double eastA5 = 308.2280D;
		double northA5 = -74.8996D;
		CoordinatesRectangular coordA5 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA5).setNorth(northA5).build();
		Point A5 = PointBuilder.getBuilder().setCoord(coordA5).setName(nameA5).build();
		System.out.println();

		String nameB5 = "B5";
		double eastB5 = 338.9599D;
		double northB5 = -74.8996D;
		CoordinatesRectangular coordB5 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB5).setNorth(northB5).build();
		Point B5 = PointBuilder.getBuilder().setCoord(coordB5).setName(nameB5).build();
		System.out.println();

		String nameC5 = "C5";
		double eastC5 = 308.2280D;
		double northC5 = -34.8227D;
		CoordinatesRectangular coordC5 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC5).setNorth(northC5).build();
		Point C5 = PointBuilder.getBuilder().setCoord(coordC5).setName(nameC5).build();
		System.out.println();

		// case 6

		String nameA6 = "A6";
		double eastA6 = 381.9827D;
		double northA6 = 4.8387D;
		CoordinatesRectangular coordA6 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA6).setNorth(northA6).build();
		Point A6 = PointBuilder.getBuilder().setCoord(coordA6).setName(nameA6).build();
		System.out.println();

		String nameB6 = "B6";
		double eastB6 = 432.1595D;
		double northB6 = 23.2445D;
		CoordinatesRectangular coordB6 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB6).setNorth(northB6).build();
		Point B6 = PointBuilder.getBuilder().setCoord(coordB6).setName(nameB6).build();
		System.out.println();

		String nameC6 = "C6";
		double eastC6 = 409.2355D;
		double northC6 = 14.8355D;
		CoordinatesRectangular coordC6 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC6).setNorth(northC6).build();
		Point C6 = PointBuilder.getBuilder().setCoord(coordC6).setName(nameC6).build();
		System.out.println();

		// case 7

		String nameA7 = "A7";
		double eastA7 = 386.8695D;
		double northA7 = -33.0081D;
		CoordinatesRectangular coordA7 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA7).setNorth(northA7).build();
		Point A7 = PointBuilder.getBuilder().setCoord(coordA7).setName(nameA7).build();
		System.out.println();

		String nameB7 = "B7";
		double eastB7 = 423.3760D;
		double northB7 = -15.6300D;
		CoordinatesRectangular coordB7 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB7).setNorth(northB7).build();
		Point B7 = PointBuilder.getBuilder().setCoord(coordB7).setName(nameB7).build();
		System.out.println();

		String nameC7 = "C7";
		double eastC7 = 441.6292D;
		double northC7 = -6.9409D;
		CoordinatesRectangular coordC7 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC7).setNorth(northC7).build();
		Point C7 = PointBuilder.getBuilder().setCoord(coordC7).setName(nameC7).build();
		System.out.println();

		// case 8

		String nameA8 = "A8";
		double eastA8 = 394.1230D;
		double northA8 = -62.9968D;
		CoordinatesRectangular coordA8 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA8).setNorth(northA8).build();
		Point A8 = PointBuilder.getBuilder().setCoord(coordA8).setName(nameA8).build();
		System.out.println();

		String nameB8 = "B8";
		double eastB8 = 448.8826D;
		double northB8 = -36.9296D;
		CoordinatesRectangular coordB8 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB8).setNorth(northB8).build();
		Point B8 = PointBuilder.getBuilder().setCoord(coordB8).setName(nameB8).build();
		System.out.println();

		String nameC8 = "C8";
		double eastC8 = 375.8698D;
		double northC8 = -71.6859D;
		CoordinatesRectangular coordC8 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC8).setNorth(northC8).build();
		Point C8 = PointBuilder.getBuilder().setCoord(coordC8).setName(nameC8).build();
		System.out.println();

		// case 9

		String nameA9 = "A9";
		double eastA9 = 489.8967D;
		double northA9 = 4.1744D;
		CoordinatesRectangular coordA9 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA9).setNorth(northA9).build();
		Point A9 = PointBuilder.getBuilder().setCoord(coordA9).setName(nameA9).build();
		System.out.println();

		String nameB9 = "B9";
		double eastB9 = 564.2888D;
		double northB9 = 39.3898;
		CoordinatesRectangular coordB9 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB9).setNorth(northB9).build();
		Point B9 = PointBuilder.getBuilder().setCoord(coordB9).setName(nameB9).build();
		System.out.println();

		String nameC9 = "C9";
		double eastC9 = 489.8967D;
		double northC9 = 4.1744D;
		CoordinatesRectangular coordC9 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC9).setNorth(northC9).build();
		Point C9 = PointBuilder.getBuilder().setCoord(coordC9).setName(nameC9).build();
		System.out.println();

		// case 10

		String nameA10 = "A10";
		double eastA10 = 491.9710D;
		double northA10 = -33.7755D;
		CoordinatesRectangular coordA10 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA10).setNorth(northA10).build();
		Point A10 = PointBuilder.getBuilder().setCoord(coordA10).setName(nameA10).build();
		System.out.println();

		String nameB10 = "B10";
		double eastB10 = 566.3632D;
		double northB10 = 1.4399D;
		CoordinatesRectangular coordB10 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB10).setNorth(northB10).build();
		Point B10 = PointBuilder.getBuilder().setCoord(coordB10).setName(nameB10).build();
		System.out.println();

		String nameC10 = "C10";
		double eastC10 = 566.3632D;
		double northC10 = 1.4399D;
		CoordinatesRectangular coordC10 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC10).setNorth(northC10).build();
		Point C10 = PointBuilder.getBuilder().setCoord(coordC10).setName(nameC10).build();
		System.out.println();

		// case 11

		String nameA11 = "A11";
		double eastA11 = 499.8812D;
		double northA11 = -69.1108D;
		CoordinatesRectangular coordA11 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA11).setNorth(northA11).build();
		Point A11 = PointBuilder.getBuilder().setCoord(coordA11).setName(nameA11).build();
		System.out.println();

		String nameB11 = "B11";
		double eastB11 = 499.8812D;
		double northB11 = -69.1108D;
		CoordinatesRectangular coordB11 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB11).setNorth(northB11).build();
		Point B11 = PointBuilder.getBuilder().setCoord(coordB11).setName(nameB11).build();
		System.out.println();

		String nameC11 = "C11";
		double eastC11 = 574.2734D;
		double northC11 = -33.8954D;
		CoordinatesRectangular coordC11 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC11).setNorth(northC11).build();
		Point C11 = PointBuilder.getBuilder().setCoord(coordC11).setName(nameC11).build();
		System.out.println();

		// case 12

		String nameA12 = "A12";
		double eastA12 = 513.4464D;
		double northA12 = -106.1581D;
		CoordinatesRectangular coordA12 = CoordinatesRectangularBuilder.getBuilder().setEast(eastA12).setNorth(northA12).build();
		Point A12 = PointBuilder.getBuilder().setCoord(coordA12).setName(nameA12).build();
		System.out.println();

		String nameB12 = "B12";
		double eastB12 = 513.4464D;
		double northB12 = -106.1581D;
		CoordinatesRectangular coordB12 = CoordinatesRectangularBuilder.getBuilder().setEast(eastB12).setNorth(northB12).build();
		Point B12 = PointBuilder.getBuilder().setCoord(coordB12).setName(nameB12).build();
		System.out.println();

		String nameC12 = "C12";
		double eastC12 = 513.4464D;
		double northC12 = -106.1581D;
		CoordinatesRectangular coordC12 = CoordinatesRectangularBuilder.getBuilder().setEast(eastC12).setNorth(northC12).build();
		Point C12 = PointBuilder.getBuilder().setCoord(coordC12).setName(nameC12).build();
		System.out.println();

		// CHECKS ...

		log.info("******************");
		log.info("* CHECK CASE 1.1 *");
		log.info("******************");
		Line lineAB = LineBuilder.getBuilder().setP1(A).setP2(B).setName(LineService.getNameIfPossible(A, B)).build();
		Point perpAB = getPerpendicularFromPointToLineExampleTest(lineAB, C);
		if (perpAB != null) log.info("Good {}", perpAB);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("******************");
		log.info("* CHECK CASE 1.2 *");
		log.info("******************");
		Line lineAB1 = LineBuilder.getBuilder().setP1(A1).setP2(B1).setName(LineService.getNameIfPossible(A1, B1)).build();
		Point perpAB1 = getPerpendicularFromPointToLineExampleTest(lineAB1, C1);
		if (perpAB1 != null) log.info("Good {}", perpAB1);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 2 *");
		log.info("****************");
		Line lineAB2 = LineBuilder.getBuilder().setP1(A2).setP2(B2).setName(LineService.getNameIfPossible(A2, B2)).build();
		Point perpAB2 = getPerpendicularFromPointToLineExampleTest(lineAB2, C2);
		if (perpAB2 != null) log.info("Good {}", perpAB2);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 3 *");
		log.info("****************");
		Line lineAB3 = LineBuilder.getBuilder().setP1(A3).setP2(B3).setName(LineService.getNameIfPossible(A3, B3)).build();
		Point perpAB3 = getPerpendicularFromPointToLineExampleTest(lineAB3, C3);
		if (perpAB3 != null) log.info("Good {}", perpAB3);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 4 *");
		log.info("****************");
		Line lineAB4 = LineBuilder.getBuilder().setP1(A4).setP2(B4).setName(LineService.getNameIfPossible(A4, B4)).build();
		Point perpAB4 = getPerpendicularFromPointToLineExampleTest(lineAB4, C4);
		if (perpAB4 != null) log.info("Good {}", perpAB4);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 5 *");
		log.info("****************");
		Line lineAB5 = LineBuilder.getBuilder().setP1(A5).setP2(B5).setName(LineService.getNameIfPossible(A5, B5)).build();
		Point perpAB5 = getPerpendicularFromPointToLineExampleTest(lineAB5, C5);
		if (perpAB5 != null) log.info("Good {}", perpAB5);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 6 *");
		log.info("****************");
		Line lineAB6 = LineBuilder.getBuilder().setP1(A6).setP2(B6).setName(LineService.getNameIfPossible(A6, B6)).build();
		Point perpAB6 = getPerpendicularFromPointToLineExampleTest(lineAB6, C6);
		if (perpAB6 != null) log.info("Good {}", perpAB6);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 7 *");
		log.info("****************");
		Line lineAB7 = LineBuilder.getBuilder().setP1(A7).setP2(B7).setName(LineService.getNameIfPossible(A7, B7)).build();
		Point perpAB7 = getPerpendicularFromPointToLineExampleTest(lineAB7, C7);
		if (perpAB7 != null) log.info("Good {}", perpAB7);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 8 *");
		log.info("****************");
		Line lineAB8 = LineBuilder.getBuilder().setP1(A8).setP2(B8).setName(LineService.getNameIfPossible(A8, B8)).build();
		Point perpAB8 = getPerpendicularFromPointToLineExampleTest(lineAB8, C8);
		if (perpAB8 != null) log.info("Good {}", perpAB8);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("****************");
		log.info("* CHECK CASE 9 *");
		log.info("****************");
		Line lineAB9 = LineBuilder.getBuilder().setP1(A9).setP2(B9).setName(LineService.getNameIfPossible(A9, B9)).build();
		Point perpAB9 = getPerpendicularFromPointToLineExampleTest(lineAB9, C9);
		if (perpAB9 != null) log.info("Good {}", perpAB9);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("*****************");
		log.info("* CHECK CASE 10 *");
		log.info("*****************");
		Line lineAB10 = LineBuilder.getBuilder().setP1(A10).setP2(B10).setName(LineService.getNameIfPossible(A10, B10)).build();
		Point perpAB10 = getPerpendicularFromPointToLineExampleTest(lineAB10, C10);
		if (perpAB10 != null) log.info("Good {}", perpAB10);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("*****************");
		log.info("* CHECK CASE 11 *");
		log.info("*****************");
		Line lineAB11 = LineBuilder.getBuilder().setP1(A11).setP2(B11).setName(LineService.getNameIfPossible(A11, B11)).build();
		Point perpAB11 = getPerpendicularFromPointToLineExampleTest(lineAB11, C11);
		if (perpAB11 != null) log.info("Good {}", perpAB11);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();

		log.info("*****************");
		log.info("* CHECK CASE 12 *");
		log.info("*****************");
		Line lineAB12 = LineBuilder.getBuilder().setP1(A12).setP2(B12).setName(LineService.getNameIfPossible(A12, B12)).build();
		Point perpAB12 = getPerpendicularFromPointToLineExampleTest(lineAB12, C12);
		if (perpAB12 != null) log.info("Good {}", perpAB12);
		else log.error("The coordinates of perpendicular could not be calculated");
		System.out.println();
	}

	private static Point getPerpendicularFromPointToLineExampleTest(Line line, Point P) {
		// TODO verify Point Units and Line Units

		if (line == null || P == null) return null;

		Point P1 = line.getP1();
		Point P2 = line.getP2();

		double thetaP1P2 = 0D, distanceP1P2 = 0D;
		Optional<SimplifiedCoordinatesPolar> optP1P2 = CoordinatesService.calculatesPolarCoordinates(P1, P2);
		if (optP1P2.isPresent()) {
			thetaP1P2 = optP1P2.get().getTheta();
			distanceP1P2 = optP1P2.get().getDistance();
		} else return null;

		double thetaP1P = 0D, distanceP1P = 0D;
		Optional<SimplifiedCoordinatesPolar> optP1P = CoordinatesService.calculatesPolarCoordinates(P1, P);
		if (optP1P.isPresent()) {
			thetaP1P = optP1P.get().getTheta();
			distanceP1P = optP1P.get().getDistance();
		} else return null;

		double thetaP2P = 0D, distanceP2P = 0D;
		Optional<SimplifiedCoordinatesPolar> optP2P = CoordinatesService.calculatesPolarCoordinates(P2, P);
		if (optP2P.isPresent()) {
			thetaP2P = optP2P.get().getTheta();
			distanceP2P = optP2P.get().getDistance();
		} else return null;

		double a = distanceP2P;
		double b = distanceP1P;
		double c = distanceP1P2;

		if (areEqual(a, 0D)) a = 0;
		if (areEqual(b, 0D)) b = 0;
		if (areEqual(c, 0D)) c = 0;

		if (a < 0 || b < 0 || c < 0) {
			log.error("Distance / side of a triangle cannot be negative: a{}, b{}, c{}", a, b, c);
			return INVALID_POINT;
		}

		// it's important that distance (the side of the triangle that is opposite the angle C)  "c" != 0 because of the formula for "d1"
		if (c == 0) {
			// CASE 12 : a == 0 && b == 0 && c == 0
			if (a == 0 && b == 0) {
				log.error("CASE 12 : Cannot calculate the perpendicular to the line. P1, P2, P3 have same coordinates");
				return INVALID_POINT;
			}
			// CASE 11 : c == 0 && a == b
			else if (areEqual(a, b)) {
				log.error("CASE 11 : Cannot calculate the perpendicular to the line. P1 and P2 have same coordinates");
				return INVALID_POINT;
			}

			log.error("Cannot calculate the perpendicular to the line. Impossible case !!");
			return INVALID_POINT;
		}

		double d1 = (a * a - b * b + c * c) / (2 * c);
		double d2 = c - d1;

		if (areEqual(d1, 0D)) d1 = 0;
		if (areEqual(d2, 0D)) d2 = 0;

		// CASE 1 : d1 > 0 && d2 > 0 && (d1 + d2 == c) && d1 != a && d2 != b)
		if (d1 > 0 && d2 > 0 && areEqual(d1 + d2, c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 1 : The perpendicular from point P is inside of the segment P1P2");
			return PointService.calculateNewPointFromKnownPoint(P1, thetaP1P2, d2).get();
		}
		// CASE 2 : d1 < 0 && d2 > 0 && (d2 - |d1| == c) && d1 != a && d2 != b)
		else if (d1 < 0 && d2 > 0 && areEqual(d2 - Math.abs(d1), c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 2 : The perpendicular from point P is outside of the segment P1P2, near P2");
			return PointService.calculateNewPointFromKnownPoint(P1, thetaP1P2, d2).get();
		}
		// CASE 3 :  d1 > 0 && d2 < 0 && (d1 - |d2| == c) && d1 != a && d2 != b)
		else if (d1 > 0 && d2 < 0 && areEqual(d1 - Math.abs(d2), c) && !areEqual(d1, a) && !areEqual(d2, b)) {
			log.info("CASE 3 : The perpendicular from point P is outside of the segment P1P2, near P1");
			return PointService.calculateNewPointFromKnownPoint(P1, AngleService.getOppositeAngleValue(thetaP1P2), Math.abs(d2)).get();
		}
		// CASE 4 : d1 == 0 && d2 == c && a != 0
		else if (d1 == 0 && areEqual(d2, c) && a != 0) {
			log.info("CASE 4 : The perpendicular from the point P is P2. It's a right-angle triangle. ");
			return P2;
		}
		// CASE 5 : d1 == c && d2 == 0 && b != 0
		else if (areEqual(d1, c) && d2 == 0 && b != 0) {
			log.info("CASE 5 : The perpendicular from the point P is P1. It's a right-angle triangle. ");
			return P1;
		}
		// CASE 6 : ((d1 == a && d2 == b) || (d1 == b && d2 == a)) && d1 != 0 && d2 != 0
		else if (((areEqual(d1, a) && areEqual(d2, b)) || (areEqual(d1, b) && areEqual(d2, a))) && d1 != 0 && d2 != 0) {
			log.error("CASE 6 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, inside P1P2");
			return INVALID_POINT;
		}
		// CASE 7 : d1 < 0 && d2 > 0 && (d2 == c + |d1|)
		else if (d1 < 0 && d2 > 0 && areEqual(d2, c + Math.abs(d1))) {
			log.error("CASE 7 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, but outside, near P2");
			return INVALID_POINT;
		}
		// CASE 8 : d1 > 0 && d2 < 0 && (d1 == c + |d2|)
		else if (d1 > 0 && d2 < 0 && areEqual(d1, c + Math.abs(d2))) {
			log.error("CASE 8 : Cannot calculate the perpendicular to the line. The point P that must be projected is already on the line P1P2, but outside, near P1");
			return INVALID_POINT;
		}
		// CASE 9 : b == 0 && a == c
		else if (b == 0 && areEqual(a, c)) {
			log.error("CASE 9 : Cannot calculate the perpendicular to the line. The point P is P1");
			return INVALID_POINT;
		}
		// CASE 10 : a == 0 && b == c
		else if (a == 0 && areEqual(b, c)) {
			log.error("CASE 10 : Cannot calculate the perpendicular to the line. The point P is P2");
			return INVALID_POINT;
		}

		log.error("Impossible case !!!");
		return INVALID_POINT;
	}

	private static boolean areEqual(double val1, double val2) {
		return Utils.areEqual(val1, val2, DISTANCE_TOLERANCE_COMPARISON);
	}

}
