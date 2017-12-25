/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.async.event;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 可持久化的事件模型
 * 
 * @author min.weixm
 * @version $Id: MtEvent.java, v 0.1 Oct 28, 2017 11:11:01 PM min.weixm Exp $
 */
public class MtEvent<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1598681584260004463L;

    /** 事件主题 */
    private String            topic;

    /** 事件中包含的数据 */
    private T                 data;

    /**
     * 构造方法
     * @param topic     异步事件主题
     * @param data      异步推送数据
     */
    public MtEvent(String topic, T data) {
        this.topic = topic;
        this.data = data;
    }

    /**
     * Getter method for property <tt>topic</tt>.
     * 
     * @return property value of topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method for property <tt>topic</tt>.
     * 
     * @param topic value to be assigned to property topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Getter method for property <tt>data</tt>.
     * 
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     * 
     * @param data value to be assigned to property data
     */
    public void setData(T data) {
        this.data = data;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
