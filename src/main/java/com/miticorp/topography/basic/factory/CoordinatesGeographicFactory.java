package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.CoordinatesGeographic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 22/04,19.
 * e-mail: sandulachedumitru@hotmail.com
 */
public class CoordinatesGeographicFactory {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesGeographicFactory.class);

    public static CoordinatesGeographic getInstance() {
        CoordinatesGeographic INSTANCE = new CoordinatesGeographic();
        LOG.info("Factory for Geographic Coordinates instance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
