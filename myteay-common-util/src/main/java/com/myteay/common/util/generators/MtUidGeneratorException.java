/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.util.generators;

/**
 * ҵ�����쳣
 * 
 * @author min.weixm
 * @version $Id: MtUidGeneratorException.java, v 0.1 Oct 28, 2017 11:23:31 PM min.weixm Exp $
 */
public class MtUidGeneratorException extends Throwable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1698555152104131128L;

    /** ϵͳĬ�ϴ����� */
    protected static final String DEFAULT_ERR_CODE = "-999999";

    /** Ĭ�ϴ������� */
    protected static final String DEFAULT_ERR_MSG  = "δ֪����";

    /** ������ */
    protected String              errorCode;

    /** �������� */
    protected String              message;

    /**
     * @param e
     */
    public MtUidGeneratorException() {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = DEFAULT_ERR_MSG;
    }

    /**
     * @param e
     */
    public MtUidGeneratorException(String message) {
        super(message);
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = message;
    }

    /**
     * @param e
     */
    public MtUidGeneratorException(Throwable e) {
        super(e);

        this.errorCode = DEFAULT_ERR_CODE;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param msg
     * @param e
     */
    public MtUidGeneratorException(String msg, Throwable e) {
        super(msg, e);
    }
}
