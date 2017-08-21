package com.overactiveinc.oarestapi.utils;

/**
 * Created by slaport on 11/22/16.
 */

public class StringUtils {

    /**
     * Returns the true if the string is null or empty
     *
     * @param str The string value
     * @return True if it is null or empty
     */
    public static boolean isEmpty(String str) {
        boolean result = false;
        if ((str == null) || "".equals(str.trim()) || "null".equals(str.trim())) {
            result = true;
        }
        return result;
    }

    /**
     * Returns the true if the string is not null or empty
     *
     * @param str The string value
     * @return True if it is not null or empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Returns the string without space
     *
     * @param value The string value
     * @return The string without space
     */
    public static String nullTrim(String value) {
        return (value != null ? value.trim() : null);
    }

    /**
     * Returns true if the given letter appears more than once in the given string
     *
     * @param s
     * @param letter
     * @return
     */
    public static boolean containsCharMoreThanOnce(String s, char letter) {
        int firstIndex = s.indexOf(letter);
        return firstIndex > -1 && s.indexOf(letter, firstIndex + 1) > -1;
    }
}
