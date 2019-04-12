package com.miticorp.topography.basic.configuration;

import com.miticorp.topography.basic.model.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfig {

    @Bean
    public AngleType angleTypeCentesimalBean() {
        return new AngleTypeCentesimal();
    }

    @Bean
    public AngleType angleTypeHexadecimalBean() {
        return new AngleTypeHexadecimal();
    }

    @Bean
    public AngleType angleTypeRadianBean() {
        return new AngleTypeRadian();
    }

    @Bean
    public DistanceType distanceTypeMetricKilometerBean() {
        return new DistanceTypeMetricKilometer();
    }

    @Bean
    public DistanceType distanceTypeMetricMeterBean() {
        return new DistanceTypeMetricMeter();
    }

    @Bean
    public DistanceType distanceTypeMetricCentimeterBean() {
        return new DistanceTypeMetricCentimeter();
    }

    @Bean
    public DistanceType distanceTypeMetricMillimeterBean() {
        return new DistanceTypeMetricMillimeter();
    }

    @Bean
    public DistanceType distanceTypeImperialMileBean() {
        return new DistanceTypeImperialMile();
    }

    @Bean
    public DistanceType distanceTypeImperialYardBean() {
        return new DistanceTypeImperialYard();
    }

    @Bean
    public DistanceType distanceTypeImperialInchBean() {
        return new DistanceTypeImperialInch();
    }
}
