/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.core.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 折扣预处理模型
 * 
 * @author min.weixm
 * @version $Id: TcDiscountPreparedModel.java, v 0.1 Mar 10, 2020 11:23:47 AM min.weixm Exp $
 */
public class TcDiscountPreparedModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 8704217825447193800L;

    /** 会员ID */
    private String            userId;

    /** 商品ID */
    private String            goodsId;

    /** 店铺ID */
    private String            shopId;

    /** 已匹配的折扣ID */
    private String            discountId;

    /** 折扣对应的产品账ID */
    private String            prodtransId;

    /** 折扣对应的产品账流水ID */
    private String            prodtransOutId;

    /** 产品账事务识别码 */
    private String            prodtransUniqId;

    /**
     * Getter method for property <tt>prodtransOutId</tt>.
     * 
     * @return property value of prodtransOutId
     */
    public String getProdtransOutId() {
        return prodtransOutId;
    }

    /**
     * Setter method for property <tt>prodtransOutId</tt>.
     * 
     * @param prodtransOutId value to be assigned to property prodtransOutId
     */
    public void setProdtransOutId(String prodtransOutId) {
        this.prodtransOutId = prodtransOutId;
    }

    /**
     * Getter method for property <tt>prodtransUniqId</tt>.
     * 
     * @return property value of prodtransUniqId
     */
    public String getProdtransUniqId() {
        return prodtransUniqId;
    }

    /**
     * Setter method for property <tt>prodtransUniqId</tt>.
     * 
     * @param prodtransUniqId value to be assigned to property prodtransUniqId
     */
    public void setProdtransUniqId(String prodtransUniqId) {
        this.prodtransUniqId = prodtransUniqId;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>goodsId</tt>.
     * 
     * @return property value of goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * Setter method for property <tt>goodsId</tt>.
     * 
     * @param goodsId value to be assigned to property goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Getter method for property <tt>shopId</tt>.
     * 
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     * 
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property <tt>discountId</tt>.
     * 
     * @return property value of discountId
     */
    public String getDiscountId() {
        return discountId;
    }

    /**
     * Setter method for property <tt>discountId</tt>.
     * 
     * @param discountId value to be assigned to property discountId
     */
    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    /**
     * Getter method for property <tt>prodtransId</tt>.
     * 
     * @return property value of prodtransId
     */
    public String getProdtransId() {
        return prodtransId;
    }

    /**
     * Setter method for property <tt>prodtransId</tt>.
     * 
     * @param prodtransId value to be assigned to property prodtransId
     */
    public void setProdtransId(String prodtransId) {
        this.prodtransId = prodtransId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
