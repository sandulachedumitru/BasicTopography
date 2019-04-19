package com.miticorp.topography.basic.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 
 * Base class for point reprezentation.
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 *
 */
public class Point extends GeometricElements {
	private static final Logger LOG = LoggerFactory.getLogger(Point.class);

	// Fields exposed by Point by getters and setters
	private Coordinates coord;

	{
		scale = new Scale();
		name = "";
	}

	// Constructors
	public Point() {}
	public Point(Coordinates coord, Scale scale, String name) {
		this.coord = coord;
		this.scale = scale;
		this.name = name;
	}

	// Getters and Setters
	public Coordinates getCoord() {
		return coord;
	}
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	// hash
	@Override
	public int hashCode() {
		return Objects.hash(coord);
	}

	// equals
	/**
	 * Two points are equals if their coordinates are equals. They could have different name and scale factor.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return Objects.equals(coord, point.coord);
	}

	@Override
	public String toString() {
		return "Point{" +
				"coord=" + coord +
				", scale=" + scale +
				", name='" + name + '\'' +
				'}';
	}
}
