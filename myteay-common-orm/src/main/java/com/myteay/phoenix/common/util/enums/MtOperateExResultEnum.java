/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 返回结果扩展码
 * 
 * @author Administrator
 * @version $Id: MtOperateExResultEnum.java, v 0.1 2015年12月1日 下午4:49:41 Administrator Exp $
 */
public enum MtOperateExResultEnum {

                                   //----------------          基本扩展结果信息信息          ----------------

                                   CAMP_SQL_EXE_INVALID("CAMP_SQL_EXE_INVALID", "00110002", "SQL执行异常"),

                                   CAMP_USERID_ERR("CAMP_USERID_ERR", "00110004", "userid不合法"),

                                   //----------------          基本扩展结果信息信息          ----------------

                                   CAMP_PRIZE_REF_GOODS_ERR("CAMP_PRIZE_REF_GOODS_ERR", "00210010", "在线活动的奖品关联商品"),

                                   COST_MODEL_ERR("COST_MODEL_ERR", "00310001", "成本模型不可用"),

                                   COST_MODEL_OP_MNG_FAILD("COST_MODEL_OP_MNG_FAILD", "00310002", "成本模型管理失败"),

    ;

    /** 枚举值 */
    private final String value;

    /** 枚举描述 */
    private final String message;

    /** 枚举描述 */
    private final String code;

    /**
     * 私有构造方法
     * 
     * @param value         枚举值
     * @param message       枚举描述
     */
    private MtOperateExResultEnum(String value, String code, String message) {
        this.value = value;
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>value</tt>.
     * 
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 通过值获取枚举对象
     * @param value     枚举值
     * @return          枚举对象
     */
    public static MtOperateExResultEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtOperateExResultEnum type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;

    }

    /**
     * 通过枚举<code>value</code>获得枚举
     * 
     * @param value     枚举值
     * @return          枚举对象
     */
    public static MtOperateExResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * 通过枚举<code>message</code>获得枚举
     * 
     * @param message       枚举描述
     * @return              枚举对象
     */
    public static MtOperateExResultEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtOperateExResultEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
