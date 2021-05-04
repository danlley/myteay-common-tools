/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.util.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

/**
 * 使用ToStringBuilder实现的ToString工具类，用于没有覆写toString方法的对象
 * 
 * @author min.weixm
 * @version $Id: ToStringUtil.java, v 0.1 Dec 25, 2017 10:56:51 PM min.weixm Exp $
 */
public class ToStringUtil {

    /**
     * 以ToStringStyle.SHORT_PREFIX_STYLE类型返回参数对象转换后的字符串
     * @param object
     * @return
     */
    public static String toShortString(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 以ToStringStyle.SHORT_PREFIX_STYLE类型返回参数List转换后的字符串
     * @param list
     * @return
     */
    public static String listToString(List<?> list) {

        StringBuffer buffer = new StringBuffer("List={");
        if (!CollectionUtils.isEmpty(list)) {
            for (Object obj : list) {
                buffer.append(toShortString(obj)).append(", ");
            }
        }
        return buffer.substring(0, buffer.length() - 2) + "}";
    }

    /**
     * 以ToStringStyle.SHORT_PREFIX_STYLE类型返回参数Map转换后的字符串
     * @param map
     * @return
     */
    public static String mapToString(Map<String, ?> map) {
        StringBuffer buffer = new StringBuffer("Map={");
        if (!CollectionUtils.isEmpty(map)) {
            for (String key : map.keySet()) {
                buffer.append("[").append(key).append("=>").append(ToStringBuilder.reflectionToString(map.get(key), ToStringStyle.SHORT_PREFIX_STYLE)).append("]");
            }
            buffer.append("}");
        }

        return buffer.toString();
    }
}
