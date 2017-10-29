package com.myteay.common.util.log;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ����loggerʵ�ֵ�С����.
 * 
 * @author min.weixm
 * @version $Id: LoggerUtil.java, v 0.1 Oct 29, 2017 12:10:09 PM min.weixm Exp $
 */
public class LoggerUtil {
    /**
     * ��resource bundle��ȡ�ø�ʽ������Ϣ.
     *
     * @param logger logger
     * @param key resource bundle key
     * @param params ��������
     *
     * @return ��ʽ������Ϣ�ַ���, ���resource bundle������, ��keyδ�ҵ�, �򷵻�<code>key</code>
     */
    public static String getMessage(Logger logger, Object key, Object[] params) {
        ResourceBundle bundle = logger.getResourceBundle();
        String keyStr = String.valueOf(key);
        String message = null;

        if (bundle == null) {
            logger.error(new StringBuffer().append("Resource bundle not set for logger \"")
                .append(logger).append("\"").toString());
        } else {
            try {
                message = MessageUtil.getMessage(bundle, keyStr, params);
            } catch (MissingResourceException e) {
                logger.error(new StringBuffer().append("No resource is associated with key \"")
                    .append(keyStr).append("\" in logger \"").append(logger).append("\"")
                    .toString(), e);
            }
        }

        return (message == null) ? keyStr : message;
    }
}
