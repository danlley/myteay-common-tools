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
    private static final long   serialVersionUID   = 220214845953730021L;

    /** 金钱的整数部分 */
    private Long                yuan               = 0L;

    /** 金钱的小数部分 */
    private Integer             cent               = 0;

    /** 金额小数点 */
    private static final String MONEY_POINT        = ".";

    /** 金额小数点后的最大进位单位 */
    private static final int    CENT_UNIT          = 100;

    /** 金额小数部分的默认格式 */
    private char[]              defaultCentPantern = { '0', '0' };

    /** 负数标识 */
    private boolean             negativeFlag       = false;

    /** 负数标记 */
    private static final String negative           = "-";

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

        initCurrentMoney(src);
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

        if (cent >= CENT_UNIT) {
            throw new NumberFormatException("金额的小数点后尾数不合法，金额小数点后不允许出现2位以上的小数");
        }

        this.yuan = yuan;
        this.cent = cent;
    }

    /**
     * 构造方法，用于初始化只有整数金额的情况
     * 
     * 注：金钱类不支持将金额换算成最小单位（分）后对金钱类进行初始化
     * 
     * @param yuan
     */
    public Money(long yuan) {
        this.yuan = yuan;
    }

    /**
     * 将源金额累加到当前目标金额
     * 
     * @param src   源金额
     */
    public void add(Money src) {
        // 当前金额为空或者金额为零则不做累加
        if (src == null || (src.getCent() == 0 && src.getYuan() == 0)) {
            return;
        }

        int localCent = this.cent + src.getCent();
        this.yuan += src.getYuan();
        if (localCent >= CENT_UNIT) {
            this.yuan++;
            this.cent = localCent % CENT_UNIT;
            return;
        }

        this.cent = localCent;

    }

    /**
     * 判断当前金额不小于源金额
     * 
     * @param src   源金额
     * @return
     */
    public boolean isNotSmallThan(Money src) {
        return !this.isSmallThan(src);
    }

    /**
     * 判断两个金额不相等
     * 
     * @param src   源金额
     * @return
     */
    public boolean isNotEqual(Money src) {
        return !this.isEqual(src);
    }

    /**
     * 检查当前金额不小于源金额
     * 
     * @param src   源金额
     * @return
     */
    public boolean isNotBiggerThan(Money src) {
        return !this.isBiggerThan(src);
    }

    /**
     * 比较当前金额是否小于源金额
     * 
     * @param src   源金额
     * @return
     */
    public boolean isSmallThan(Money src) {
        if (!this.isBiggerThan(src) && !this.isEqual(src)) {
            return true;
        }

        return false;
    }

    /**
     * 比较两个金额的面额，如果面额相等，则返回true
     * 
     * @param src   源金额
     * @return
     */
    public boolean isEqual(Money src) {
        Long localMoney = this.yuan * CENT_UNIT + this.cent;
        Long localSrcMoney = src.getYuan() * CENT_UNIT + src.getCent();

        return (localMoney - localSrcMoney == 0);
    }

    /**
     * 比较当前金额是否大于源金额，如大于，则返回true
     * 
     * @param src   源金额
     * @return
     */
    public boolean isBiggerThan(Money src) {
        Long localMoney = this.yuan * CENT_UNIT + this.cent;
        Long localSrcMoney = src.getYuan() * CENT_UNIT + src.getCent();

        return (localMoney - localSrcMoney > 0);
    }

    /**
     * 当前目标金额减掉源金额
     * 
     * @param src   源金额
     */
    public void reduce(Money src) {
        // 当前金额为空或者金额为零则不做扣减
        if (src == null || (src.getCent() == 0 && src.getYuan() == 0)) {
            return;
        }

        Long localMoney = this.yuan * CENT_UNIT + this.cent;
        Long localSrcMoney = src.getYuan() * CENT_UNIT + src.getCent();

        Long localTmpResult = localMoney - localSrcMoney;
        if (localTmpResult < 0) {
            this.negativeFlag = true;
            localTmpResult *= -1;
        }

        this.yuan = localTmpResult / CENT_UNIT;
        this.cent = (int) (localTmpResult % CENT_UNIT);
    }

    /**
     * 当前目标金额增加相应的倍数
     * 
     * @param number    倍数
     */
    public void multiply(int number) {
        // 当前倍数为零或不合法，则将当前金额直接归零
        if (number == 0) {
            this.yuan = 0L;
            this.cent = 0;
        }

        if (number < 0) {
            this.negativeFlag = true;
            number *= -1;
        }

        Long localMoney = this.yuan * CENT_UNIT + this.cent;
        Long tmpResult = localMoney * number;

        this.yuan = tmpResult / CENT_UNIT;
        this.cent = (int) (tmpResult % CENT_UNIT);

    }

    /**
     * 初始化当前现金类
     * 
     * @param src
     */
    private void initCurrentMoney(String src) {

        // case 1: 初始化目标为空，则只是形成一个空金额对象，不抛出任何异常，此时金额toString后为0.00
        if (StringUtils.isBlank(src)) {
            return;
        }

        if (src.indexOf(".") < 0) {
            Long localYuan = Long.parseLong(src);
            this.yuan = localYuan;
            return;
        }

        String[] srcArray = src.split("\\.");
        if (src.indexOf(".") == 0 || srcArray.length > 2) {
            throw new NumberFormatException("金额格式异常，无法完成Money类的初始化");
        }

        char[] localCentArr = srcArray[1].toCharArray();
        if (localCentArr.length > 2) {
            throw new NumberFormatException("金额的小数点后尾数不合法，金额小数点后不允许出现2位以上的小数");
        }
        char[] tmpCentPantern = { '0', '0' };
        System.arraycopy(localCentArr, 0, tmpCentPantern, 0, localCentArr.length);

        this.yuan = Long.parseLong(srcArray[0]);
        this.cent = Integer.parseInt(new String(tmpCentPantern));
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

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {

        this.yuan = (this.yuan < 0 ? this.yuan * -1 : this.yuan);
        this.cent = (this.cent < 0 ? this.cent * -1 : this.cent);

        String localYuan = Long.toString(this.yuan);
        if (StringUtils.isBlank(localYuan) || this.yuan == 0) {
            localYuan = "0";
        }

        String localCent = Integer.toString(this.cent);
        if (StringUtils.isBlank(localCent) || this.cent == 0) {
            return localYuan + ".00";
        }

        char[] localCentArray = localCent.toCharArray();
        if (localCentArray.length > 2) {
            throw new NumberFormatException("金额的小数点后尾数不合法，金额小数点后不允许出现2位以上的小数");
        }

        if (localCentArray.length == 2) {
            System.arraycopy(localCentArray, 0, this.defaultCentPantern, 0, localCentArray.length);
        }

        if (localCentArray.length == 1) {
            System.arraycopy(localCentArray, 0, this.defaultCentPantern, 1, localCentArray.length);
        }

        if (this.negativeFlag) {
            return negative + localYuan + MONEY_POINT + new String(this.defaultCentPantern);
        }

        return localYuan + MONEY_POINT + new String(this.defaultCentPantern);
    }

    //    /** 
    //     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
    //     */
    //    @Override
    //    public void writeExternal(ObjectOutput out) throws IOException {
    //        out.write(this.toString().getBytes());
    //    }
    //
    //    /** 
    //     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
    //     */
    //    @Override
    //    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    //        String src = in.readLine();
    //        initCurrentMoney(src);
    //    }

    /**
     * readObject is called to restore the state of the StringBuffer from
     * a stream.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.write(this.toString().getBytes());
    }

    /**
     * readObject is called to restore the state of the StringBuffer from
     * a stream.
     */
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        String src = s.readLine();
        initCurrentMoney(src);
    }
}
