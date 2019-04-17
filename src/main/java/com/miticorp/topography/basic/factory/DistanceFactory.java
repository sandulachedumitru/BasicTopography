package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sandualche Dumitru on 15/04/19.
 * e-mail: sandulachedumitru@hotmail.com
 */
public class DistanceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DistanceFactory.class);

    private static Distance INSTANCE = new Distance();

    public static Distance getInstance() {
        LOG.info("Created default Distance: [{}]", INSTANCE);
        return INSTANCE;
    }
}
