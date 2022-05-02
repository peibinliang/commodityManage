package com.commoditymanage.service.impl;

import com.commoditymanage.dao.CommodityCarMapper;
import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.CommodityCarService;
import com.commoditymanage.service.PayOrderService;
import com.commoditymanage.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liangpeibin
 * @ClassName CommodityCarServiceImpl
 * @Description
 * @since 2022/4/23 15:56
 */
@Service
public class CommodityCarServiceImpl implements CommodityCarService {
    @Resource
    private CommodityCarMapper commodityCarMapper;
    @Resource
    private PayOrderService payOrderService;

    /**
     * 新增购物车信息
     *
     * @param commodityCar
     * @return
     */
    @Override
    public boolean saveCommodityCar(CommodityCar commodityCar) {
        CommodityCar car = commodityCarMapper.getCommodityCarByUserIdAndCommodityId(commodityCar.getUserId(), commodityCar.getCommodityId());
        if (Objects.nonNull(car)) {
            car.setNum(commodityCar.getNum() + car.getNum());
            car.setUnitPrice(commodityCar.getUnitPrice());
            car.setTotalPrice(car.getUnitPrice().multiply(new BigDecimal(car.getNum())));
            return commodityCarMapper.modifyCommodityCarByUserIdAndCommodityId(car) > 0;
        }
        commodityCar.setCarNo(Utils.generateCommodityCarNo());
        return commodityCarMapper.saveCommodityCar(commodityCar) > 0;
    }

    /**
     * 修改购物车信息
     *
     * @param commodityCar
     * @return
     */
    @Override
    public boolean modifyCommodityCar(CommodityCar commodityCar) {
        return commodityCarMapper.updateById(commodityCar) > 0;
    }

    /**
     * 删除购物车信息
     *
     * @param carId
     * @return
     */
    @Override
    public boolean removeCommodityCar(Integer carId) {
        return commodityCarMapper.removeCommodityCar(carId) > 0;
    }

    /**
     * 根据用户Id和商品Id获取购物车信息
     *
     * @param userId
     * @param commodityId
     * @return
     */
    @Override
    public CommodityCar getCommodityCarByUserIdAndCommodityId(Integer userId, Integer commodityId) {
        return commodityCarMapper.getCommodityCarByUserIdAndCommodityId(userId, commodityId);
    }

    /**
     * 根据购物车Id获取购物车信息
     *
     * @param carId
     * @return
     */
    @Override
    public CommodityCar getCommodityCarByCarId(Integer carId) {
        return commodityCarMapper.getCommodityCarByCarId(carId);
    }

