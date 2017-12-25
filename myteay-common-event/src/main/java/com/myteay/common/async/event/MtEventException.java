/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.async.event;

/**
 * 业务处理异常
 * 
 * @author min.weixm
 * @version $Id: MtEventException.java, v 0.1 Oct 28, 2017 11:23:31 PM min.weixm Exp $
 */
public class MtEventException extends Throwable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1698555152104131128L;

    /** 系统默认错误码 */
    protected static final String DEFAULT_ERR_CODE = "-999999";

    /** 默认错误详情 */
    protected static final String DEFAULT_ERR_MSG  = "未知错误";

    /** 错误码 */
    protected String              errorCode;

    /** 错误详情 */
    protected String              message;

    /**
     * @param e
     */
    public MtEventException() {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = DEFAULT_ERR_MSG;
    }

    /**
     * @param e
     */
    public MtEventException(String message) {
        super(message);
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = message;
    }

    /**
     * @param e
     */
    public MtEventException(Throwable e) {
        super(e);

        this.errorCode = DEFAULT_ERR_CODE;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param msg
     * @param e
     */
    public MtEventException(String msg, Throwable e) {
        super(msg, e);
    }
}
