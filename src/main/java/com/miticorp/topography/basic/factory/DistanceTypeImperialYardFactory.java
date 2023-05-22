package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.DistanceTypeImperialYard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceTypeImperialYardFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceTypeImperialYardFactory.class);
    private static final DistanceTypeImperialYard INSGANCE = new DistanceTypeImperialYard();

    public static DistanceTypeImperialYard getInstance() {
        LOG.info("Factory of Distance Type Imperial for Yard instance: [{}]", INSGANCE);
        return INSGANCE;
    }
}
