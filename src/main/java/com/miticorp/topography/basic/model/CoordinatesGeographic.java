package com.miticorp.topography.basic.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CoordinatesGeographic extends Coordinates {
	
	public void test() {
		dimensions = Collections.synchronizedSortedMap(new TreeMap<DimensionsRectangularNEH, Double>());
		dimensions.put(DimensionsRectangularNEH.NORTH, 10D);
	}
	
	


}
