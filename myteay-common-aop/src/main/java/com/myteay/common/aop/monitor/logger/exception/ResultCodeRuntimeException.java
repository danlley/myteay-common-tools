/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

import com.myteay.common.aop.monitor.logger.enums.ResultCode;

/**
 * 携带<code>ResultCode</code>的异常。
 * 
 * @author min.weixm
 * @version $Id: ResultCodeRuntimeException.java, v 0.1 Nov 5, 2017 9:35:32 PM min.weixm Exp $
 */
public class ResultCodeRuntimeException extends ChainedRuntimeException {
    private static final long serialVersionUID = 3832907658473910577L;
    private ResultCode        resultCode;

    /**
     * 构造一个空的异常.
     */
    public ResultCodeRuntimeException(ResultCode resultCode) {
        this(resultCode, null, null);
    }

    /**
     * 构造一个空的异常.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    /**
     * 构造一个空的异常.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    /**
     * 构造一个空的异常.
     */
    public ResultCodeRuntimeException(ResultCode resultCode, Throwable cause) {
        super((resultCode == null) ? null : resultCode.name(), cause);
        this.resultCode = resultCode;
    }

    /**
     * 取得<code>ResultCode</code>。
     *
     * @return result code
     */
    public ResultCode getResultCode() {
        return resultCode;
    }
}
