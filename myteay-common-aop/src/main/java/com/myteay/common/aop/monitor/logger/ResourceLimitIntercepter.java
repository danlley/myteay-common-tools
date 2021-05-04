/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myteay.common.aop.monitor.logger.enums.ResultCode;
import com.myteay.common.aop.monitor.logger.exception.ResourceLimitExceedException;
import com.myteay.common.aop.monitor.logger.supports.SynchronizedResource;
import com.myteay.common.aop.monitor.logger.utils.StringUtil;

/**
 * 对访问资源进行限制的拦截器。
 * 
 * @author min.weixm
 * @version $Id: ResourceLimitIntercepter.java, v 0.1 Nov 5, 2017 9:33:53 PM min.weixm Exp $
 */
public class ResourceLimitIntercepter implements MethodInterceptor {

    private static final Logger  logger = LoggerFactory.getLogger(ResourceLimitIntercepter.class);

    //令牌控制器
    private SynchronizedResource synchronizedResource;

    //需要拦截的方法
    private String               methodName;

    /**
     * 拦截EBankPayment的genEBankItem方法，限制进入该方法的线程数。
     * 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (StringUtil.equals(invocation.getMethod().getName(), methodName)) {
            if (logger.isDebugEnabled()) {
                logger.debug("拦截方法=[" + invocation.getMethod().getClass().getName() + "." + methodName + "]");
            }

            if (synchronizedResource.applyResource()) {
                try {
                    return invocation.proceed();
                } finally {
                    synchronizedResource.releaseResource();
                }
            } else {
                throw new ResourceLimitExceedException(synchronizedResource.getResourceName(), ResultCode.RESOURCE_LIMIT_EXCEED);
            }
        } else {
            return invocation.proceed();
        }
    }

    public void setSynchronizedResource(SynchronizedResource synchronizedResource) {
        this.synchronizedResource = synchronizedResource;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
