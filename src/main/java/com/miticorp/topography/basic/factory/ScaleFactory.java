package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.Scale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScaleFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ScaleFactory.class);

    public static Scale getInstance() {
        Scale INSTANCE = new Scale();
        LOG.info("Factory for Scale instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
