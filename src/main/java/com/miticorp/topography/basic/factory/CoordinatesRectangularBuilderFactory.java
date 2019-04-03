package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 22/03/2019.
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
