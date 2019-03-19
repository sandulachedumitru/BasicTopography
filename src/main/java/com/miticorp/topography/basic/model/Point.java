package com.miticorp.topography.basic.model;

/**
 * 
 * Base class for point reprezentation. Point extends Shape class.
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 *
 * @param <T> point's coordinates type
 */
public class Point<T extends Coordinates> extends GeometricElements {
	// Fields exposed by Point by getters and setters
	private T coord;
//	private double scaleFactor = 1D;
//	private String name;

	// Constructors
	public Point() {}
	public Point(T coord, double scaleFactor, String name) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
		this.name = name;
	}
	public Point(T coord, double scaleFactor) {
		this.coord = coord;
		this.scaleFactor = scaleFactor;
	}
	public Point(T coord) {
		super();
		this.coord = coord;
	}

	// Getters and Setters
	public synchronized T getCoord() { return coord; }
	public synchronized void setCoord(T coord) { this.coord = coord; }
	
	// hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		return result;
	}
	
	// equals
	/**
	 * Two points are equals if their coordinates are equals. They could have different name and scale factor.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point<?> other = (Point<?>) obj;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		return true;
	}
}
