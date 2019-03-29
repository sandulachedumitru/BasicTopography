package com.miticorp.topography.service;

import com.miticorp.topography.BasicTopographyBootApplication;
import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilderFactory;
import com.miticorp.topography.basic.builder.PointBuilderFactory;
import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.DistanceType;
import com.miticorp.topography.basic.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dumitru Sandulache on 28/3/19.
 * sandulachedumitru@hotmail.com
 */
@Service
public class BasicService {
    private static final Logger LOG = LoggerFactory.getLogger(BasicTopographyBootApplication.class);

    @Autowired
    private DistanceType distanceTypeImperialYard;

    @Autowired
    private AngleType angleTypeHexadecimal;

    public void pointService() {
        double north1 = 4003446.030, east1 = 435488.969, height1 = 101.101;
        double north2 = 4003249.436, east2 = 435452.215, height2 = 101.101;

        CoordinatesRectangular coordinatesRectangular = CoordinatesRectangularBuilderFactory.getBuilder()
                .setNorth(north1)
                .setEast(east1)
                .setHeight(height1)
//                .setAngleType(angleTypeHexadecimal)
//                .setDistanceType(distanceTypeImperialYard)
                .build();
        LOG.info("Rectangular coordinate [{}]", coordinatesRectangular);
        Point<CoordinatesRectangular> point = PointBuilderFactory.getBuilder().setCoord(coordinatesRectangular).setName("Base Point").build();
        LOG.info("Point --> DistanceType:{}, \tAngleType:{}", point.getCoord().getDistanceType(), point.getCoord().getAngleType());
    }
}