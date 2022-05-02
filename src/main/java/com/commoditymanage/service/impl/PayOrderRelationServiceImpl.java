package com.commoditymanage.service.impl;

import com.commoditymanage.dao.PayOrderRelationMapper;
import com.commoditymanage.service.PayOrderRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liangpeibin
 * @ClassName PayOrderRelationServiceImpl
 * @Description
 * @since 2022/4/23 17:11
 */
@Service
public class PayOrderRelationServiceImpl implements PayOrderRelationService {
    @Resource
    private PayOrderRelationMapper payOrderRelationMapper;

    /**
     * 新增订单和商品关联关系
     *
     * @param orderId
     * @param carIdList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePayOrderRelation(Integer orderId, List<Integer> carIdList) {
        for (Integer carId : carIdList) {
            payOrderRelationMapper.savePayOrderRelation(orderId,carId);
        }
        return true;
    }
}
