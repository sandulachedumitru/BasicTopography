package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 22/03/2019.
 * sandulachedumitru@hotmail.com
 */
public class CoordinatesRectangularBuilderFactory {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesRectangularBuilderFactory.class);

    public static CoordinatesRectangularBuilder getBuilder() {
        LOG.info("Creates coordinate rectangular builder");
        return new CoordinatesRectangularBuilder();
    }
}
