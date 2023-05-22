package com.hardcodacii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.hardcodacii.environment.CommonConstants.EMPTY_STRING;

/**
 * Root for all geometric elements0
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeometricElements {
	protected String name = EMPTY_STRING;
}
