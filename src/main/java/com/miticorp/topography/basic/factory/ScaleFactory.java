package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.Scale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScaleFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ScaleFactory.class);
    private static final Scale INSTANCE = new Scale();

    public static Scale getInstance() {
        LOG.info("Factory for Scale instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
