/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.common.util.lang;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 用于电子商务场景的金额计算
 * 
 * @author danlley
 * @version $Id: Money.java, v 0.1 Apr 20, 2019 12:28:47 AM danlley Exp $
 */
public class Money implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 220214845953730021L;

    /** 金钱的整数部分 */
    long                      yuan             = 0;

    /** 金钱的小数部分 */
    int                       cent             = 0;

    /**
     * 默认构造方法，用于无数据初始化情况下生成金额
     */
    public Money() {
    }

    /**
     * 金额初始化，用于字符串类型的金额初始化，字符串格式：23.68 含义：23元6角8分
     * 
     * @param src   金额源数据
     */
    public Money(String src) throws NumberFormatException {

        // case 1: 初始化目标为空，则只是形成一个空金额对象，不抛出任何异常，此时金额toString后为0.00
        if (StringUtils.isBlank(src)) {
            return;
        }

        if (src.indexOf(".") < 0) {
            Long localYuan = Long.parseLong(src);
            this.yuan = localYuan;
            return;
        }

    }

    /**
     * 用于指明金额的整数和小数部分进行金钱类的初始化
     * 
     * 注： 此方法的功能也可以通过默认构造方法得到金钱类对象后，通过相应的set方法对金钱的整数部分和小数部分进行初始化
     * 
     * @param yuan  金额整数部分
     * @param cent  金额小数部分
     */
    public Money(long yuan, int cent) {
    }

    /**
     * 构造方法，用于初始化只有整数金额的情况
     * 
     * 注：金钱类不支持将金额换算成最小单位（分）后对金钱类进行初始化
     * 
     * @param yuan
     */
    public Money(long yuan) {
    }

    /**
     * Getter method for property <tt>yuan</tt>.
     * 
     * @return property value of yuan
     */
    public long getYuan() {
        return yuan;
    }

    /**
     * Setter method for property <tt>yuan</tt>.
     * 
     * @param yuan value to be assigned to property yuan
     */
    public void setYuan(long yuan) {
        this.yuan = yuan;
    }

    /**
     * Getter method for property <tt>cent</tt>.
     * 
     * @return property value of cent
     */
    public int getCent() {
        return cent;
    }

    /**
     * Setter method for property <tt>cent</tt>.
     * 
     * @param cent value to be assigned to property cent
     */
    public void setCent(int cent) {
        this.cent = cent;
    }

}
