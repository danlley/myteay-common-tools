/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.common.util.enums;

/**
 * 枚举接口用于获取相应的枚举值
 * 
 * @author min.weixm
 * @version $Id: EnumMessage.java, v 0.1 Mar 8, 2018 10:33:27 PM min.weixm Exp $
 */
public interface EnumMessage {

    /**
     * 获取枚举值
     * 
     * @return
     */
    String getValue();

    /**
    * 获取枚举描述
    * 
    * @return
    */
    String getMessage();
}
