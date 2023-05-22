package com.hardcodacii.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base class for point representation
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Point extends GeometricElements {

	// Fields exposed by Point by getters and setters
	private Coordinates coord;

	public Point(Coordinates coord, String name) {
		this.coord = coord;
		this.name = name;
	}
}
