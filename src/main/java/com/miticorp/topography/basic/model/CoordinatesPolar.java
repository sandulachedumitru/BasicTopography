package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.factory.AngleFactory;
import com.miticorp.topography.basic.factory.AngleTypeCentesimalFactory;
import com.miticorp.topography.basic.factory.DistanceFactory;
import com.miticorp.topography.basic.factory.DistanceTypeMetricMeterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

// TODO reflect changes in tests classes
@Component
@Scope("prototype")
public class CoordinatesPolar extends Coordinates {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinatesPolar.class);

	private Angle angle = AngleFactory.getInstance();
	private Distance distance = DistanceFactory.getInstance();

	// Constructors
	@Autowired
	private CoordinatesPolar(DistanceType distanceTypeMetricMeterBean, AngleType angleTypeCentesimalBean) {
		this.distanceType = distanceTypeMetricMeterBean;
		this.angleType = angleTypeCentesimalBean;

		angle.setAngleType(angleType);
		angle.setValue(0D);

		distance.setDistanceType(distanceType);
		distance.setValue(0D);

		LOG.info("Creates a polar coordinate bean with values: default Distance[{}], default Angle[{}]", distance, angle);
	}
	public CoordinatesPolar() {
		this.distanceType = DistanceTypeMetricMeterFactory.getInstance();
		this.angleType =  AngleTypeCentesimalFactory.getInstance();

		angle.setAngleType(angleType);
		angle.setValue(0D);

		distance.setDistanceType(distanceType);
		distance.setValue(0D);

		LOG.info("Creates a polar coordinate with values: default Distance[{}], default Angle[{}]", distance, angle);
	}
	public CoordinatesPolar(Angle angle, Distance distance) {
		this.angle = angle;
		this.distance = distance;

		LOG.info("Creates a polar coordinate with values: Distance[{}], Angle[{}]", distance, angle);
	}

	// Getters and Setters
	public synchronized Angle getAngle() {
		LOG.info("Get values of Angle: [{}]", angle);
		return angle;
	}
	public synchronized void setAngle(Angle angle) {
		LOG.info("Set values of Angle: [{}]", angle);
		this.angle = angle;
	}
	public synchronized Distance getDistance() {
		LOG.info("Get values of Distance: [{}]", distance);
		return distance;
	}
	public synchronized void setDistance(Distance distance) {
		LOG.info("Set values of Distance: [{}]", distance);
		this.distance = distance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatesPolar that = (CoordinatesPolar) o;
		return Objects.equals(angle, that.angle) &&
				Objects.equals(distance, that.distance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(angle, distance);
	}

	@Override
	public String toString() {
		return "CoordinatesPolar{" +
				"angle=" + angle +
				", distance=" + distance +
				", distanceType=" + distanceType +
				", angleType=" + angleType +
				'}';
	}
}
