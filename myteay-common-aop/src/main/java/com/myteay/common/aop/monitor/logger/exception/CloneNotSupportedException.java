/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

/**
 * ��<code>ObjectUtil.clone</code>����������ʱ����������ƵĶ���֧�ָò��������׳����쳣��
 * 
 * <p>
 * ע�⣬��<code>java.lang.CloneNotSupportedException</code>��ͬ�����쳣��<code>RuntimeException</code>������
 * </p>
 * 
 * @author min.weixm
 * @version $Id: CloneNotSupportedException.java, v 0.1 Nov 5, 2017 9:35:00 PM min.weixm Exp $
 */
public class CloneNotSupportedException extends ChainedRuntimeException {

    private static final long serialVersionUID = 3257281439807584562L;

    /**
     * ����һ���յ��쳣.
     */
    public CloneNotSupportedException() {
        super();
    }

    /**
     * ����һ���쳣, ָ���쳣����ϸ��Ϣ.
     *
     * @param message ��ϸ��Ϣ
     */
    public CloneNotSupportedException(String message) {
        super(message);
    }

    /**
     * ����һ���쳣, ָ����������쳣������.
     *
     * @param cause �쳣������
     */
    public CloneNotSupportedException(Throwable cause) {
        super(cause);
    }

    /**
     * ����һ���쳣, ָ����������쳣������.
     *
     * @param message ��ϸ��Ϣ
     * @param cause �쳣������
     */
    public CloneNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
