package com.hardcodacii.domain;

import com.hardcodacii.service.AngleService;
import com.hardcodacii.utils.Utils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.hardcodacii.environment.CommonConstants.ANGLE_TOLERANCE_COMPARISON;
import static com.hardcodacii.environment.CommonConstants.INVALID_ELEMENT;

/**
 * Base class for angular representation (angular value, angular type ex: grad, deg, rad)
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Angle extends GeometricElements {
	// Fields exposed by Angle by getters and setters
	private Double value;
	private Point vertex, p1, p2;

	// Constructors
	public Angle(Double value, String name) {
		this.value = value;
		this.name = name;
	}

	public Angle(Point vertex, Point point1, Point point2, String name) {
		this.vertex = vertex;
		this.p1 = point1;
		this.p2 = point2;
		this.name = name;
		this.value = AngleService.getAngleValue(vertex, p1, p2).orElse(INVALID_ELEMENT);
	}

	// setters
	public void setValue(Double angleValue) {
		Double valueOld = this.value;
		this.value = angleValue;
		if (vertex != null && p1 != null && p2 != null) {
			boolean areEquals = Utils.areEqual(valueOld, angleValue, ANGLE_TOLERANCE_COMPARISON);
			if (!areEquals) {
				p1 = p2 = null;
				log.info("Set Value of Angle from {} into {}. Old angle value was dependent on P1 and P2 so they have been set to null.", valueOld, angleValue);
			}
		} else {
			log.info("Set Value of the Angle to {}", angleValue);
		}
	}

	public void setVertex(Point vertex) {
		this.vertex = vertex;
		if (vertex != null && p1 != null && p2 != null) {
			Double valueOld = value;
			value = AngleService.getAngleValue(vertex, p1, p2).orElse(INVALID_ELEMENT);
			log.info("Set point Vertex of Angle to {}. Angle value has been modified from {} into {}:", vertex, valueOld, value);
		} else {
			log.info("Set point Vertex of Angle to {}", vertex);
		}
	}

	public void setP1(Point point) {
		this.p1 = point;
		if (vertex != null && p1 != null && p2 != null) {
			Double valueOld = value;
			value = AngleService.getAngleValue(vertex, p1, p2).orElse(INVALID_ELEMENT);
			log.info("Set point Point1 of Angle to {}. Angle value has been modified from {} into {}:", point, valueOld, value);
		} else {
			log.info("Set point Point1 of Angle to {}", point);
		}
	}

	public void setP2(Point point) {
		this.p2 = point;
		if (vertex != null && p1 != null && p2 != null) {
			Double valueOld = value;
			value = AngleService.getAngleValue(vertex, p1, p2).orElse(INVALID_ELEMENT);
			log.info("Set point Point2 of Angle to {}. Angle value has been modified from {} into {}:", point, valueOld, value);
		} else {
			log.info("Set point Point2 of Angle to {}", point);
		}
	}
}
