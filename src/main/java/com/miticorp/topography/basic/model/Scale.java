package com.miticorp.topography.basic.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Dumitru Sandulache on 3/22/19.
 * sandulachedumitru@hotmail.com
 */
@Component
@Scope("prototype")
public class Scale {
    private double scale = 1D; // value 1 is by default
    //TODO analyze if scale could be 0;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "scale=" + scale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scale scale1 = (Scale) o;

        return Double.compare(scale1.scale, scale) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(scale);
        return (int) (temp ^ (temp >>> 32));
    }
}
