package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.Angle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sandualche Dumitru on 15/04/19.
 * e-mail: sandulachedumitru@hotmail.com
 */
public class AngleFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AngleFactory.class);

    private static Angle INSTANCE = new Angle();

    public static Angle getInstance() {
        LOG.info("Created default Angle: [{}]", INSTANCE);
        return INSTANCE;
    }
}
