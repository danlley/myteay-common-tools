/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.supports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步资源的控制器. 负责控制资源的同步申请和释放。 
 * 
 * @author min.weixm
 * @version $Id: SynchronizedResource.java, v 0.1 Nov 5, 2017 9:36:08 PM min.weixm Exp $
 */
public class SynchronizedResource {

    private static final Logger logger               = LoggerFactory.getLogger(SynchronizedResource.class);

    /** 同时允许的资源数 */
    private int                 resourceCount        = 5;

    /** 当时占用的资源数 */
    private int                 currentResourceCount = 0;

    /** 默认资源 */
    private String              resourceName         = "DEFAULT";

    public synchronized boolean applyResource() {
        if (currentResourceCount < resourceCount) {
            currentResourceCount++;

            if (logger.isDebugEnabled()) {
                logger.debug("资源[" + resourceName + "], 计数[" + currentResourceCount + "/" + resourceCount + "], 申请[成功]");
            }

            return true;
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("资源[" + resourceName + "], 计数[" + currentResourceCount + "/" + resourceCount + "], 申请[失败]");
            }

            return false;
        }
    }

    public synchronized void releaseResource() {
        currentResourceCount--;

        if (logger.isDebugEnabled()) {
            logger.debug("资源[" + resourceName + "], 计数[" + currentResourceCount + "/" + resourceCount + "], 释放[成功]");
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
