package com.miticorp.topography.service;

import com.miticorp.topography.basic.builder.CoordinatesRectangularBuilder;
import com.miticorp.topography.basic.builder.PointBuilder;
import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.DistanceType;
import com.miticorp.topography.basic.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Dumitru Sandulache on 28/03/19.
 * sandulachedumitru@hotmail.com
 */
@Service
public class BasicService {
    private static final Logger LOG = LoggerFactory.getLogger(BasicService.class);

    ApplicationContext ctx = new AnnotationConfigApplicationContext();

    private final DistanceType distanceTypeImperialYardBean;
    private final AngleType angleTypeHexadecimalBean;
    private final CoordinatesRectangularBuilder coordinatesRectangularBuilder;
    private final PointBuilder pointBuilder;
    private BasicService(DistanceType distanceTypeImperialYardBean, AngleType angleTypeHexadecimalBean, CoordinatesRectangularBuilder coordinatesRectangularBuilder, PointBuilder pointBuilder) {
        this.distanceTypeImperialYardBean = distanceTypeImperialYardBean;
        this.angleTypeHexadecimalBean = angleTypeHexadecimalBean;
        this.coordinatesRectangularBuilder = coordinatesRectangularBuilder;
        this.pointBuilder = pointBuilder;
    }
//    PointBuilder pointBuilder = ctx.getBean("pointBuilder", PointBuilder.class);

    public void pointService() {
        double north1 = 4003446.030, east1 = 435488.969, height1 = 101.101;
        double north2 = 4003249.436, east2 = 435452.215, height2 = 101.101;

        CoordinatesRectangular coordinatesRectangular = coordinatesRectangularBuilder
                .setNorth(north1)
                .setEast(east1)
                .setHeight(height1)
//                .setAngleType(angleTypeHexadecimalBean)
//                .setDistanceType(distanceTypeImperialYardBean)
                .build();
        LOG.info("Rectangular coordinate [{}]", coordinatesRectangular);
        Point point = pointBuilder.setCoord(coordinatesRectangular).setName("Base Point").build();
        LOG.info("Point --> DistanceType:{}, \tAngleType:{}", point.getCoord().getDistanceType(), point.getCoord().getAngleType());

        //TODO test CoordinatesGeographicBuilder
    }
}
