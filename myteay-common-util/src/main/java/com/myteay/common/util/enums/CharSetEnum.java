/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 字符编码枚举类
 * 
 * @author min.weixm
 * @version $Id: CharSetEnum.java, v 0.1 Oct 29, 2017 11:53:56 AM min.weixm Exp $
 */
public enum CharSetEnum implements EnumMessage {
                                                /**  */
                                                GBK("GBK", "GBK"),

                                                /**  */
                                                GB2312("gb2312", "gb2312"),

                                                /**  */
                                                ISO_8859_1("ISO-8859-1", "ISO-8859-1"),

    ;
    /** value */
    private final String value;

    /** message */
    private final String message;

    /**
     * 私有构造方法
     * @param code
     * @param description
     */
    private CharSetEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    /** 
     * @see com.myteay.common.util.enums.EnumMessage#getValue()
     */
    public String getValue() {
        return value;
    }

    /** 
     * @see com.myteay.common.util.enums.EnumMessage#getMessage()
     */
    public String getMessage() {
        return message;
    }

    /**
     * 通过值获取枚举对象
     * @param value
     * @return
     */
    public static CharSetEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CharSetEnum type : values()) {
            if (type.getValue().equals(value))
                return type;
        }
        return null;

    }

    /**
     * 通过枚举<code>value</code>获得枚举
     * 
     * @param value
     * @return
     */
    public static CharSetEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (CharSetEnum result : values()) {
            if (result.getValue().equals(value)) {
                return result;
            }
        }
        return null;
    }

    /**
     * 通过枚举<code>name</code>获得枚举
     * 
     * @param message
     * @return
     */
    public static CharSetEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CharSetEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}