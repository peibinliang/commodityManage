package com.commoditymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commoditymanage.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper extends BaseMapper<Commodity> {
    /**
     * 根据商品名称获取商品
     *
     * @param commodityName
     * @return
     */
    Commodity getCommodityByCommodityName(@Param("commodityName") String commodityName);

    /**
     * 删除商品
     *
     * @param commodityId
     * @return
     */
    Integer removeCommodity(@Param("commodityId") Integer commodityId);

    /**
     * 商品列表条数
     *
     * @param search
     * @return
     */
    Integer countCommodity(@Param("search") String search);

    /**
     * 商品列表
     *
     * @param search
     * @param countShowed
     * @param pageSize
     * @return
     */
    List<Commodity> listCommodity(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize);

    /**
     * 获取商品详情
     *
     * @param commodityId
     * @return
     */
    Commodity getCommodityByCommodityId(@Param("commodityId") Integer commodityId);

    /**
     * 编辑商品
     *
     * @param commodity
     * @return
     */
    Integer modifyCommodity(@Param("commodity") Commodity commodity);

    /**
     * 新增商品
     *
     * @param commodity
     * @return
     */
    Integer saveCommodity(@Param("commodity") Commodity commodity);

    /**
     * 可购买商品列表条数
     *
     * @param search
     * @return
     */
    Integer countCommodityOfShop(@Param("search") String search);

    /**
     * 可购买商品列表
     * @param search
     * @param countShowed
     * @param pageSize
     * @return
     */
    List<Commodity> listCommodityOfShop(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize);
}
