package com.commoditymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commoditymanage.entity.CommodityCar;
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
public interface CommodityCarMapper extends BaseMapper<CommodityCar> {
    /**
     * 移除购物车信息
     * @param carId
     * @return
     */
    Integer removeCommodityCar(@Param("carId") Integer carId);


    /**
     * 添加购物车
     * @param commodityCar
     * @return
     */
    Integer saveCommodityCar(@Param("commodityCar") CommodityCar commodityCar);

    /**
     * 根据用户Id和商品Id获取购物车信息
     * @param userId
     * @param commodityId
     * @return
     */
    CommodityCar getCommodityCarByUserIdAndCommodityId(@Param("userId") Integer userId, @Param("commodityId") Integer commodityId);

    /**
     * 根据用户Id和商品Id更新购物车信息
     * @param car
     * @return
     */
    Integer modifyCommodityCarByUserIdAndCommodityId(@Param("car")CommodityCar car);

    /**
     * 根据购物车Id获取购物车信息
     * @param carId
     * @return
     */
    CommodityCar getCommodityCarByCarId(@Param("carId") Integer carId);

    /**
     * 计算购物列表条数
     * @param search
     * @param userId
     * @return
     */
    Integer countCommodityCar(@Param("search") String search, @Param("userId") Integer userId);

    /**
     * 获取用户购物车列表
     * @param search
     * @param countShowed
     * @param pageSize
     * @param userId
     * @return
     */
    List<CommodityCar> listCommodityCarByUserId(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize, @Param("userId") Integer userId);

    /**
     * 根据购物车编号获取购物车信息
     * @param carNo
     * @return
     */
    CommodityCar getCommodityCarByCarNo(@Param("carNo") String carNo);
}
