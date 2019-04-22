package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.factory.AngleTypeCentesimalFactory;
import com.miticorp.topography.basic.model.AngleTypeRadian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache on 4/12/19
 * e-mail: sandulachedumitru@hotmail.com
 */
public class AngleTypeRadianFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AngleTypeCentesimalFactory.class);
    private static final AngleTypeRadian INSTANCE = new AngleTypeRadian();

    public static AngleTypeRadian getInstance() {
        LOG.info("Angle Type Radian instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
