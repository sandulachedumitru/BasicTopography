package com.hardcodacii.domain.factory;

import com.hardcodacii.domain.Line;
import com.hardcodacii.domain.Point;
import com.hardcodacii.service.LineService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
public class LineFactory {
	public static Line getDefaultInstance() {
		Point p1 = PointFactory.getDefaultInstance();
		Point p2 = PointFactory.getDefaultInstance();

		Line INSTANCE = new Line(p1, p2, LineService.getNameIfPossible(p1, p2));
		log.info("Factory of LINE. Created default LINE instance: {}", INSTANCE);
		return INSTANCE;
	}
}
