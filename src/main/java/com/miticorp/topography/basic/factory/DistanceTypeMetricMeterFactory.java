package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeMetricMeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeMetricMeterFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeMetricMeterFactory.class);
    private static final DistanceTypeMetricMeter INSTANCE = new DistanceTypeMetricMeter();

    public static DistanceTypeMetricMeter getInstance() {
        LOG.info("Factory of Distance Type Metric for Meter instance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
