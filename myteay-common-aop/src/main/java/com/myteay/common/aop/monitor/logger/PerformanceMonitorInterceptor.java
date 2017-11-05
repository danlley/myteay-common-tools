/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2005 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myteay.common.aop.monitor.logger.supports.Profiler;

/**
 * һ���򵥵��������ܼ���������.
 * 
 * @author min.weixm
 * @version $Id: PerformanceMonitorInterceptor.java, v 0.1 Nov 5, 2017 9:33:37 PM min.weixm Exp $
 */
public class PerformanceMonitorInterceptor implements MethodInterceptor {
    /** logger */
    private static final Logger logger    = LoggerFactory.getLogger(PerformanceMonitorInterceptor.class);

    /** �Ժ����ʾ����ֵ */
    private int                 threshold = 1;

    /**
     * ȱʡ�Ĺ��췽��.
     */
    public PerformanceMonitorInterceptor() {
        super();
    }

    /**
     * �жϷ������õ�ʱ���Ƿ񳬹���ֵ������ǣ����ӡ������־.
     *
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getDeclaringClass().getName() + "."
                      + invocation.getMethod().getName();

        Profiler.enter("Invoking method: " + name);

        long startTime = System.currentTimeMillis();

        try {
            return invocation.proceed();
        } finally {
            Profiler.release();

            long elapseTime = System.currentTimeMillis() - startTime;

            if (elapseTime > threshold) {
                logger.info("����" + name + "��ִ��ʱ�䳬����ֵ��ʵ��ִ��ʱ��Ϊ" + elapseTime + "���롣");
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("����" + name + "��ִ��ʱ��Ϊ" + elapseTime + "���롣");
                }
            }
        }
    }

    // ----- �������� ------

    /**
     * @param threshold The threshold to set.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
