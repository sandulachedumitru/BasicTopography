package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.factory.AngleFactory;
import com.miticorp.topography.basic.factory.DistanceFactory;
import com.miticorp.topography.basic.model.Angle;
import com.miticorp.topography.basic.model.CoordinatesPolar;
import com.miticorp.topography.basic.model.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by Sandulache Dumitru on 15/04/19.
 * e-mail: sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
public class CoordinatesPolarBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesPolarBuilder.class);

    private Angle angle = AngleFactory.getInstance();
    private Distance distance = DistanceFactory.getInstance();

    public CoordinatesPolar build() {

        return new CoordinatesPolar(angle, distance);
    }

    public CoordinatesPolarBuilder setAngle(Angle angle) {
        this.angle = angle;
        LOG.info("Set Angle[{}] in a polar coordinate", angle);

        return this;
    }

    public CoordinatesPolarBuilder setDistance(Distance distance) {
        this.distance = distance;
        LOG.info("Set Distance[{}] in a polar coordinate", distance);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesPolarBuilder that = (CoordinatesPolarBuilder) o;
        return Objects.equals(angle, that.angle) &&
                Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(angle, distance);
    }

    @Override
    public String toString() {
        return "CoordinatesPolarBuilder{" +
                "angle=" + angle +
                ", distance=" + distance +
                '}';
    }
}
