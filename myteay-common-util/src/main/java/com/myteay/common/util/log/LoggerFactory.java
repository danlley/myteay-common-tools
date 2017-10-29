package com.myteay.common.util.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����<code>Logger</code>�Ĺ���.  ��commons-logging��<code>LogFactory</code>����.
 * ����Ե���ԭ����<code>LoggerFactory.getLog</code>����, ���������ܵõ���չ�Ĺ���.
 * ����<code>LoggerFactory.getLogger</code>����Եõ���չ��<code>Logger</code>�Ĺ���.
 * 
 * @author min.weixm
 * @version $Id: LoggerFactory.java, v 0.1 Oct 29, 2017 12:02:08 PM min.weixm Exp $
 */
public abstract class LoggerFactory extends LogFactory {
    /**
     * ȡ����չ��<code>Logger</code>.
     *
     * @param clazz ����<code>Logger</code>���Ƶ���
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(getLog(clazz));
    }

    /**
     * ȡ����չ��<code>Logger</code>.
     *
     * @param name <code>Logger</code>����
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    public static Logger getLogger(String name) {
        return getLogger(getLog(name));
    }

    /**
     * ȡ����չ��<code>Logger</code>, ���ָ����log�����Ѿ�ʵ����<code>Logger</code>�ӿ�, ��ֱ�ӷ���, �����װһ���ٷ���.
     *
     * @param log ʵ��commons-logging��<code>Log</code>�ӿڵĶ���
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    private static Logger getLogger(Log log) {
        if (log instanceof Logger) {
            return (Logger) log;
        }

        return new LoggerWrapper(log);
    }
}
