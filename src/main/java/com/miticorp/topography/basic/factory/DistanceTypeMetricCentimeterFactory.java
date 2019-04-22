package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeMetricCentimeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeMetricCentimeterFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeMetricCentimeterFactory.class);
    private static final DistanceTypeMetricCentimeter INSTANCE = new DistanceTypeMetricCentimeter();

    public static DistanceTypeMetricCentimeter getInstance() {
        LOG.info("Factory of Distance Type Metric for Centimeter instance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
