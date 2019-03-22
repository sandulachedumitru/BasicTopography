package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.Coordinates;
import com.miticorp.topography.basic.model.Point;
import com.miticorp.topography.basic.model.Scale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 3/22/19.
 * sandulachedumitru@hotmail.com
 */
public class PointBuilder <T extends Coordinates> {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilder.class);

    private T coord;
    private Scale scale = new Scale();
    private String name = "";

    public PointBuilder setCoord(T coord) {
        this.coord = coord;
        LOG.info("Set Coordinate[{}] of a Point", coord);

        return this;
    }

    public PointBuilder setScale(Scale scale) {
        this.scale = scale;
        LOG.info("Set Scale[{}] of a Point", scale);

        return this;
    }

    public PointBuilder setScale(double scaleValue) {
        this.scale.setScale(scaleValue);
        LOG.info("Set Scale[{}] of a Point", scaleValue);

        return this;
    }

    public PointBuilder setName(String name) {
        this.name = name;
        LOG.info("Set Name[{}] of a Point", name);

        return this;
    }

    public Point build() {
        LOG.info("Create a Point with parameters: Coord[{}], Scale[{}], Name[{}]", coord, scale, name);
        return new Point<T>(coord, scale.getScale(), name);
    }
}
