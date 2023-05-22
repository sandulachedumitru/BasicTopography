package com.hardcodacii.domain;

import com.hardcodacii.domain.factory.AngleFactory;
import com.hardcodacii.domain.factory.DistanceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesPolar extends Coordinates {
	private Angle angle = AngleFactory.getDefaultInstance();
	private Distance distance = DistanceFactory.getDefaultInstance();
}
