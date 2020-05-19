/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.myteay.common.async.event.MtEvent;

/**
 * 事件执行抽象接口
 * 
 * @author min.weixm
 * @version $Id: EventListener.java, v 0.1 May 19, 2020 10:14:42 AM min.weixm Exp $
 */
public abstract class EventListener<T, K> implements InitializingBean {

    /** 日志 */
    public static final Logger            logger = Logger.getLogger(EventListener.class);

    /** 监听器描述文件缓存 */
    private ListenerDescriptorCache<T, K> listenerDescriptorCache;

    /** 监听器描述文件 */
    private ListenerDescriptor<T, K>      descriptor;

    /**
     * 判断是否处理该事件
     * @param event 待处理事件
     * @return 返回true，表示需要处理该事件；返回false，表示不处理该事件
     */
    public boolean aboutToHandleEvent(MtEvent<K> event) {
        return true;
    }

    /**
     * 处理事件
     * @param event 待处理的事件
     * @return      用于支持同步的返回值
     */
    public abstract T handleEvent(MtEvent<K> event);

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            listenerDescriptorCache.registerExtension(descriptor, this);
        } catch (Exception e) {
            logger.error("当前事件描述文件注册失败：descriptor=" + descriptor, e);
        }
    }

    /**
     * Setter method for property <tt>listenerDescriptorCache</tt>.
     * 
     * @param listenerDescriptorCache value to be assigned to property listenerDescriptorCache
     */
    public void setListenerDescriptorCache(ListenerDescriptorCache<T, K> listenerDescriptorCache) {
        this.listenerDescriptorCache = listenerDescriptorCache;
    }

    /**
     * Setter method for property <tt>descriptor</tt>.
     * 
     * @param descriptor value to be assigned to property descriptor
     */
    public void setDescriptor(ListenerDescriptor<T, K> descriptor) {
        this.descriptor = descriptor;
    }
}
