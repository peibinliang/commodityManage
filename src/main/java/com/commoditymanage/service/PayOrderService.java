package com.commoditymanage.service;

import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.entity.PayOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  支付订单服务类
 * </p>
 *
 * @author liangpeibin
 * @since 2022/4/17
 */
public interface PayOrderService {
    /**
     * 新增支付订单
     * @param payOrder
     * @return
     */
    boolean savePayOrder(PayOrder payOrder);

    /**
     * 删除支付订单
     * @param orderId
     * @return
     */
    boolean removePayOrder(Integer orderId);

    /**
     * 获取支付订单详情
     * @param orderId
     * @return
     */
    PayOrder getPayOrderByOrderId(Integer orderId);

    /**
     * 商品详情直接购买
     * @param car
     * @return
     */
    Boolean savePayOrderOfShop(CommodityCar car);

    /**
     * 购物车付款
     * @param carId
     * @return
     */
    boolean payCommodityCar(Integer[] carId);

    /**
     * 订单列表
     * @param request
     * @return
     */
    boolean listPayOrder(HttpServletRequest request);

    /**
     * 根据订单获取关联的购物车信息
     * @param orderId
     * @return
     */
    List<CommodityCar> listCommodityCarOfPayOrder(Integer orderId);
}
