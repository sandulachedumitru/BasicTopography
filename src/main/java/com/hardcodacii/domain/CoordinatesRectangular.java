package com.hardcodacii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CoordinatesRectangular extends Coordinates {
	private double north, east, height;
}
