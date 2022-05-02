package com.commoditymanage.service;

import java.util.List;

public interface PayOrderRelationService {
    /**
     * 新增订单和商品关联关系
     * @param orderId
     * @param carIdList
     * @return
     */
    boolean savePayOrderRelation(Integer orderId, List<Integer> carIdList);
}
