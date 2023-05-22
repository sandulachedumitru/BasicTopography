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
public class PointsX3DTO {
	private Point p1;
	private Point p2;
	private Point p3;
}
