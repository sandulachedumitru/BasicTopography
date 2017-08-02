package com.miticorp.topography;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.miticorp.topography.basic.model.Angle;
import com.miticorp.topography.basic.model.AngleType;
import com.miticorp.topography.basic.model.AngleTypeCentesimal;
import com.miticorp.topography.basic.model.CoordinatesPolar;
import com.miticorp.topography.basic.model.CoordinatesRectangular;
import com.miticorp.topography.basic.model.Distance;
import com.miticorp.topography.basic.model.Point;
import com.miticorp.topography.basic.utils.Utils;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
    {
        
        double cent2rad = 2*Math.PI/400;
        double rad2cent = 400/(2*Math.PI);
        double rad = Math.PI/2;
        double cent = rad * rad2cent;
        double dN = 123.456, dE = 45.678;
        System.out.println( "rad = " + rad + " --> " + cent);
        System.out.println( "sin(" + rad + ") = " + Math.sin(rad));
        System.out.println( "sin(" + cent + ") = " + Math.sin(cent * cent2rad));
        System.out.println("arctang(dn/de) = " + Math.atan(dN/dE) * rad2cent);
        System.out.println("cent2rad = " + Angle.CENTESIMAL_TO_RADIAN);
        
        
        
        double base = 2, pow = 20;
        System.out.println(base + "^" + pow + " = " + Math.pow(base, pow));
        
        
        Angle angle = new Angle(123.4567, new AngleTypeCentesimal());
        Distance<CoordinatesRectangular> distance = new Distance<>(16D);
        CoordinatesPolar coordinatesPolar = new CoordinatesPolar(angle, distance);
        double r = coordinatesPolar.getDistance().getValue();
        double thet = coordinatesPolar.getAngle().getValue();
        System.out.println("P(r,thet) = P(" + r + "," + thet + ")");
        
        double north1 = 4003446.030, east1 = 435488.969, height1 = 101.101;
        double north2 = 4003249.436, east2 = 435452.215, height2 = 101.101;
        CoordinatesRectangular coord1 = new CoordinatesRectangular(north1, east1, height1);
        CoordinatesRectangular coord2 = new CoordinatesRectangular(north2, east2, height2);
        Point<CoordinatesRectangular> from = new Point<>(coord1, 1D, "first");
        Point<CoordinatesRectangular> to = new Point<>(coord2, 1D, "second");
        AngleType angleType = new AngleTypeCentesimal();
        coordinatesPolar = Utils.calculateCoordinatePolar(from, to, angleType);
        r = coordinatesPolar.getDistance().getValue();
        thet = coordinatesPolar.getAngle().getValue();
        DecimalFormat df3 = new DecimalFormat(".###m"); df3.setRoundingMode(RoundingMode.UP);
        DecimalFormat df4 = new DecimalFormat(".####g"); df4.setRoundingMode(RoundingMode.UP);
        System.out.println("P(r,thet) = P(" + df3.format(r) + "," + df4.format(thet) + ")");
    }
    
    public static void main() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("");
    	context.refresh();
    	
    	context.close();
    }
}
