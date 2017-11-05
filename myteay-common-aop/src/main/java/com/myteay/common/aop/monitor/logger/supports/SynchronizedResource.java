/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.supports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ͬ����Դ�Ŀ�����. ���������Դ��ͬ��������ͷš� 
 * 
 * @author min.weixm
 * @version $Id: SynchronizedResource.java, v 0.1 Nov 5, 2017 9:36:08 PM min.weixm Exp $
 */
public class SynchronizedResource {

    private static final Logger logger               = LoggerFactory
        .getLogger(SynchronizedResource.class);

    /** ͬʱ�������Դ�� */
    private int                 resourceCount        = 5;

    /** ��ʱռ�õ���Դ�� */
    private int                 currentResourceCount = 0;

    /** Ĭ����Դ */
    private String              resourceName         = "DEFAULT";

    public synchronized boolean applyResource() {
        if (currentResourceCount < resourceCount) {
            currentResourceCount++;

            if (logger.isDebugEnabled()) {
                logger.debug("��Դ[" + resourceName + "], ����[" + currentResourceCount + "/"
                             + resourceCount + "], ����[�ɹ�]");
            }

            return true;
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("��Դ[" + resourceName + "], ����[" + currentResourceCount + "/"
                            + resourceCount + "], ����[ʧ��]");
            }

            return false;
        }
    }

    public synchronized void releaseResource() {
        currentResourceCount--;

        if (logger.isDebugEnabled()) {
            logger.debug("��Դ[" + resourceName + "], ����[" + currentResourceCount + "/"
                         + resourceCount + "], �ͷ�[�ɹ�]");
        }
    }

    /**
     * @param resourceCount
     */
    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

}
