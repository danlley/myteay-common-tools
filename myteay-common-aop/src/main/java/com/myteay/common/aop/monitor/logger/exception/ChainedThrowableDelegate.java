/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.aop.monitor.logger.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.sql.SQLException;

import com.myteay.common.aop.monitor.logger.supports.ExceptionHelper;

/**
 * ��Ƕ�׵��쳣����, �򻯿�Ƕ�׵��쳣��ʵ��.
 * 
 * @author min.weixm
 * @version $Id: ChainedThrowableDelegate.java, v 0.1 Nov 5, 2017 9:34:50 PM min.weixm Exp $
 */
public class ChainedThrowableDelegate
                                      implements ChainedThrowable {
    private static final long        serialVersionUID   = 3257288032683241523L;

    /** ��ʾ�쳣�����ڵĳ���. */
    protected static final Throwable NO_CAUSE           = new Throwable();

    /** ����������ȡ���쳣����ķ�����. */
    private static final String[]    CAUSE_METHOD_NAMES = {
                                                            "getNested", "getNestedException",
                                                            "getNextException", "getTargetException",
                                                            "getException", "getSourceException",
                                                            "getCausedByException", "getRootCause",
                                                            "getCause"
    };

    /** ����������ȡ���쳣������ֶ���. */
    private static final String[]    CAUSE_FIELD_NAMES  = { "detail" };

    /** �������<code>Throwable</code>����. */
    public Throwable                 delegatedThrowable;

    /**
     * ����һ��<code>Throwable</code>����.
     *
     * @param throwable ��������쳣
     */
    public ChainedThrowableDelegate(Throwable throwable) {
        this.delegatedThrowable = throwable;
    }

    /**
     * ȡ�ñ�������쳣������.
     *
     * @return �쳣������.
     */
    public Throwable getCause() {
        Throwable cause = getCauseByWellKnownTypes(delegatedThrowable);

        for (Class<?> throwableClass = delegatedThrowable.getClass(); (cause == null) && Throwable.class.isAssignableFrom(
            throwableClass); throwableClass = throwableClass.getSuperclass()) {
            // ���Գ����ķ���
            for (int i = 0; (cause == null) && (i < CAUSE_METHOD_NAMES.length); i++) {
                cause = getCauseByMethodName(delegatedThrowable, throwableClass,
                    CAUSE_METHOD_NAMES[i]);
            }

            // ���Գ������ֶ�
            for (int i = 0; (cause == null) && (i < CAUSE_FIELD_NAMES.length); i++) {
                cause = getCauseByFieldName(delegatedThrowable, throwableClass, CAUSE_FIELD_NAMES[i]);
            }
        }

        if (cause == delegatedThrowable) {
            cause = null;
        }

        if (cause == NO_CAUSE) {
            return null;
        }

        return cause;
    }

    /**
     * ȡ�ó���<code>Throwable</code>����쳣����.
     *
     * @param throwable �쳣
     *
     * @return �쳣����
     */
    public Throwable getCauseByWellKnownTypes(Throwable throwable) {
        Throwable cause = null;
        boolean isWellKnownType = false;

        if (throwable instanceof ChainedThrowable) {
            isWellKnownType = true;
            cause = ((ChainedThrowable) throwable).getCause();
        } else if (throwable instanceof SQLException) {
            isWellKnownType = true;
            cause = ((SQLException) throwable).getNextException();
        } else if (throwable instanceof InvocationTargetException) {
            isWellKnownType = true;
            cause = ((InvocationTargetException) throwable).getTargetException();
        } else if (throwable instanceof RemoteException) {
            isWellKnownType = true;
            cause = ((RemoteException) throwable).detail;
        }

        if (isWellKnownType && (cause == null)) {
            return NO_CAUSE;
        }

        return cause;
    }

    /**
     * ͨ�������ķ�����̬��ȡ���쳣����.
     *
     * @param throwable �쳣
     * @param throwableClass �쳣��
     * @param methodName ������
     *
     * @return �쳣�����<code>NO_CAUSE</code>
     */
    public Throwable getCauseByMethodName(Throwable throwable, Class<?> throwableClass,
                                          String methodName) {
        Method method = null;

        try {
            method = throwableClass.getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException ignored) {
        }

        if ((method != null) && Throwable.class.isAssignableFrom(method.getReturnType())) {
            Throwable cause = null;

            try {
                cause = (Throwable) method.invoke(throwable, new Object[0]);
            } catch (IllegalAccessException ignored) {
            } catch (IllegalArgumentException ignored) {
            } catch (InvocationTargetException ignored) {
            }

            if (cause == null) {
                return NO_CAUSE;
            }

            return cause;
        }

        return null;
    }

    /**
     * ͨ�������ķ�����̬��ȡ���쳣����.
     *
     * @param throwable �쳣
     * @param throwableClass �쳣��
     * @param fieldName �ֶ���
     *
     * @return �쳣�����<code>NO_CAUSE</code>
     */
    public Throwable getCauseByFieldName(Throwable throwable, Class<?> throwableClass,
                                         String fieldName) {
        Field field = null;

        try {
            field = throwableClass.getField(fieldName);
        } catch (NoSuchFieldException ignored) {
        }

        if ((field != null) && Throwable.class.isAssignableFrom(field.getType())) {
            Throwable cause = null;

            try {
                cause = (Throwable) field.get(throwable);
            } catch (IllegalAccessException ignored) {
            } catch (IllegalArgumentException ignored) {
            }

            if (cause == null) {
                return NO_CAUSE;
            }

            return cause;
        }

        return null;
    }

    /**
     * ��ӡ����ջ����׼����.
     */
    public void printStackTrace() {
        ExceptionHelper.printStackTrace(this);
    }

    /**
     * ��ӡ����ջ��ָ�������.
     *
     * @param stream ����ֽ���.
     */
    public void printStackTrace(PrintStream stream) {
        ExceptionHelper.printStackTrace(this, stream);
    }

    /**
     * ��ӡ����ջ��ָ�������.
     *
     * @param writer ����ַ���.
     */
    public void printStackTrace(PrintWriter writer) {
        ExceptionHelper.printStackTrace(this, writer);
    }

    /**
     * ��ӡ�쳣�ĵ���ջ, �����������쳣����Ϣ.
     *
     * @param writer ��ӡ�������
     */
    public void printCurrentStackTrace(PrintWriter writer) {
        if (delegatedThrowable instanceof ChainedThrowable) {
            ((ChainedThrowable) delegatedThrowable).printCurrentStackTrace(writer);
        } else {
            delegatedThrowable.printStackTrace(writer);
        }
    }
}
