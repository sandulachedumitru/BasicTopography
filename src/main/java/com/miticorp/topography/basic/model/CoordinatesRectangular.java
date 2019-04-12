package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.factory.AngleTypeCentesimalFactory;
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
public class CoordinatesRectangular extends CoordinatesOrthogonal {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangular.class);

	// TODO change 'Double' to 'double'. This change have deep impact in code.
	private Double north, east, height;

	private DistanceType distanceTypeMetricMeter = DistanceTypeMetricMeterFactory.getInstance();
	private AngleType angleTypeCentesimal = AngleTypeCentesimalFactory.getInstance();

	// constructors
	@Autowired
	private CoordinatesRectangular(DistanceType distanceTypeMetricMeterBean, AngleType angleTypeCentesimalBean) {
		this.distanceType = distanceTypeMetricMeterBean;
		this.angleType = angleTypeCentesimalBean;
		LOG.info("Creates a rectangular coordinate bean with values: default North[{}], default East[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", north, east, height, distanceType, angleType);
	}
	public CoordinatesRectangular() {
		this.distanceType = distanceTypeMetricMeter;
		this.angleType = angleTypeCentesimal;
		LOG.info("Creates a rectangular coordinate with values: default North[{}], default East[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", north, east, height, distanceType, angleType);
	}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue) {
		this();
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		LOG.info("Creates a rectangular coordinate with values: North[{}], East[{}], Height[{}], default DistanceType[{}], default AngleType[{}]", north, east, height, distanceType, angleType);
	}
	public CoordinatesRectangular(double northValue, double estValue, double heightValue, DistanceType distanceType, AngleType angleType) {
		this.north = northValue;
		this.east = estValue;
		this.height = heightValue;
		this.distanceType = distanceType;
		this.angleType = angleType;
		LOG.info("Creates a rectangular coordinate with values: North[{}], East[{}], Height[{}], DistanceType[{}], AngleType[{}]", north, east, height, distanceType, angleType);
	}

	// setters and getters
	public Double getNorth() {
		LOG.info("Get value of North: [{}]", north);
		return north;
	}
	public void setNorth(Double north) {
		LOG.info("Set value of North: [{}]", north);
		this.north = north;
	}
	public Double getEast() {
		LOG.info("Get value of East: [{}]", east);
		return east;
	}
	public void setEast(Double east) {
		LOG.info("Set value of East: [{}]", east);
		this.east = east;
	}
	public Double getHeight() {
		LOG.info("Get value of Height: [{}]", height);
		return height;
	}
	public void setHeight(Double height) {
		LOG.info("Set value of Height: [{}]", height);
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatesRectangular that = (CoordinatesRectangular) o;
		return Objects.equals(north, that.north) &&
				Objects.equals(east, that.east) &&
				Objects.equals(height, that.height);
	}

	@Override
	public int hashCode() {
		return Objects.hash(north, east, height);
	}

	@Override
	public String toString() {
		return "CoordinatesRectangular{" +
				"north=" + north +
				", east=" + east +
				", height=" + height +
				", distanceType=" + distanceType +
				", angleType=" + angleType +
				'}';
	}
}
