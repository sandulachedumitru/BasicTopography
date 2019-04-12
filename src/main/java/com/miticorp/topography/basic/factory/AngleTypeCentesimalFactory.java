package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.AngleTypeCentesimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dumitru Sandulache
 * e-mail: sandulachedumitru@hotmail.com
 */

public class AngleTypeCentesimalFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AngleTypeCentesimalFactory.class);
    private static final AngleTypeCentesimal INSTANCE = new AngleTypeCentesimal();

    public static AngleTypeCentesimal getInstance() {
        LOG.info("Angle Type Centesimal instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
