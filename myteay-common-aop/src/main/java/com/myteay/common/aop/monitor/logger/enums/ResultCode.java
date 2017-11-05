/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.enums;

/**
 * Command处理结果的代码
 * 
 * @author min.weixm
 * @version $Id: ResultCode.java, v 0.1 Nov 5, 2017 9:34:11 PM min.weixm Exp $
 */
public enum ResultCode {

                        /** 表示成功执行AO。 */
                        SUCCESS,

                        /** 表示AO抛出未预料到异常，或者<code>isSuccess()</code>为<code>false</code>却未指明具体的<code>ResultCode</code>。 */
                        GENERIC_FAILURE,

                        /** 如果未指定command，或command的名称为空。 */
                        MISSING_COMMAND,

                        /** 表示command的参数不正确。 */
                        ILLEGAL_COMMAND_PARAMETERS,

                        /** 表示取AO对象时失败。 */
                        GET_APPLICATION_OBJECT_FAILURE,

                        /** 发送JMS异步消息时失败。 */
                        SEND_ASYNCHRONOUS_MESSAGE_FAILURE,

                        /** 处理action event时失败。 */
                        PROCESS_ACTION_EVENT_FAILURE,

                        RESOURCE_LIMIT_EXCEED;

}
