/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.enums;

/**
 * Command�������Ĵ���
 * 
 * @author min.weixm
 * @version $Id: ResultCode.java, v 0.1 Nov 5, 2017 9:34:11 PM min.weixm Exp $
 */
public enum ResultCode {

                        /** ��ʾ�ɹ�ִ��AO�� */
                        SUCCESS,

                        /** ��ʾAO�׳�δԤ�ϵ��쳣������<code>isSuccess()</code>Ϊ<code>false</code>ȴδָ�������<code>ResultCode</code>�� */
                        GENERIC_FAILURE,

                        /** ���δָ��command����command������Ϊ�ա� */
                        MISSING_COMMAND,

                        /** ��ʾcommand�Ĳ�������ȷ�� */
                        ILLEGAL_COMMAND_PARAMETERS,

                        /** ��ʾȡAO����ʱʧ�ܡ� */
                        GET_APPLICATION_OBJECT_FAILURE,

                        /** ����JMS�첽��Ϣʱʧ�ܡ� */
                        SEND_ASYNCHRONOUS_MESSAGE_FAILURE,

                        /** ����action eventʱʧ�ܡ� */
                        PROCESS_ACTION_EVENT_FAILURE,

                        RESOURCE_LIMIT_EXCEED;

}
