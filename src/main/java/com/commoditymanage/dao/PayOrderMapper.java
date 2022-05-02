package com.commoditymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.entity.PayOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liangpeibin
 * @since 2022/4/23
 */
public interface PayOrderMapper extends BaseMapper<PayOrder> {
    /**
     * 移除销售订单
     *
     * @param orderId
     * @return
     */
    Integer removePayOrder(@Param("orderId") Integer orderId);

    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    Integer savePayOrder(@Param("order") PayOrder order);

    /**
     * 计算订单数量
     *
     * @param search
     * @return
     */
    Integer countPayOrder(@Param("search") String search);

    /**
     * 计算用户订单数量
     *
     * @param search
     * @param userId
     * @return
     */
    Integer countPayOrderByUserId(@Param("search") String search, @Param("userId") Integer userId);

    /**
     * 订单列表
     *
     * @param search
     * @param countShowed
     * @param pageSize
     * @return
     */
    List<PayOrder> listPayOrder(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize);

    /**
     * 获取用户支付订单
     *
     * @param search
     * @param countShowed
     * @param pageSize
     * @param userId
     * @return
     */
    List<PayOrder> listPayOrderByUserId(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize, @Param("userId") Integer userId);

    /**
     * 根据订单号获取订单
     * @param orderNo
     * @return
     */
    PayOrder getPayOrderByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据订单获取关联的购物车信息
     * @param orderId
     * @return
     */
    List<CommodityCar> listCommodityCarOfPayOrder(@Param("orderId") Integer orderId);

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    PayOrder getPayOrderByOrderId(@Param("orderId") Integer orderId);
}
