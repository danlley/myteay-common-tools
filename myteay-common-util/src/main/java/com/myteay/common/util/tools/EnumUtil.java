/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.common.util.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.myteay.common.util.enums.EnumMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.common.util.model.DataDictionaryModel;

/**
 * 枚举操作工具类
 * 
 * @author min.weixm
 * @version $Id: EnumUtil.java, v 0.1 Mar 8, 2018 10:09:47 PM min.weixm Exp $
 */
public class EnumUtil {

    /** 枚举的values方法名 */
    private static final String ENUM_METHOD_VALUES = "values";

    /**
     * 通过反射获取枚举对应的key-value值
     * 
     * @param clazz         枚举类型
     * @return              数据字典模型
     * @throws MtException  异常
     */
    public static List<DataDictionaryModel> getDataDictionaryModelFromEnum(Class<?> clazz) throws MtException {
        try {
            return invokeValues(clazz);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new MtException(e);
        }
    }

    /**
     * 执行枚举的values方法
     * 
     * @param clazz                         枚举类型
     * @return                              数据字典模型
     * @throws NoSuchMethodException        异常（方法不可见）
     * @throws SecurityException            异常（安全错误）
     * @throws IllegalAccessException       异常（非法访问）
     * @throws IllegalArgumentException     异常（非法参数）
     * @throws InvocationTargetException    异常（方法唤起失败）
     */
    private static List<DataDictionaryModel> invokeValues(Class<?> clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                                                                          InvocationTargetException {
        Method method = clazz.getMethod(ENUM_METHOD_VALUES);
        EnumMessage inter[] = (EnumMessage[]) method.invoke(null, null);
        DataDictionaryModel dataDictionaryModel = null;
        List<DataDictionaryModel> list = new ArrayList<>();
        for (EnumMessage enumMessage : inter) {
            dataDictionaryModel = new DataDictionaryModel();
            dataDictionaryModel.setBizKey(enumMessage.getValue());
            dataDictionaryModel.setValue(enumMessage.getMessage());
            list.add(dataDictionaryModel);
        }
        return list;
    }

}
