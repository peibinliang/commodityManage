package com.commoditymanage.service;

import com.commoditymanage.entity.CommodityCar;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  购物车服务类
 * </p>
 *
 * @author liangpeibin
 * @since 2022/4/17
 */
public interface CommodityCarService {
    /**
     * 新增购物车信息
     * @param commodityCar
     * @return
     */
    boolean saveCommodityCar(CommodityCar commodityCar);

    /**
     * 修改购物车信息
     * @param commodityCar
     * @return
     */
    boolean modifyCommodityCar(CommodityCar commodityCar);

    /**
     * 删除购物车信息
     * @param carId
     * @return
     */
    boolean removeCommodityCar(Integer carId);

    /**
     * 根据用户Id和商品Id获取购物车信息
     * @param userId
     * @param commodityId
     * @return
     */
    CommodityCar getCommodityCarByUserIdAndCommodityId(Integer userId, Integer commodityId);

    /**
     * 根据购物车Id获取购物车信息
     * @param carId
     * @return
     */
    CommodityCar getCommodityCarByCarId(Integer carId);

    /**
     * 根据用户Id获取购物车列表
     * @param request
     * @return
     */
    boolean listCommodityCarByUserId(HttpServletRequest request);

    /**
     * 商品详情页直接购买时创建购物车
     * @param car
     * @return
     */
    CommodityCar saveCommodityCarOfShop(CommodityCar car);
}
