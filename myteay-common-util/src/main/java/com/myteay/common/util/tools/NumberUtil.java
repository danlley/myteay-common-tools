/**
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.myteay.common.util.tools;

import org.apache.commons.lang.StringUtils;

/**
 * 数字转换工具
 * 
 * @author min.weixm
 * @version $Id: NumberUtil.java, v 0.1 Oct 29, 2017 12:20:47 PM min.weixm Exp $
 */
public class NumberUtil {

    public static int convertString2Int(String str, int defaultValue) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * <p>Checks if the String contains only unicode digits.
     * A decimal point is not a unicode digit and returns false.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String ("") will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        int pointCount = 0;
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                if (str.charAt(i) != '.') {
                    return false;
                }
                pointCount++;
                if (i <= 0 || pointCount > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
