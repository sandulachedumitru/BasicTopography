package com.miticorp.topography.basic.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 3/22/19.
 * sandulachedumitru@hotmail.com
 */
@Component
public class CoordinatesRectangularBuilderFactory {
    @Autowired
    CoordinatesRectangularBuilder coordinatesRectangularBuilder;

    public CoordinatesRectangularBuilder getBuilder() {
        return coordinatesRectangularBuilder;
    }
}
