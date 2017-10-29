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
}
