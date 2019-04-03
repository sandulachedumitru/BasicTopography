package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by Dumitru Sandulache on 22/3/19.
 * sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
//@ComponentScan(basePackages = "com.miticorp.topography.basic.model")
public class CoordinatesRectangularBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilder.class);

    private double north, east, height;
    private DistanceType distanceType;
    private AngleType angleType;

    @Autowired
    private Coordinates coordinatesRectangularWithCardinalPoints;

    @Autowired
    private DistanceType distanceTypeMetricMeter;

    @Autowired
    private AngleType angleTypeCentesimal;

    public CoordinatesRectangular build() {
        CoordinatesRectangular coordinatesRectangular = new CoordinatesRectangular(north, east, height, distanceTypeMetricMeter, angleTypeCentesimal);
        coordinatesRectangular.setDistanceType(coordinatesRectangularWithCardinalPoints.getDistanceType());
        coordinatesRectangular.setAngleType(coordinatesRectangularWithCardinalPoints.getAngleType());

        if (distanceType != null) coordinatesRectangular.setDistanceType(distanceType);
        else distanceType = coordinatesRectangular.getDistanceType();

        if (angleType != null) coordinatesRectangular.setAngleType(angleType);
        else angleType = coordinatesRectangular.getAngleType();

        LOG.info("Build rectangular coordinate with values: North[{}], East[{}], Height[{}], DistanceType[{}], AngleType[{}]", north, east, height, distanceType, angleType);

        return coordinatesRectangular;
    }

    public CoordinatesRectangularBuilder setNorth(double north) {
        this.north = north;
        LOG.info("Set North[{}] in a rectangular coordinate", north);

        return this;
    }

    public CoordinatesRectangularBuilder setEast(double east) {
        this.east = east;
        LOG.info("Set East[{}] in a rectangular coordinate", east);

        return this;
    }

    public CoordinatesRectangularBuilder setHeight(double height) {
        this.height = height;
        LOG.info("Set Height[{}] in a rectangular coordinate", height);

        return this;
    }
    
    public CoordinatesRectangularBuilder setDistanceType(DistanceType distanceType) {
    	this.distanceType = distanceType;
    	LOG.info("Set DistanceType[{}] in a rectangular coordinate", distanceType);
    	
    	return this;
    }
    
    public CoordinatesRectangularBuilder setAngleType(AngleType angleType) {
    	this.angleType = angleType;
    	LOG.info("Set AngleType[{}] in a rectangular coordinate", angleType);
    	
    	return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesRectangularBuilder that = (CoordinatesRectangularBuilder) o;
        return Double.compare(that.north, north) == 0 &&
                Double.compare(that.east, east) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Objects.equals(distanceType, that.distanceType) &&
                Objects.equals(angleType, that.angleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(north, east, height, distanceType, angleType);
    }

    @Override
    public String toString() {
        return "CoordinatesRectangularBuilder{" +
                "north=" + north +
                ", east=" + east +
                ", height=" + height +
                ", distanceType=" + distanceType +
                ", angleType=" + angleType +
                '}';
    }
}
