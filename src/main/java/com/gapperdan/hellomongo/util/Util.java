package com.gapperdan.hellomongo.util;

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

}
