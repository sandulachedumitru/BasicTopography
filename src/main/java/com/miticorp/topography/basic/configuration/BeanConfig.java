package com.miticorp.topography.basic.configuration;

import com.miticorp.topography.basic.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AngleType angleTypeCentesimal() {
        return new AngleTypeCentesimal();
    }

    @Bean
    public AngleType angleTypeHexadecimal() {
        return new AngleTypeHexadecimal();
    }

    @Bean
    public AngleType angleTypeRadian() {
        return new AngleTypeRadian();
    }

    @Bean
    public DistanceType distanceTypeMetricKilometer() {
        return new DistanceTypeMetricKilometer();
    }

    @Bean
    public DistanceType distanceTypeMetricMeter() {
        return new DistanceTypeMetricMeter();
    }

    @Bean
    public DistanceType distanceTypeMetricCentimeter() {
        return new DistanceTypeMetricCentimeter();
    }

    @Bean
    public DistanceType distanceTypeMetricMillimeter() {
        return new DistanceTypeMetricMillimeter();
    }

    @Bean
    public DistanceType distanceTypeImperialMile() {
        return new DistanceTypeImperialMile();
    }

    @Bean
    public DistanceType distanceTypeImperialYard() {
        return new DistanceTypeImperialYard();
    }

    @Bean
    public DistanceType distanceTypeImperialInch() {
        return new DistanceTypeImperialInch();
    }
}
