/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.common.event;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;

/**
 * 事件发布组件v2.0版本，在v1.0的基础上对组件进行了完全泛型化支持
 * 
 * @author min.weixm
 * @version $Id: EventPublishServiceImpl.java, v 0.1 May 18, 2020 6:24:55 PM min.weixm Exp $
 */
public class EventPublishServiceImpl<T, K> implements EventPublishService<T, K> {

    /** 日志 */
    public static final Logger            logger = Logger.getLogger(EventPublishServiceImpl.class);

    /** 监听器描述文件缓存 */
    private ListenerDescriptorCache<T, K> listenerDescriptorCache;

    /** 
     * @throws MtEventException 
     * @see com.alipay.promocore.core.model.event.EventPulishService#publishEvent(com.alipay.promocore.core.model.event.MtEvent)
     */
    @Override
    public T publishEvent(MtEvent<K> event) throws MtEventException {

        if (event == null) {
            logger.warn("Event is null，默认不执行");
            return null;
        }

        String topic = event.getTopic();

        ListenerDescriptor<T, K> descriptor = listenerDescriptorCache.getListenerDescriptor(topic);
        if (descriptor == null) {
            logger.warn("扩展描述文件配置不可为空，topic=" + topic);
            throw new MtEventException("扩展描述文件配置不可为空，topic=" + topic);
        }

        //扩展点在开发配置过程已进行校验，这里不需要重复校验
        EventListener<T, K> listener = descriptor.getListener();

        //异步执行处理
        if (descriptor.isAsync()) {

            //扩展点在开发配置过程已进行校验，这里不需要重复校验
            TaskExecutor taskExecutor = descriptor.getTaskExecutor();

            try {
                taskExecutor.execute(new EventListenerExecutor<T, K>(listener, event));
            } catch (RuntimeException e) {
                logger.warn("事件发布器发布事件发生运行时异常，极可能是异步运行时队列大小不够RuntimeException" + event, e);
            } catch (Exception e) {
                logger.warn("事件发布器发布事件时发生异常Exception" + event, e);
            }
        }
        //同步执行处理
        else {
            try {
                if (listener.aboutToHandleEvent(event)) {
                    return listener.handleEvent(event);
                }
            } catch (Exception e) {
                logger.warn("同步执行监听器失败Exception，" + e.getMessage(), e);
            }
        }

        //默认情况下返回值为空，只有当同步执行的情况下才会有返回值
        return null;

    }

    /**
     * Setter method for property <tt>listenerDescriptorCache</tt>.
     * 
     * @param listenerDescriptorCache value to be assigned to property listenerDescriptorCache
     */
    public void setListenerDescriptorCache(ListenerDescriptorCache<T, K> listenerDescriptorCache) {
        this.listenerDescriptorCache = listenerDescriptorCache;
    }

}
