package com.hardcodacii.web.controller;

import com.hardcodacii.domain.Point;
import com.hardcodacii.domain.factory.PointFactory;
import com.hardcodacii.web.dto.ParabolaAndPointDTO;
import com.hardcodacii.web.dto.PointsX3DTO;
import com.hardcodacii.web.service.CalculusService;
import com.hardcodacii.web.service.subservice.PointPositionOnParabola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculus")
public class CalculusController {

	private final CalculusService service;

	@Autowired
	public CalculusController(final CalculusService service) {
		this.service = service;
	}

	@GetMapping("/point-on-line")
	ResponseEntity<Integer> checkPointOnLine(@RequestBody PointsX3DTO pointsX3DTO) {
		return ResponseEntity.of(service.checkPointPositionOnLine(pointsX3DTO));
	}

	@GetMapping("/perpendicular-to-line")
	ResponseEntity<Point> getPerpendicularFromPointToLine(@RequestBody PointsX3DTO pointsX3DTO) {
		return ResponseEntity.of(service.getPerpendicularFromPointToLine(pointsX3DTO));
	}

	@GetMapping("/point-on-vertical-parabola")
	ResponseEntity<PointPositionOnParabola.EPointParabolaPosition> checkPointOnVerticalParabola(@RequestBody ParabolaAndPointDTO dto) {
		return ResponseEntity.of(service.checkPointPositionOnVerticalParabola(dto.getParabola(), dto.getPoint()));
	}

	// the next end-points will be removed

	@GetMapping("/info/defaultPoint")
	ResponseEntity<Point> getDefaultPoint() {
		return ResponseEntity.ok(PointFactory.getDefaultInstance());
	}

	@GetMapping("/info/defaultPoints3xDTO")
	ResponseEntity<PointsX3DTO> getDefaultPoints3xDTO() {
		Point point = PointFactory.getDefaultInstance();
		return ResponseEntity.ok(new PointsX3DTO(point, point, point));
	}
}
