package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilder;
import com.miticorp.topography.basic.model.DistanceTypeMetricMeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;

/**
 * Created by Dumitru Sandulache on 22/03/2019.
 * sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularBuilderFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeMetricMeterFactory.class);
    private static final CoordinatesRectangularBuilder INSTANCE = new CoordinatesRectangularBuilder();

    public CoordinatesRectangularBuilder getBuilder() {
        LOG.info("Creates builder [{}]", INSTANCE);
        return INSTANCE;
    }
}
