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
    private double scaleFactor = 1D; // value 1 is by default
    //TODO analyze if scaleFactor could be 0;

    public Scale(){}
    public Scale(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }
    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "scaleFactor=" + scaleFactor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scale scale1 = (Scale) o;

        return Double.compare(scale1.scaleFactor, scaleFactor) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(scaleFactor);
        return (int) (temp ^ (temp >>> 32));
    }
}
