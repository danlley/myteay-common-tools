/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myteay.common.aop.monitor.logger.supports.Profiler;

/**
 * 一个简单的用于性能监测的拦截器.
 * 
 * @author min.weixm
 * @version $Id: PerformanceMonitorInterceptor.java, v 0.1 Nov 5, 2017 9:33:37 PM min.weixm Exp $
 */
public class PerformanceMonitorInterceptor implements MethodInterceptor {
    /** logger */
    private static final Logger logger    = LoggerFactory.getLogger(PerformanceMonitorInterceptor.class);

    /** 以毫秒表示的阈值 */
    private int                 threshold = 1;

    /**
     * 缺省的构造方法.
     */
    public PerformanceMonitorInterceptor() {
        super();
    }

    /**
     * 判断方法调用的时间是否超过阈值，如果是，则打印性能日志.
     *
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName();

        Profiler.enter("Invoking method: " + name);

        long startTime = System.currentTimeMillis();

        try {
            return invocation.proceed();
        } finally {
            Profiler.release();

            long elapseTime = System.currentTimeMillis() - startTime;

            if (elapseTime > threshold) {
                logger.info("方法" + name + "的执行时间超过阈值，实际执行时间为" + elapseTime + "毫秒。");
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("方法" + name + "的执行时间为" + elapseTime + "毫秒。");
                }
            }
        }
    }

    // ----- 容器方法 ------

    /**
     * @param threshold The threshold to set.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
