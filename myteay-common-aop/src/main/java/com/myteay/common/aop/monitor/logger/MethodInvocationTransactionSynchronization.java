/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2005 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronization;

/**
 * 用于在事务提交后执行某个方法调用的类。
 * 
 * @author min.weixm
 * @version $Id: MethodInvocationTransactionSynchronization.java, v 0.1 Nov 5, 2017 9:32:29 PM min.weixm Exp $
 */
public class MethodInvocationTransactionSynchronization implements TransactionSynchronization {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MethodInvocationTransactionSynchronization.class);

    /** 一个方法调用 */
    private MethodInvocation    methodInvocation;

    /**
     * 构造方法。
     *
     * @param methodInvocation 用于在事务提交后执行的方法。
     *
     */
    public MethodInvocationTransactionSynchronization(MethodInvocation methodInvocation) {
        super();

        this.methodInvocation = methodInvocation;
    }

    /**
     * @see org.springframework.transaction.support.TransactionSynchronization#suspend()
     */
    public void suspend() {
    }

    /**
     * @see org.springframework.transaction.support.TransactionSynchronization#resume()
     */
    public void resume() {
    }

    /**
     * @see org.springframework.transaction.support.TransactionSynchronization#beforeCommit(boolean)
     */
    public void beforeCommit(boolean readOnly) {
    }

    /**
     * @see org.springframework.transaction.support.TransactionSynchronization#beforeCompletion()
     */
    public void beforeCompletion() {
    }

    /**
     * @see org.springframework.transaction.support.TransactionSynchronization#afterCompletion(int)
     */
    public void afterCompletion(int status) {
        if (status == STATUS_COMMITTED) {
            try {
                Object result = methodInvocation.proceed();
                if (logger.isDebugEnabled()) {
                    logger.debug("事务提交后执行方法调用返回结果为" + result + ".");
                }
            } catch (Throwable t) {
                logger.error("在事务后执行方法调用("
                             + methodInvocation.getMethod().getDeclaringClass().getName() + "."
                             + methodInvocation.getMethod().getName() + ")时出现异常。", t);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.transaction.support.TransactionSynchronization#afterCommit()
     */
    public void afterCommit() {
        // SPRING 升级 2.0.6
    }

    @Override
    public void flush() {
    }
}
