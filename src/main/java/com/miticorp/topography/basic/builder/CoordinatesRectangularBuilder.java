package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.DistanceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 22/3/19.
 * sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilder.class);

    private double north, east, height;
    private DistanceType distanceType;
    private AngleType angleType;

    public CoordinatesRectangular build() {
        LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}]", north, east, height);
        return new CoordinatesRectangular(north, east, height, distanceType, angleType);
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
