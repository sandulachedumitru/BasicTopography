package com.miticorp.topography.basic.model;

import java.util.Map;

public class Coordinates<T> {
	
	Map<T , Double> dimensions;
	
	protected enum DimensionsAll {
		NORTH,
		EST,
		SOUTH,
		WEST,
		LATITUDE,
		PARALLEL,
		LONGITUDE,
		MERIDIAN,
		X,
		Y,
		Z,
		HEIGHT;
	}
	
	protected enum DimensionsCardinal {
		NORTH,
		EST,
		SOUTH,
		WEST;
	}
	
	protected enum DimensionsRectangularNEH {
		NORTH,
		EST,
		HEIGHT;
	}
	
	protected enum DimensionsRectangularXYZ {
		X,
		Y,
		Z;
	}
	
	protected enum DimensionsGeographicLatLongH {
		LATITUDE,
		LONGITUDE,
		HEIGHT;
	}
	
	protected enum DimensionsGeographicMerParH {
		PARALLEL,
		MERIDIAN,
		HEIGHT;
	}
}
