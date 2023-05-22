package com.hardcodacii.web.service;

import com.hardcodacii.domain.Point;
import com.hardcodacii.web.dto.PointsX3DTO;
import com.hardcodacii.web.service.subservice.PointPositionOnParabola;

import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface CalculusService {
	Optional<PointPositionOnParabola.EPointParabolaPosition> checkPointPositionOnVerticalParabola(PointsX3DTO dto, Point P);

	Optional<Integer> checkPointPositionOnLine(final PointsX3DTO dao);

	Optional<Point> getPerpendicularFromPointToLine(PointsX3DTO pointsX3DTO);
}
