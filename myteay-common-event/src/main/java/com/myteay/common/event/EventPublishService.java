/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.common.event;

import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;

/**
 * 事件发布组件v2.0版本，在v1.0的基础上对组件进行了完全泛型化支持
 * 
 * @author min.weixm
 * @version $Id: EventPublishService.java, v 0.1 May 18, 2020 6:23:40 PM min.weixm Exp $
 */
public interface EventPublishService<T, K> {

    /**
     * 发布事件
     * 
     * @param event     异步事件模型
     */
    public T publishEvent(MtEvent<K> event) throws MtEventException;
}
