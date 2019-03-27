package com.miticorp.topography;

import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilderFactory;
import com.miticorp.topography.basic.builder.PointBuilderFactory;
import com.miticorp.topography.basic.model.*;
import com.miticorp.topography.basic.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

@SpringBootApplication
public class BasicTopographyBootApplication {
	@Autowired
	DistanceType distanceTypeMetricMeter;

	@Autowired
	AngleType angleTypeCentesimal;

	public static void main(String[] args) {
		SpringApplication.run(BasicTopographyBootApplication.class, args);

		LOG.info("TEST ...");

		double cent2rad = 2 * Math.PI / 400;
		double rad2cent = 400 / (2 * Math.PI);
		double rad = Math.PI / 2;

		double cent = rad * rad2cent;
		double dN = 123.456, dE = 45.678;
		LOG.info("rad = " + rad + " --> " + cent);
		LOG.info("sin(" + rad + ") = " + Math.sin(rad));
		LOG.info("sin(" + cent + ") = " + Math.sin(cent * cent2rad));
		LOG.info("arctang(dn/de) = " + Math.atan(dN / dE) * rad2cent);
		LOG.info("cent2rad = " + Angle.CENTESIMAL_TO_RADIAN);


		double base = 2, pow = 20;
		LOG.info(base + "^" + pow + " = " + Math.pow(base, pow));


		Angle angle = new Angle(123.4567, new AngleTypeCentesimal());
		Distance distance = new Distance(16D);
		CoordinatesPolar coordinatesPolar = new CoordinatesPolar(angle, distance);
		double r = coordinatesPolar.getDistance().getValue();
		double thet = coordinatesPolar.getAngle().getValue();
		LOG.info("P(r,thet) = P(" + r + "," + thet + ")");

		double north1 = 4003446.030, east1 = 435488.969, height1 = 101.101;
		double north2 = 4003249.436, east2 = 435452.215, height2 = 101.101;
		CoordinatesRectangular coord1 = new CoordinatesRectangular(north1, east1, height1);
		CoordinatesRectangular coord2 = new CoordinatesRectangular(north2, east2, height2);
		Point<CoordinatesRectangular> from = new Point<>(coord1, 1D, "first");
		Point<CoordinatesRectangular> to = new Point<>(coord2, 1D, "second");
		AngleType angleType = new AngleTypeCentesimal();
		coordinatesPolar = Utils.calculatesPolarfromRectangularCoordinates(from, to, angleType);
		r = coordinatesPolar.getDistance().getValue();
		thet = coordinatesPolar.getAngle().getValue();
		DecimalFormat df3 = new DecimalFormat(".###m");
		df3.setRoundingMode(RoundingMode.UP);
		DecimalFormat df4 = new DecimalFormat(".####g");
		df4.setRoundingMode(RoundingMode.UP);
		LOG.info("P(r,thet) = P(" + df3.format(r) + "," + df4.format(thet) + ")");

		Point<CoordinatesPolar> polar = new Point<>(coordinatesPolar, 1D, "polar");


		CoordinatesRectangular coordinatesRectangular = CoordinatesRectangularBuilderFactory.getBuilder().setNorth(north1).setEast(east1).setHeight(height1).setAngleType(new AngleTypeCentesimal()).setDistanceType(new DistanceTypeMetricMeter()).build();
		Point<CoordinatesRectangular> point = PointBuilderFactory.getBuilder().setCoord(coordinatesRectangular).setName("Base Point").build();
		LOG.info("Point --> DistanceType:{}, \tAngleType:{}", point.getCoord().getDistanceType(), point.getCoord().getAngleType());
	}

	private static final Logger LOG = LoggerFactory.getLogger(BasicTopographyBootApplication.class);



	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

//	@Bean
//	public AngleType angleTypeCentesimal() {
//		return new AngleTypeCentesimal();
//	}
//
//	@Bean
//	public AngleType angleTypeHexadecimal() {
//		return new AngleTypeHexadecimal();
//	}
//
//	@Bean
//	public AngleType angleTypeRadian() {
//		return new AngleTypeRadian();
//	}
//
//	@Bean
//	public DistanceType distanceTypeMetricKilometer() {
//		return new DistanceTypeMetricKilometer();
//	}
//
//	@Bean
//	public DistanceType distanceTypeMetricMeter() {
//		return new DistanceTypeMetricMeter();
//	}
//
//	@Bean
//	public DistanceType distanceTypeMetricCentimeter() {
//		return new DistanceTypeMetricCentimeter();
//	}
//
//	@Bean
//	public DistanceType DistanceTypeMetricMillimeter() {
//		return new DistanceTypeMetricMillimeter();
//	}
//
//	@Bean
//	public DistanceType distanceTypeImperialMile() {
//		return new DistanceTypeImperialMile();
//	}
//
//	@Bean
//	public DistanceType distanceTypeImperialYard() {
//		return new DistanceTypeImperialYard();
//	}
//
//	@Bean
//	public DistanceType distanceTypeImperialInch() {
//		return new DistanceTypeImperialInch();
//	}
}
