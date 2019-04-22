package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeMetricKilometer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeMetricKilometerFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeMetricKilometerFactory.class);
    private static final DistanceTypeMetricKilometer INSTANCE = new DistanceTypeMetricKilometer();

    public static DistanceTypeMetricKilometer getInstance() {
        LOG.info("Factory of Distance Type Metric for Kilometer: [{}]", INSTANCE);
        return INSTANCE;
    }
}
