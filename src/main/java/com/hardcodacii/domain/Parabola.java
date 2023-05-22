package com.hardcodacii.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Parabola extends GeometricElements {
	// parabola properties
	protected Double p; // (distance from vertex to focus) == (distance from directrix to vertex)
	protected Double directrix, axisOfSymmetry, latusRectum;
	protected Point focus, vertex; // F(h, k+p), V(h, k)
	protected Point P1, P2, P3; // points on the parabola
	protected EParabolaAperture aperture;

	protected Parabola() {
	}

	public enum EParabolaAperture {
		UPWARD,
		DOWNWARD,
		RIGHTWARD,
		LEFTWARD,
		GENERALWARD
	}
}
