package com.miticorp.topography;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

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

        
        System.out.println( "rad = " + rad + " --> " + cent);
        System.out.println( "sin(" + rad + ") = " + Math.sin(rad));
        System.out.println( "sin(" + cent + ") = " + Math.sin(cent * cent2rad));
    }
    
    public static void main() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("");
    	context.refresh();
    }
}
