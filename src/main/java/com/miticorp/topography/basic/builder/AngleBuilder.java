package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.Angle;
import com.miticorp.topography.basic.model.AngleType;
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
public class AngleBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(AngleBuilder.class);

    private double value = 0;
    private AngleType angleType;
    private boolean clockwise = true; // true by default
    private String name = "";
    private Scale scale; // is 1 by default

    @Autowired
    private AngleType angleTypeCentesimalBean;

    public Angle build() {
        if (angleType == null) angleType = angleTypeCentesimalBean;

        LOG.info("Build Angle with values: Value[{}], AngleType[{}], Clockwise[{}], Name[{}], Scale[{}]", value, angleType, clockwise, name, scale);

        return new Angle(value, angleType, clockwise, scale.getScale(), name);
    }

    public AngleBuilder setValue(double value) {
        this.value = value;
        LOG.info("Set Value[{}] of an Angle", value);

        return this;
    }

    public AngleBuilder setAngleType(AngleType angleType) {
        this.angleType = angleType;
        LOG.info("Set AngleType[{}] of an Angle", angleType);

        return this;
    }

    public AngleBuilder setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
        LOG.info("Set Clockwise[{}] of an Angle", clockwise);

        return this;
    }

    public AngleBuilder setName(String name) {
        this.name = name;
        LOG.info("Set Name[{}] of an Angle", name);

        return this;
    }

    public AngleBuilder setScale(Scale scale) {
        this.scale = scale;
        LOG.info("Set Scale[{}] of an Angle", scale);

        return this;
    }
}
