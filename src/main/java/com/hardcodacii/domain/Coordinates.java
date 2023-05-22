package com.hardcodacii.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base class for coordinates
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@Type(value = CoordinatesRectangular.class, name = "Coordinates:Rectangular"),
		@Type(value = CoordinatesPolar.class, name = "Coordinates:Polar")})
public class Coordinates {
}
