/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.async.event;

/**
 * 异步事件执行服务（默认支持同步）
 * 
 * @author min.weixm
 * @version $Id: EventPublishService.java, v 0.1 Oct 28, 2017 11:21:57 PM min.weixm Exp $
 */
public interface EventPublishService<T> {

    /**
     * 发布事件
     * 
     * @param event     异步事件模型
     */
    public T publishEvent(MtEvent<?> event) throws MtEventException;
}
