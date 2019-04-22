package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.builder.PointBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 22/03/2019.
 * sandulachedumitru@hotmail.com
 */
public class PointBuilderFactory {
    private static final Logger LOG = LoggerFactory.getLogger(PointBuilderFactory.class);
    private static PointBuilder INSTANCE = new PointBuilder();

    public static PointBuilder getBuilder() {
        LOG.info("Created Point Builder: [{}]", INSTANCE);
        return new PointBuilder();
    }
}
