package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.DistanceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dumitru Sandulache on 22/3/19.
 * sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularBuilder {
    // https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null
    // google search: autowired instance is null
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilder.class);

    private double north, east, height;
    private DistanceType distanceType;
    private AngleType angleType;

    @Autowired
    private CoordinatesRectangular coordinatesRectangular;

    public CoordinatesRectangular build() {
//        CoordinatesRectangular coordinatesRectangular = new CoordinatesRectangular(north, east, height);


        if (distanceType != null) coordinatesRectangular.setDistanceType(distanceType);
        else distanceType = coordinatesRectangular.getDistanceType();

        if (angleType != null) coordinatesRectangular.setAngleType(angleType);
        else angleType = coordinatesRectangular.getAngleType();

        LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}], DistanceType[{}], AngleType[{}]", north, east, height, distanceType, angleType);

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
}
