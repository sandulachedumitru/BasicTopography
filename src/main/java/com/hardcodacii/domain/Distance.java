package com.hardcodacii.domain;

import com.hardcodacii.service.DistanceService;
import com.hardcodacii.utils.Utils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.hardcodacii.environment.CommonConstants.DISTANCE_TOLERANCE_COMPARISON;
import static com.hardcodacii.environment.CommonConstants.INVALID_ELEMENT;

/**
 * Base class for distance representation (distance value, distance point1/point2, distance type (ex; metric, imperial)).
 * Distance extends Shape because a distance is a line between two points.
 *
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Distance extends GeometricElements {

	// Fields exposed by Distance by getters and setters
	private Point from;
	private Point to;
	private Double value;

	/**
	 * Constructor for Distance class.
	 *
	 * @param from start point of the distance
	 * @param to   end point of the distance
	 * @param name name of distance
	 */
	public Distance(Point from, Point to, String name) {
		this.from = from;
		this.to = to;
		this.name = name;
		this.value = DistanceService.getDistanceValue(from, to).orElse(INVALID_ELEMENT);
	}

	/**
	 * Constructor for Distance class.
	 *
	 * @param value value of distance
	 * @param name  name of distance
	 */
	public Distance(Double value, String name) {
		this.value = value;
		this.name = name;
	}

	// setters
	public void setFrom(Point from) {
		this.from = from;
		if (from != null && to != null) {
			Double distanceValueOld = value;
			value = DistanceService.getDistanceValue(from, to).orElse(INVALID_ELEMENT);
			log.info("Set point FROM of Distance to {}. Distance value has been modified from {} into {}", from, distanceValueOld, value);
		} else {
			log.info("Set Point FROM of Distance to {}", from);
		}
	}

	public void setTo(Point to) {
		this.to = to;
		if (from != null && to != null) {
			Double distanceValueOld = value;
			value = DistanceService.getDistanceValue(to, from).orElse(INVALID_ELEMENT);
			log.info("Set point TO of Distance to {}. Distance value has been modified from {} into {}", to, distanceValueOld, value);
		} else {
			log.info("Set point TO of Distance to {}", to);
		}
	}

	public void setValue(Double value) {
		Double distanceValueOld = this.value;
		this.value = value;
		if (from != null && to != null) {
			boolean areEquals = Utils.areEqual(distanceValueOld, value, DISTANCE_TOLERANCE_COMPARISON);
			if (!areEquals) {
				to = null;
				log.info("Set Value of Distance from {} into {}. Old distance value was dependent on P1 and P2, so P2 has been set to null", distanceValueOld, value);
			}
		} else {
			log.info("Set Value of Distance to {}", value);
		}
	}
}
