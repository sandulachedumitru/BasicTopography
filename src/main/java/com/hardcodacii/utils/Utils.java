package com.hardcodacii.utils;

import com.hardcodacii.environment.CommonConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Utils {
	public static String getRandString(int length) {
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		while (sb.length() < length) { // length of the random string.
			int index = (int) (rnd.nextFloat() * alphaNumeric.length());
			sb.append(alphaNumeric.charAt(index));
		}
		return sb.toString();
	}

	public static String getRandString() {
		return getRandString(100);
	}

	public static boolean areEqual(Double val1, Double val2, double TOLERANCE_COMPARISON) {
		if (TOLERANCE_COMPARISON < 0 || 1 <= TOLERANCE_COMPARISON)
			TOLERANCE_COMPARISON = CommonConstants.DOUBLE_TOLERANCE_COMPARISON;
		if (val1 != null && val2 != null)
			return (val1 < val2 + TOLERANCE_COMPARISON && val1 > val2 - TOLERANCE_COMPARISON);
		return false;
	}

	public static double roundAndFormat(double val, int precision) {
		int defaultPrecisionValue = 10;

		if (precision < 0) return val;
		if (precision > defaultPrecisionValue) precision = defaultPrecisionValue;

		return new BigDecimal(val).setScale(precision, RoundingMode.HALF_UP).doubleValue();
	}
}
