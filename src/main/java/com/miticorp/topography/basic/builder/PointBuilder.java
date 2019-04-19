package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.Coordinates;
import com.miticorp.topography.basic.model.Point;
import com.miticorp.topography.basic.model.Scale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 22/3/2019.
 * sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
public class PointBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(PointBuilder.class);

    @Autowired
    private Scale scale;
    private Coordinates coord;
    private String name = "";

    public Point build() {
        LOG.info("Create a Point with parameters: Coord[{}], Scale[{}], Name[{}]", coord, scale, name);
        return new Point(coord, scale, name);
    }

    public PointBuilder setCoord(Coordinates coord) {
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
        this.scale.setScaleFactor(scaleValue);
        LOG.info("Set Scale[{}] of a Point", scaleValue);

        return this;
    }

    public PointBuilder setName(String name) {
        this.name = name;
        LOG.info("Set Name[{}] of a Point", name);

        return this;
    }
}
