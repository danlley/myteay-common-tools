/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.core.model.camp.CampCashierModel;

/**
 * 创建交易并付款接口返回结果
 * 
 * @author min.weixm
 * @version $Id: PxOrderPayedResultModel.java, v 0.1 2019年12月26日 下午3:19:48 min.weixm Exp $
 */
public class PxOrderPayedResultModel implements Serializable {

    /** serialVersionUID */
    private static final long          serialVersionUID = -2564761276765192927L;

    /** 店铺ID */
    private String                     shopId;

    /** 店铺名称 */
    private String                     shopName;

    /** 订单序号 */
    private String                     orderNo;

    /** 交易号 */
    private String                     tradeNo;

    /** 图片名称 */
    private String                     shopLogo;

    /** 联系电话 */
    private String                     shopTel;

    /** 下单时间 */
    private Date                       orderTime;

    /** 抽奖结果 */
    private CampCashierModel           campCashierModel;

    /** 下单列表 */
    private List<PxGoodsOrderOutModel> pxGoodsOrderOutModelList;

    /**
     * Getter method for property <tt>shopTel</tt>.
     * 
     * @return property value of shopTel
     */
    public String getShopTel() {
        return shopTel;
    }

    /**
     * Setter method for property <tt>shopTel</tt>.
     * 
     * @param shopTel
     *            value to be assigned to property shopTel
     */
    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
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
     * @param shopId
     *            value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property <tt>shopName</tt>.
     * 
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property <tt>shopName</tt>.
     * 
     * @param shopName
     *            value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property <tt>orderNo</tt>.
     * 
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property <tt>orderNo</tt>.
     * 
     * @param orderNo
     *            value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property <tt>tradeNo</tt>.
     * 
     * @return property value of tradeNo
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * Setter method for property <tt>tradeNo</tt>.
     * 
     * @param tradeNo
     *            value to be assigned to property tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * Getter method for property <tt>shopLogo</tt>.
     * 
     * @return property value of shopLogo
     */
    public String getShopLogo() {
        return shopLogo;
    }

    /**
     * Setter method for property <tt>shopLogo</tt>.
     * 
     * @param shopLogo
     *            value to be assigned to property shopLogo
     */
    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    /**
     * Getter method for property <tt>orderTime</tt>.
     * 
     * @return property value of orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * Setter method for property <tt>orderTime</tt>.
     * 
     * @param orderTime
     *            value to be assigned to property orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Getter method for property <tt>campCashierModel</tt>.
     * 
     * @return property value of campCashierModel
     */
    public CampCashierModel getCampCashierModel() {
        return campCashierModel;
    }

    /**
     * Setter method for property <tt>campCashierModel</tt>.
     * 
     * @param campCashierModel
     *            value to be assigned to property campCashierModel
     */
    public void setCampCashierModel(CampCashierModel campCashierModel) {
        this.campCashierModel = campCashierModel;
    }

    /**
     * Getter method for property <tt>pxGoodsOrderOutModelList</tt>.
     * 
     * @return property value of pxGoodsOrderOutModelList
     */
    public List<PxGoodsOrderOutModel> getPxGoodsOrderOutModelList() {
        return pxGoodsOrderOutModelList;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutModelList</tt>.
     * 
     * @param pxGoodsOrderOutModelList
     *            value to be assigned to property pxGoodsOrderOutModelList
     */
    public void setPxGoodsOrderOutModelList(List<PxGoodsOrderOutModel> pxGoodsOrderOutModelList) {
        this.pxGoodsOrderOutModelList = pxGoodsOrderOutModelList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
