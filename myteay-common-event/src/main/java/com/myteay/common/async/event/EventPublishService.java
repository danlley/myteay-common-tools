/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.async.event;

/**
 * �첽�¼�ִ�з���Ĭ��֧��ͬ����
 * 
 * @author min.weixm
 * @version $Id: EventPublishService.java, v 0.1 Oct 28, 2017 11:21:57 PM min.weixm Exp $
 */
public interface EventPublishService<T> {

    /**
     * �����¼�
     * 
     * @param event     �첽�¼�ģ��
     */
    public T publishEvent(MtEvent<?> event) throws MtEventException;
}
