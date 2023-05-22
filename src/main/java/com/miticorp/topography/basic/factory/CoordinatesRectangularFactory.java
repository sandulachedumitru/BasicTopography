package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.CoordinatesRectangular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 22/04,19.
 * e-mail: sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularFactory {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularFactory.class);

    public static CoordinatesRectangular getInstance() {
        CoordinatesRectangular INSTANCE = new CoordinatesRectangular();
        LOG.info("Factory for Rectangular Coordinates instance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