    /**
     * 根据用户Id获取购物车列表
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean listCommodityCarByUserId(HttpServletRequest request) {
        List<CommodityCar> commodityCarList = new ArrayList<CommodityCar>();    //记录列表
        String search = "";                                //搜索的内容
        int countShowed = 0;                                //（要略过的）之前的记录数
        int pageShow = 1;                                    //当前页码
        String page = "";                                    //页码链接组
        String msg = "";
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();

        try {
            String[] commodityCarIdArray = request.getParameterValues("carId");    //获取ID列表
            if (Objects.nonNull(commodityCarIdArray)) {
                Integer[] commodityCarIdList = new Integer[commodityCarIdArray.length];

                for (int i = 0; i < commodityCarIdArray.length; i++) {
                    commodityCarIdList[i] = Integer.parseInt(commodityCarIdArray[i]);
                }

                if (request.getParameter("buttonDelete") != null) {                //如果单击了删除按钮
                    deleteByIdList(request, commodityCarIdList);                        //****删除所选。调用在本类中定义的方法
                } else if (request.getParameter("buttonPay") != null) {
                    payByIdList(request, commodityCarIdList);
                }
            }

            String buttonQuery = request.getParameter("buttonQuery");    //数据查询按钮
            String buttonPage = request.getParameter("buttonPage");    //页码提交按钮
            String pageInput = "1";                                    //输入的页码

            if (buttonQuery != null) {                                    //如果按下了数据查询按钮
                search = request.getParameter("search").trim();        //搜索内容
            } else if (buttonPage != null) {                            //如果按下了页码提交按钮
                search = request.getParameter("search").trim();        //搜索内容
                pageInput = request.getParameter("pageShow");        //页码输入框中的值
            } else {                                                    //点击了页码链接，或者刚打开此页
                if (request.getParameter("searchUrl") != null) {
                    search = request.getParameter("searchUrl");        //不需要进行解码操作，系统会自动解码
                }

                if (request.getParameter("pageUrl") != null) {            //地址栏中的页码
                    pageInput = request.getParameter("pageUrl");
                }
            }

            int countRow = commodityCarMapper.countCommodityCar(search,userId);    //****获得记录总数

            int pageSize = 6;                            //每页6条记录
            int pageCount = 0;                                //预设总页数为0

            if (countRow % pageSize == 0) {                    //如果余数为0，即能整除
                pageCount = countRow / pageSize;            //总页数
            } else {
                pageCount = countRow / pageSize + 1;        //不能整除则加1页。如果除数为小数，将自动去除小数部分得到整数
            }

            try {
                pageShow = Integer.parseInt(pageInput);        //如果是数字，返回字符串对应的整数
            } catch (Exception e) {
                pageShow = 1;                            //如果抛出异常，则取预设值
            }

            if (pageShow < 1) {                                //如果当前页码小于1
                pageShow = 1;
            } else if (pageShow > pageCount && pageCount >= 1) {    //如果当前页码大于总页数，且总页数>=1
                pageShow = pageCount;
            }

            String searchUrl = "";

            if (search.equals("") == false) {
                searchUrl = URLEncoder.encode(search, "UTF-8");        //进行URL编码，以便在地址栏传递
            }

            if (pageShow <= 1) {
                page += "<span style='color:gray;'>首页&ensp;";
                page += "上一页&ensp;</span>";
            } else {
                page += "<a href='?pageUrl=1&searchUrl=" + searchUrl + "'>首页</a>&ensp;";
                page += "<a href='?pageUrl=" + (pageShow - 1) + "&searchUrl=" + searchUrl
                        + "'>上一页</a>&ensp;";
            }

            if (pageShow >= pageCount) {
                page += "<span style='color:gray;'>下一页&ensp;";
                page += "尾页</span>";
            } else {
                page += "<a href='?pageUrl=" + (pageShow + 1) + "&searchUrl=" + searchUrl
                        + "'>下一页</a>&ensp;";
                page += "<a href='?pageUrl=" + pageCount + "&searchUrl=" + searchUrl + "'>尾页</a>";
            }

            page += "&emsp;&emsp;";
            page += "页码：" + pageShow + "/" + pageCount + "&emsp;";
            page += "记录数：" + countRow + "&emsp;&emsp;";

            page += "输入页码:";
            page += "	<input type='text' name='pageShow' value='" + pageShow
                    + "' style='width:40px; text-align:center;'>";
            page += "	<input type='submit' name='buttonPage' value='提交'>&emsp;";

            if (pageShow > 0) {
                countShowed = (pageShow - 1) * pageSize;                    //（要略过的）之前的记录数
            }

            commodityCarList = commodityCarMapper.listCommodityCarByUserId(search, countShowed, pageSize, userId);    //****获取当前页的记录列表

            if (commodityCarList == null || commodityCarList.size() == 0) {
                msg = "购物车空空如也。";
                return false;
            }

            return true;

        } catch (Exception e) {
            msg = "系统发生错误。";
            e.printStackTrace();
        } finally {
            if (request.getAttribute("msg") != null) {
                msg = request.getAttribute("msg").toString() + msg;
            }
            request.setAttribute("msg", msg);

            if (request.getSession().getAttribute("msg") != null) {                //如果session中有消息。在详情页删除记录后保存的消息
                msg = request.getSession().getAttribute("msg").toString() + msg;//读取session中的消息
                request.getSession().removeAttribute("msg");                    //从session中移除此键值
                request.setAttribute("msg", msg);                                //将消息赋值给request
            }

            request.setAttribute("search", search);
            request.setAttribute("countShowed", countShowed);
            request.setAttribute("page", page);
            request.setAttribute("carList", commodityCarList);                        //****传递对象
        }

        return false;
    }

    /**
     * 商品详情页直接购买时创建购物车
     *
     * @param car
     * @return
     */
    @Override
    public CommodityCar saveCommodityCarOfShop(CommodityCar car) {
        car.setCarNo(Utils.generateCommodityCarNo());
        commodityCarMapper.saveCommodityCar(car);
        return commodityCarMapper.getCommodityCarByCarNo(car.getCarNo());
    }

    /**
     * 批量删除
     * @param request
     * @param commodityCarIdList
     * @return
     */
    public boolean deleteByIdList(HttpServletRequest request, Integer[] commodityCarIdList) {        //被本类中的selectAll()方法调用

        String msg = "";

        try {
            for (int i = 0; i < commodityCarIdList.length; i++) {
                try {
                    int result = commodityCarMapper.removeCommodityCar(commodityCarIdList[i]);        //****删除这条记录，本类中的方法

                    if (result == 0) {
                        msg = "删除记录失败！请重试。";
                        return false;
                    }

                } catch (Exception e) {
                    continue;                                                //略过此项
                }
            }

            msg = "删除记录成功。";
            return true;

        } catch (Exception e) {
            msg += "系统发生错误。";
            e.printStackTrace();
        } finally {
            if (request.getAttribute("msg") != null) {
                msg = request.getAttribute("msg").toString() + msg;
            }
            request.setAttribute("msg", msg);
        }

        return false;
    }

    /**
     * 购物车付款
     * @param request
     * @param commodityCarIdList
     * @return
     */
    public boolean payByIdList(HttpServletRequest request, Integer[] commodityCarIdList) {
        String msg = "";

        try {

            boolean result = payOrderService.payCommodityCar(commodityCarIdList);        //****删除这条记录，本类中的方法

            if (!result){
                msg = "支付失败,请售后再试";
            }

            msg = "支付成功！";
            return true;

        } catch (Exception e) {
            msg += "系统发生错误。";
            e.printStackTrace();
        } finally {
            if (request.getAttribute("msg") != null) {
                msg = request.getAttribute("msg").toString() + msg;
            }
            request.setAttribute("msg", msg);
        }

        return false;
    }
}
