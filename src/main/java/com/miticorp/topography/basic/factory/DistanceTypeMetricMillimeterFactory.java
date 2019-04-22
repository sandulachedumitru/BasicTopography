package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeMetricMillimeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeMetricMillimeterFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeMetricMillimeterFactory.class);
    private static final DistanceTypeMetricMillimeter INSTANCE = new DistanceTypeMetricMillimeter();

    public static DistanceTypeMetricMillimeter getInstance() {
        LOG.info("Factory of Distance Type Metric for Millimeter instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
