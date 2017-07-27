package com.miticorp.topography;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.miticorp.topography.basic.model.Angle;

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
    }
    
    public static void main() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("");
    	context.refresh();
    }
}
