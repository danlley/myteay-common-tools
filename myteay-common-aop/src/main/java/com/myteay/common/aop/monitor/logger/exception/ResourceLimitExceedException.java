/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

import com.myteay.common.aop.monitor.logger.enums.ResultCode;

/**
 * 资源访问超出限制异常。
 * 
 * @author min.weixm
 * @version $Id: ResourceLimitExceedException.java, v 0.1 Nov 5, 2017 9:35:18 PM min.weixm Exp $
 */
public class ResourceLimitExceedException extends ResultCodeRuntimeException {
    /** serialVersionUID */
    private static final long serialVersionUID = -6328786303005448132L;

    /** 超出资源限制的资源名称 */
    private final String      resourceType;

    public ResourceLimitExceedException(String resourceType, ResultCode resultCode) {
        super(resultCode);

        this.resourceType = resourceType;
    }

    public ResourceLimitExceedException(String resourceType) {
        this(resourceType, ResultCode.GENERIC_FAILURE);
    }

    public String getResourceType() {
        return resourceType;
    }
}
