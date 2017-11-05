/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.utils;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 关于数据源处理的工具类
 * 
 * @author min.weixm
 * @version $Id: DataSrcUtil.java, v 0.1 Nov 5, 2017 9:36:59 PM min.weixm Exp $
 */
public class DataSrcUtil {
    /*
     * 当前事务和指定的datasource是否同一个数据源,如果数据源为null或当前没有事务返回true
     * 
     */
    static public boolean isSameRm(DataSource dataSource) {
        if (dataSource == null | !TransactionSynchronizationManager.isActualTransactionActive()) {
            return true;
        }
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        if (conHolder != null) {
            try {
                Method method = ConnectionHolder.class.getDeclaredMethod("isTransactionActive",
                    new Class[0]);
                method.setAccessible(true);
                return (Boolean) method.invoke(conHolder);
            } catch (Exception e) {
                throw new RuntimeException("不应该发生的异常，请检查原因。", e);
            }
        }
        return false;
    }

}
