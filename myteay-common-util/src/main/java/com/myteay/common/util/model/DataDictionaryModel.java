/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.util.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 数据字典模型
 * 
 * @author min.weixm
 * @version $Id: DataDictionaryModel.java, v 0.1 Feb 8, 2018 9:32:51 PM min.weixm Exp $
 */
public class DataDictionaryModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 2928697187611143920L;

    /** 字典关键字 */
    private String            bizKey;

    /** 字典内容 */
    private String            value;

    /**
     * Getter method for property <tt>bizKey</tt>.
     * 
     * @return property value of bizKey
     */
    public String getBizKey() {
        return bizKey;
    }

    /**
     * Setter method for property <tt>bizKey</tt>.
     * 
     * @param bizKey value to be assigned to property bizKey
     */
    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    /**
     * Getter method for property <tt>value</tt>.
     * 
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     * 
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
