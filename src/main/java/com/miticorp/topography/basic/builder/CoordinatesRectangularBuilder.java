package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.CoordinatesRectangular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 3/22/19.
 * sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilder.class);

    private double north, east, height;

    public CoordinatesRectangular build() {
        LOG.info("Create a rectangular coordinate with values: North[{}], East[{}], Height[{}]", north, east, height);
        return new CoordinatesRectangular(north, east, height);
    }

    public CoordinatesRectangularBuilder setNorth(double north) {
        this.north = north;
        LOG.info("Set North[{}] in a rectangular coordinate", north);

        return this;
    }

    public CoordinatesRectangularBuilder setEast(double east) {
        this.east = east;
        LOG.info("Set East[{}] in a rectangular coordinate", north);

        return this;
    }

    public CoordinatesRectangularBuilder setHeight(double height) {
        this.height = height;
        LOG.info("Set Height[{}] in a rectangular coordinate", north);

        return this;
    }
}
