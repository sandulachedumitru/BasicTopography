package com.hardcodacii.web.dto;

import com.hardcodacii.domain.Point;
import lombok.*;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ParabolaAndPointDTO {
	private PointsX3DTO parabola;
	private Point point;
}
