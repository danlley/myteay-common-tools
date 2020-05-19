/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.event;

import org.apache.log4j.Logger;

import com.myteay.common.async.event.MtEvent;

/**
 * 事件执行器</br>
 * 事件执行器实现了{@link Runnable}接口，被创建后会自动触发执行，创建一个事件执行器时需要包含有该事件被处理时需要的事件对象和事件监听器。
 * 
 * @author min.weixm
 * @version $Id: EventListenerExecutor.java, v 0.1 May 19, 2020 10:14:54 AM min.weixm Exp $
 */
public class EventListenerExecutor<T, K> implements Runnable {

    /** 日志 */
    public static final Logger  logger = Logger.getLogger(EventListenerExecutor.class);

    /** 事件监听器 */
    private EventListener<T, K> eventListener;

    /** 事件模型 */
    private MtEvent<K>          event;

    /**
     * 构造方法
     * @param eventListener     监听器
     * @param event             异步事件模型
     */
    public EventListenerExecutor(EventListener<T, K> eventListener, MtEvent<K> event) {
        super();
        this.eventListener = eventListener;
        this.event = event;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {

        try {
            if (eventListener == null) {
                logger.warn("事件没有找到对应的执行监听器，event" + event);
                return;
            }

            if (eventListener.aboutToHandleEvent(event)) {
                eventListener.handleEvent(event);
            }

        } catch (Exception e) {
            logger.error("事件执行器处理时发生异常 event=" + event + ", eventListener=" + eventListener, e);
        }
    }
}
