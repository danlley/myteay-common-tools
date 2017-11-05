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
 * �����������ύ��ִ��ĳ���������õ��ࡣ
 * 
 * @author min.weixm
 * @version $Id: MethodInvocationTransactionSynchronization.java, v 0.1 Nov 5, 2017 9:32:29 PM min.weixm Exp $
 */
public class MethodInvocationTransactionSynchronization implements TransactionSynchronization {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MethodInvocationTransactionSynchronization.class);

    /** һ���������� */
    private MethodInvocation    methodInvocation;

    /**
     * ���췽����
     *
     * @param methodInvocation �����������ύ��ִ�еķ�����
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
                    logger.debug("�����ύ��ִ�з������÷��ؽ��Ϊ" + result + ".");
                }
            } catch (Throwable t) {
                logger.error("�������ִ�з�������("
                             + methodInvocation.getMethod().getDeclaringClass().getName() + "."
                             + methodInvocation.getMethod().getName() + ")ʱ�����쳣��", t);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.transaction.support.TransactionSynchronization#afterCommit()
     */
    public void afterCommit() {
        // SPRING ���� 2.0.6
    }

    @Override
    public void flush() {
    }
}
