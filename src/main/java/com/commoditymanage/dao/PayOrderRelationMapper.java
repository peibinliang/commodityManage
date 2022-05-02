package com.commoditymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commoditymanage.entity.PayOrderRelation;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *
 * </p>
 *
 * @author liangpeibin
 * @since 2022/4/23
 */
public interface PayOrderRelationMapper extends BaseMapper<PayOrderRelation> {
    /**
     * 新增订单关联关系
     * @param orderId
     * @param carId
     * @return
     */
    Integer savePayOrderRelation(@Param("orderId") Integer orderId, @Param("carId") Integer carId);
}
