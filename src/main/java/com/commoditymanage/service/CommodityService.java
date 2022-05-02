package com.commoditymanage.service;

import com.commoditymanage.entity.Commodity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *   商品服务类
 * </p>
 *
 * @author ~
 * @since 2022/4/10
 */
public interface CommodityService {
    /**
     * 新增商品
     * @param commodity
     * @return
     */
    boolean saveCommodity(Commodity commodity);

    /**
     * 删除商品
     * @param commodityId
     * @return
     */
    boolean removeCommodity(Integer commodityId);

    /**
     * 修改商品
     * @param commodity
     * @return
     */
    boolean modifyCommodity(Commodity commodity);

    /**
     * 根据商品Id获取商品详情
     * @param commodityId
     * @return
     */
    Commodity getCommodityByCommodityId(Integer commodityId);

    /**
     * 商品列表
     * @param request
     * @return
     */
    boolean listCommodity(HttpServletRequest request);

    /**
     * 商品搜索
     * @param searchTerm
     * @return
     */
    List<Commodity> searchCommodity(String searchTerm);
}
