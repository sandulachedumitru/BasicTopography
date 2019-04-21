package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeImperialMile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeImperialMileFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeImperialMileFactory.class);
    private static final DistanceTypeImperialMile INSTANCE = new DistanceTypeImperialMile();

    public static DistanceTypeImperialMile getInstance() {
        LOG.info("Factory of Distance Type Imperial for Mile instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
