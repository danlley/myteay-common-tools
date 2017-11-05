/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

import com.myteay.common.aop.monitor.logger.enums.ResultCode;

/**
 * Я��<code>ResultCode</code>���쳣��
 * 
 * @author min.weixm
 * @version $Id: ResultCodeRuntimeException.java, v 0.1 Nov 5, 2017 9:35:32 PM min.weixm Exp $
 */
public class ResultCodeRuntimeException extends ChainedRuntimeException {
    private static final long serialVersionUID = 3832907658473910577L;
    private ResultCode        resultCode;

    /**
     * ����һ���յ��쳣.
     */
    public ResultCodeRuntimeException(ResultCode resultCode) {
        this(resultCode, null, null);
    }

    /**
     * ����һ���յ��쳣.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    /**
     * ����һ���յ��쳣.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    /**
     * ����һ���յ��쳣.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, Throwable cause) {
        super((resultCode == null) ? null
            : resultCode.name(), cause);
        this.resultCode = resultCode;
    }

    /**
     * ȡ��<code>ResultCode</code>��
     *
     * @return result code
     */
    public ResultCode getResultCode() {
        return resultCode;
    }
}
