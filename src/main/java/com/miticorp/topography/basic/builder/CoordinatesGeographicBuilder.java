package com.miticorp.topography.basic.builder;

import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.CoordinatesGeographic;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.DistanceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 12/04/19.
 * e-mail: sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
public class CoordinatesGeographicBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CoordinatesGeographicBuilder.class);

    private double latitude, longitude, height;
    private DistanceType distanceType;
    private AngleType angleType;

    @Autowired
    private CoordinatesGeographic coordinatesGeographic;

    public CoordinatesGeographic build() {
        if (distanceType == null) distanceType = coordinatesGeographic.getDistanceType();
        if (angleType == null) angleType = coordinatesGeographic.getAngleType();

        LOG.info("Build geographic coordinate with values: default Latitude[{}], default Longitude[{}], default Height[{}], default DistanceType[{}], default AngleType[{}]", latitude, longitude, height, distanceType, angleType);

        return new CoordinatesGeographic(latitude, longitude, height, distanceType, angleType);
    }

    public CoordinatesGeographicBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        LOG.info("Set Latitude[{}] in a geographic coordinate", latitude);

        return this;
    }

    public CoordinatesGeographicBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        LOG.info("Set Longitude[{}] in a geographic coordinate", longitude);

        return this;
    }

    public CoordinatesGeographicBuilder setHeight(double height) {
        this.height = height;
        LOG.info("Set Height[{}] in a geographic coordinate", height);

        return this;
    }

    public CoordinatesGeographicBuilder setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
        LOG.info("Set DistanceType[{}] in a geographic coordinate", distanceType);

        return this;
    }

    public CoordinatesGeographicBuilder setAngleType(AngleType angleType) {
        this.angleType = angleType;
        LOG.info("Set AngleType[{}] in a geographic coordinate", angleType);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinatesGeographicBuilder that = (CoordinatesGeographicBuilder) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (Double.compare(that.height, height) != 0) return false;
        if (distanceType != null ? !distanceType.equals(that.distanceType) : that.distanceType != null) return false;
        return angleType != null ? angleType.equals(that.angleType) : that.angleType == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (distanceType != null ? distanceType.hashCode() : 0);
        result = 31 * result + (angleType != null ? angleType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoordinatesGeographicBuilder{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", distanceType=" + distanceType +
                ", angleType=" + angleType +
                '}';
    }
}
