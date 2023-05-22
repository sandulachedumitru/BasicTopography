package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeImperialInch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeImperialInchFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeImperialInchFactory.class);
    private static final DistanceTypeImperialInch INSTANCE = new DistanceTypeImperialInch();

    public static DistanceTypeImperialInch getInstance() {
        LOG.info("Factory of Distance Type Imperial for Inch instance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
