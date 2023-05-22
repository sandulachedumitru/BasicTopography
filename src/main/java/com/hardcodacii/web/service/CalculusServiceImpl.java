package com.hardcodacii.web.service;

import com.hardcodacii.domain.Point;
import com.hardcodacii.web.dto.PointsX3DTO;
import com.hardcodacii.web.service.subservice.PerpendicularFromPointToLine;
import com.hardcodacii.web.service.subservice.PointPositionOnLine;
import com.hardcodacii.web.service.subservice.PointPositionOnParabola;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Service
public class CalculusServiceImpl implements CalculusService {
	@Override
	public Optional<PointPositionOnParabola.EPointParabolaPosition> checkPointPositionOnVerticalParabola(PointsX3DTO dto, Point P) {
		return PointPositionOnParabola.checkPointPositionOnParabola(dto, P);
	}

	@Override
	public Optional<Integer> checkPointPositionOnLine(PointsX3DTO dto) {
		return PointPositionOnLine.checkPointPositionOnLine(dto);
	}

	@Override
	public Optional<Point> getPerpendicularFromPointToLine(PointsX3DTO dto) {
		return PerpendicularFromPointToLine.getPerpendicularFromPointToLine(dto);
	}
}
