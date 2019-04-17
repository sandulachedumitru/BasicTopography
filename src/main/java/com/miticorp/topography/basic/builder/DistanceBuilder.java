package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.Distance;
import com.miticorp.topography.basic.model.DistanceType;
import com.miticorp.topography.basic.model.Point;
import com.miticorp.topography.basic.model.Scale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 16/04/19.
 * e-mail: sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
public class DistanceBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceBuilder.class);

    @Autowired
    DistanceType distanceTypeMetricMeterBean;

    private Point from;
    private Point to;
    private double value = 0D;
    private DistanceType distanceType;
    private String name = "";
    private Scale scale;

    public Distance build() {
        Distance distance = new Distance(value, distanceType, scale, name);

        if (distanceType == null) distanceType = distanceTypeMetricMeterBean;

        if (from != null || to != null)
            if (from.getCoord() != null || to.getCoord() != null) distance = new Distance(from, to, distanceType, scale.getScale(), name);

        LOG.info("Build Distance with values: PointFrom[{}], PointTo[{}], Value[{}], DistanceType[{}], Name[{}]", from, to, value, distanceType, name);

        return distance;
    }

    public DistanceBuilder setPointFrom(Point from) {
        this.from = from;
        LOG.info("Set Point From [{}] parameter of a Distance", from);

        return this;
    }

    public DistanceBuilder setPointTo(Point to)  {
        this.to = to;
        LOG.info("Set Point To [{}} parameter of a Distance", to);

        return this;
    }

    public DistanceBuilder setValue(double value) {
        this.value = value;
        LOG.info("Set Value [{}] of a Distance. If Point From and Point TO have been set correctly then the Value set here will be override by the calculated distance.", value);

        return this;
    }

    public DistanceBuilder setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
        LOG.info("Set Distance Type [{}] of a Distance", distanceType);

        return this;
    }

    public DistanceBuilder setName(String name) {
        this.name = name;
        LOG.info("Set Name [{}] of a Distance", name);

        return this;
    }

    public DistanceBuilder setScale(Scale scale) {
        this.scale = scale;
        LOG.info("Set Scale [{}] of a Distance", scale);

        return this;
    }
}
