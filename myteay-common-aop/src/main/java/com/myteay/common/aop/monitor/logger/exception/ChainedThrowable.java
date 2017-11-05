/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * ʵ�ִ˽ӿڵ��쳣, ������һ���쳣�����.
 * 
 * @author min.weixm
 * @version $Id: ChainedThrowable.java, v 0.1 Nov 5, 2017 9:34:39 PM min.weixm Exp $
 */
public interface ChainedThrowable extends Serializable {
    /**
     * ȡ���쳣������.
     *
     * @return �쳣������.
     */
    Throwable getCause();

    /**
     * ��ӡ����ջ����׼����.
     */
    void printStackTrace();

    /**
     * ��ӡ����ջ��ָ�������.
     *
     * @param stream ����ֽ���.
     */
    void printStackTrace(PrintStream stream);

    /**
     * ��ӡ����ջ��ָ�������.
     *
     * @param writer ����ַ���.
     */
    void printStackTrace(PrintWriter writer);

    /**
     * ��ӡ�쳣�ĵ���ջ, �����������쳣����Ϣ.
     *
     * @param writer ��ӡ�������
     */
    void printCurrentStackTrace(PrintWriter writer);
}
