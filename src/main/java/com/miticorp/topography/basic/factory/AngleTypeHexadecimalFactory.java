package com.miticorp.topography.basic.factory;

import com.miticorp.topography.basic.model.AngleTypeHexadecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AngleTypeHexadecimalFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AngleTypeHexadecimalFactory.class);
    private static final AngleTypeHexadecimal INSTANCE = new AngleTypeHexadecimal();

    public static AngleTypeHexadecimal getInstance() {
        LOG.info("Angle Type Hexadecimal instance:[{}]", INSTANCE);
        return INSTANCE;
    }
}
