package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.builder.PointBuilder;

/**
 * Created by Dumitru Sandulache on 22/03/2019.
 * sandulachedumitru@hotmail.com
 */
public class PointBuilderFactory {
    public static PointBuilder getBuilder() {
        return new PointBuilder();
    }
}
