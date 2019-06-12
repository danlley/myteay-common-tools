/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAL调用监控拦截器, 记录DAL执行的摘要日志
 * 
 * @author min.weixm
 * @version $Id: DalMonitorInterceptor.java, v 0.1 Nov 5, 2017 9:32:11 PM min.weixm Exp $
 */
public class DalMonitorInterceptor implements MethodInterceptor {

    /** DAL层摘要日志，dal层无法访问common-util层代码，故此处写死日志Name */
    private static final Logger digestLogger = LoggerFactory.getLogger("DAL-DIGEST");

    /** 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String packageName = invocation.getMethod().getDeclaringClass().getPackage().getName();
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();
        String method = className + "." + invocation.getMethod().getName();
        String dbName = parseDbName(packageName);

        long startTime = System.currentTimeMillis();

        //是否有Throwable
        boolean hasError = false;
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            hasError = true;
            throw ex;

        } finally {
            if (digestLogger.isInfoEnabled()) {
                long elapseTime = System.currentTimeMillis() - startTime;

                if (hasError) {
                    digestLogger.info("(" + dbName + "," + method + ",N," + elapseTime + "ms)");
                } else {
                    digestLogger.info("(" + dbName + "," + method + ",Y," + elapseTime + "ms)");
                }
            }
        }
    }

    /**
     * 解析数据库名称
     * 
     * @param packageName
     * @param className
     * @return
     */
    private String parseDbName(String packageName) {
        // 平台门面
        if (StringUtils.contains(packageName, "phoenix")) {
            return "phoenix";
        }

        // 数据中心
        if (StringUtils.contains(packageName, "dbcenter")) {
            return "dbcenter";
        }

        // 交易系统
        if (StringUtils.contains(packageName, "trade")) {
            return "trade";
        }

        // 营销系统
        if (StringUtils.contains(packageName, "promocore")) {
            return "promocore";
        }

        // 结算系统
        if (StringUtils.contains(packageName, "counter")) {
            return "counter";
        }

        // 产品账
        if (StringUtils.contains(packageName, "transprod")) {
            return "transprod";
        }

        // 账务系统
        if (StringUtils.contains(packageName, "accounts")) {
            return "accounts";
        }

        // 默认标识
        return "myteay";
    }
}
