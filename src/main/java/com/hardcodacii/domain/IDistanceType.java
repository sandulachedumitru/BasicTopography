package com.hardcodacii.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hardcodacii.domain.EDistanceType.EDistanceTypeImperial;
import com.hardcodacii.domain.EDistanceType.EDistanceTypeMetric;

/**
 * Marker interface for subtypes of EDistanceType
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@Type(value = EDistanceTypeImperial.class, name = "DistanceType:Imperial"),
		@Type(value = EDistanceTypeMetric.class, name = "DistanceType:Metric")})
public interface IDistanceType {
	double getConversionFactor();
}
