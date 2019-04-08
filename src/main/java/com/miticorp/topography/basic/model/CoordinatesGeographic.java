package com.miticorp.topography.basic.model;

import com.miticorp.topography.basic.factory.AngleTypeCentesimalFactory;
import com.miticorp.topography.basic.factory.AngleTypeHexadecimalFactory;
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
public class CoordinatesGeographic extends CoordinatesOrthogonal {
	private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangular.class);

	// TODO change 'Double' to 'double'. This change have deep impact in code.
	private double latitude, longitude, height;

	private DistanceType distanceTypeMetricMeter = DistanceTypeMetricMeterFactory.getInstance();
	private AngleType angleTypeHexadecimal = AngleTypeHexadecimalFactory.getInstance();

	// Constructors
	@Autowired
	public CoordinatesGeographic(DistanceType distanceTypeMetricMeter, AngleType angleTypeHexadecimal) {
		this.distanceType = distanceTypeMetricMeter;
		this.angleType = angleTypeHexadecimal;
		LOG.info("Creates a geographic coordinate bean with values: default Latitude[{}], default Longitude[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", latitude, longitude, height, distanceType, angleType);
	}
	public CoordinatesGeographic() {
		this.distanceType = distanceTypeMetricMeter;
		this.angleType = angleTypeHexadecimal;
		LOG.info("Creates a geographic coordinate with values: default Latitude[{}], default Longitude[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", latitude, longitude, height, distanceType, angleType);
	}
	public CoordinatesGeographic(double latitude,double longitude,double height) {
		this();
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		LOG.info("Creates a geographic coordinate with values: Latitude[{}], Longitude[{}], Height[{}], default DistanceType[{}], default AngleType[{}]", latitude, longitude, height, distanceType, angleType);
	}
	public CoordinatesGeographic(double latitude,double longitude,double height, DistanceType distanceType, AngleType angleType) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.distanceType = distanceType;
		this.angleType = angleType;
		LOG.info("Creates a geographic coordinate with values: Latitude[{}], Longitude[{}], Height[{}], DistanceType[{}], AngleType[{}]", latitude, longitude, height, distanceType, angleType);
	}

	// Getters and Setters
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatesGeographic that = (CoordinatesGeographic) o;
		return Double.compare(that.latitude, latitude) == 0 &&
				Double.compare(that.longitude, longitude) == 0 &&
				Double.compare(that.height, height) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude, height);
	}

	@Override
	public String toString() {
		return "CoordinatesGeographic{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", height=" + height +
				", distanceType=" + distanceType +
				", angleType=" + angleType +
				'}';
	}
}
