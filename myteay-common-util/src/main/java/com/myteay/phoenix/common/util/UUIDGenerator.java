/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.common.util;

import java.util.UUID;

/**
 * UUID生成工具类
 * 
 * @author min.weixm
 * @version $Id: UUIDGenerator.java, v 0.1 May 20, 2020 10:51:20 PM min.weixm Exp $
 */
public class UUIDGenerator {

    /**
     * 生成UUID
     * 
     * 注：去掉原有UUID中的“-”符号
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
