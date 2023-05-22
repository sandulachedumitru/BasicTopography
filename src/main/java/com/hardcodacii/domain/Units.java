package com.hardcodacii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.hardcodacii.environment.CommonConstants.*;

/**
 * Units management
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Units {
	private EAngleType angleType = DEFAULT_ANGLE_TYPE;
	private IDistanceType distanceType = DEFAULT_DISTANCE_TYPE;
	private EDistanceType generalDistanceType = DEFAULT_GENERAL_DISTANCE_TYPE;
	private boolean clockwise = DEFAULT_CLOCKWISE;
}
