package com.gapperdan.hellomongo.util;

import java.util.Random;

public class Util {

    /**
     * Formats the name for display by making only the first letter uppercase.
     * @param str
     * @return formatted name for display
     */
    public static String upperFirstCharOnly(String str) {
        String result = str;

        if (str.length() > 0) {
            result = str.substring(0,1).toUpperCase() +
                    str.substring(1).toLowerCase();
        }
        return result;
    }

    /**
     * Generate a random int from 0..upper range (non-inclusive)
     * @param upperRangeNonInclusive
     * @return a random int
     */
    public static String generateRandomNumberString(int upperRangeNonInclusive) {

        if (upperRangeNonInclusive == 0) {
            return "0";
        }

        Random rand = new Random();

        return String.valueOf(rand.nextInt(upperRangeNonInclusive));
    }

}
