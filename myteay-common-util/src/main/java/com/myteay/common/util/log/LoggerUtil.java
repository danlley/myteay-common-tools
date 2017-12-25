package com.myteay.common.util.log;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 方便logger实现的小工具.
 * 
 * @author min.weixm
 * @version $Id: LoggerUtil.java, v 0.1 Oct 29, 2017 12:10:09 PM min.weixm Exp $
 */
public class LoggerUtil {
    /**
     * 从resource bundle中取得格式化的消息.
     *
     * @param logger logger
     * @param key resource bundle key
     * @param params 参数数组
     *
     * @return 格式化的消息字符串, 如果resource bundle不存在, 或key未找到, 则返回<code>key</code>
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
