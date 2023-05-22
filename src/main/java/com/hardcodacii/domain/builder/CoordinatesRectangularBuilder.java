package com.hardcodacii.domain.builder;

import com.hardcodacii.domain.CoordinatesRectangular;
import com.hardcodacii.domain.factory.CoordinatesRectangularFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public final class CoordinatesRectangularBuilder {

	private final CoordinatesRectangular coordRect = CoordinatesRectangularFactory.getDefaultInstance();

	private CoordinatesRectangularBuilder() {
	}

	public static CoordinatesRectangularBuilder getBuilder() {
		return new CoordinatesRectangularBuilder();
	}

	public CoordinatesRectangular build() {
		log.info("Build rectangular coordinate with values: North[{}], East[{}], Height[{}]", coordRect.getNorth(), coordRect.getEast(), coordRect.getHeight());
		return coordRect;
	}

	public CoordinatesRectangularBuilder setNorth(double north) {
		coordRect.setNorth(north);
		log.info("Set North=[{}] in rectangular coordinate", north);
		return this;
	}

	public CoordinatesRectangularBuilder setEast(double east) {
		coordRect.setEast(east);
		log.info("Set East=[{}] in rectangular coordinate", east);
		return this;
	}

	public CoordinatesRectangularBuilder setHeight(double height) {
		coordRect.setHeight(height);
		log.info("Set Height=[{}] in rectangular coordinate", height);
		return this;
	}
}
