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
 * ��������Դ����Ĺ�����
 * 
 * @author min.weixm
 * @version $Id: DataSrcUtil.java, v 0.1 Nov 5, 2017 9:36:59 PM min.weixm Exp $
 */
public class DataSrcUtil {
    /*
     * ��ǰ�����ָ����datasource�Ƿ�ͬһ������Դ,�������ԴΪnull��ǰû�����񷵻�true
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
                throw new RuntimeException("��Ӧ�÷������쳣������ԭ��", e);
            }
        }
        return false;
    }

}
