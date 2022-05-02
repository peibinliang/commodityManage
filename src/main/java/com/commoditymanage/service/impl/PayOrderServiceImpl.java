package com.commoditymanage.service.impl;

import com.commoditymanage.dao.PayOrderMapper;
import com.commoditymanage.entity.Commodity;
import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.entity.PayOrder;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.CommodityCarService;
import com.commoditymanage.service.CommodityService;
import com.commoditymanage.service.PayOrderRelationService;
import com.commoditymanage.service.PayOrderService;
import com.commoditymanage.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liangpeibin
 * @ClassName PayOrderServiceImpl
 * @Description
 * @since 2022/4/23 17:13
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private CommodityCarService commodityCarService;
    @Resource
    private PayOrderRelationService payOrderRelationService;
    @Resource
    private CommodityService commodityService;

    /**
     * 新增支付订单
     *
     * @param payOrder
     * @return
     */
    @Override
    public boolean savePayOrder(PayOrder payOrder) {
        return payOrderMapper.insert(payOrder)>0;
    }

    /**
     * 删除支付订单
     *
     * @param orderId
     * @return
     */
    @Override
    public boolean removePayOrder(Integer orderId) {
        return payOrderMapper.removePayOrder(orderId)>0;
    }

    /**
     * 获取支付订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public PayOrder getPayOrderByOrderId(Integer orderId) {
        return payOrderMapper.getPayOrderByOrderId(orderId);
    }


    /**
     * 商品详情直接购买
     *
     * @param car
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class) //事务,报错就回滚
    public Boolean savePayOrderOfShop(CommodityCar car) {
        CommodityCar car1 = commodityCarService.saveCommodityCarOfShop(car);//新增购物车信息并返回
        Commodity commodity = commodityService.getCommodityByCommodityId(car1.getCommodityId());//获取商品信息
        commodity.setStock(commodity.getStock()-car.getNum());//计算剩下库存
        if (commodity.getStock() == 0){//若库存为零,则下架商品
            commodity.setStatus(0);
        }else if(commodity.getStock() < 0){//库存不足,抛异常
            throw new RuntimeException("库存不足！可减少购买量或稍等商家补货");
        }
        PayOrder order = new PayOrder();//实例化订单对象
        order.setUserId(car.getUserId());//设置订单关联用户
        order.setOrderNo(Utils.generatePayOrderNo());//生成订单编号
        order.setOrderPrice(car1.getTotalPrice());//设置订单价钱
        Integer orderId = payOrderMapper.savePayOrder(order);//保存订单
        payOrderRelationService.savePayOrderRelation(orderId,new ArrayList<>(car1.getCarId()));//保存订单和购物车信息的关联关系，即订单包含哪些商品
        commodityCarService.removeCommodityCar(car1.getCarId());//移除订单关联的购物车信息(逻辑删除,用户不可见,后台数据不删除)
        commodityService.modifyCommodity(commodity);//更新库存
        return true;
    }

    /**
     * 购物车付款
     *
     * @param carIdList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean payCommodityCar(Integer[] carIdList) {
        Integer userId = commodityCarService.getCommodityCarByCarId(carIdList[0]).getUserId();//获取订单关联用户
        BigDecimal totalPrice = new BigDecimal(0);
        for (Integer carId : carIdList) {
            CommodityCar commodityCar = commodityCarService.getCommodityCarByCarId(carId);//获取购物车信息
            Commodity commodity = commodityService.getCommodityByCommodityId(commodityCar.getCommodityId());//获取商品信息
            commodity.setStock(commodity.getStock()-commodityCar.getNum());//计算剩下库存
            if (commodity.getStock() == 0){//若库存为零,则下架商品
                commodity.setStatus(0);
            }else if(commodity.getStock() < 0){//库存不足,抛异常
                throw new RuntimeException("库存不足！可减少购买量或稍等商家补货");
            }
            commodityService.modifyCommodity(commodity);//更新库存
            BigDecimal price = commodityCar.getTotalPrice();
            totalPrice = totalPrice.add(price);//累加订单价格
        }
        PayOrder order = new PayOrder();//实例化订单对象
        order.setUserId(userId);//设置订单关联用户
        order.setOrderNo(Utils.generatePayOrderNo());//生成订单编号
        order.setOrderPrice(totalPrice);//设置订单价钱
        payOrderMapper.savePayOrder(order);//保存订单
        order = payOrderMapper.getPayOrderByOrderNo(order.getOrderNo());//根据订单号获取订单
        payOrderRelationService.savePayOrderRelation(order.getOrderId(), new ArrayList<Integer>(Arrays.asList(carIdList)));//批量保存订单和购物车信息的关联关系，即订单包含哪些商品
        for (Integer carId : carIdList) {
            commodityCarService.removeCommodityCar(carId);//移除订单关联的购物车信息(逻辑删除,用户不可见,后台数据不删除)
        }
        return true;
    }

    /**
     * 订单列表
     *
     * @param request
     * @return
     */
    @Override
    public boolean listPayOrder(HttpServletRequest request) {
        List<PayOrder> payList = new ArrayList<PayOrder>(); 	//记录列表
        String search = ""; 								//搜索的内容
        int countShowed = 0;	 							//（要略过的）之前的记录数
        int pageShow = 1; 									//当前页码
        String page = ""; 									//页码链接组
        String msg = "";
        User user = (User)request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        Integer userRole = user.getUserRole();

        try {
            if (request.getParameter("buttonDelete") != null) { 				//如果单击了删除按钮

                String[] orderIdArray = request.getParameterValues("orderId"); 	//获取ID列表
                Integer[] orderIdList = new Integer[orderIdArray.length];

                for (int i = 0; i < orderIdArray.length;i++) {
                    orderIdList[i] = Integer.parseInt(orderIdArray[i]);
                }

                if (orderIdArray != null) {
                    deleteByIdList(request, orderIdList);						//****删除所选。调用在本类中定义的方法
                }
            }

            String buttonQuery	= request.getParameter("buttonQuery");	//数据查询按钮
            String buttonPage  	= request.getParameter("buttonPage"); 	//页码提交按钮
            String pageInput  	= "1";									//输入的页码

            if (buttonQuery != null) { 									//如果按下了数据查询按钮
                search = request.getParameter("search").trim(); 		//搜索内容
            } else if (buttonPage != null) { 							//如果按下了页码提交按钮
                search 	= request.getParameter("search").trim(); 		//搜索内容
                pageInput	= request.getParameter("pageShow"); 		//页码输入框中的值
            } else { 													//点击了页码链接，或者刚打开此页
                if (request.getParameter("searchUrl") != null) {
                    search = request.getParameter("searchUrl"); 		//不需要进行解码操作，系统会自动解码
                }

                if (request.getParameter("pageUrl") != null) {			//地址栏中的页码
                    pageInput = request.getParameter("pageUrl");
                }
            }

            int countRow = 0;
            if (userRole == 1) {
                countRow = payOrderMapper.countPayOrder(search);    //****获得记录总数
            }else {
                countRow = payOrderMapper.countPayOrderByUserId(search,userId);
            }

            int pageSize  = 6;  							//每页6条记录
            int pageCount = 0; 								//预设总页数为0

            if (countRow % pageSize == 0) {					//如果余数为0，即能整除
                pageCount = countRow / pageSize; 			//总页数
            } else {
                pageCount = countRow / pageSize + 1;		//不能整除则加1页。如果除数为小数，将自动去除小数部分得到整数
            }

            try {
                pageShow = Integer.parseInt(pageInput);		//如果是数字，返回字符串对应的整数
            } catch (Exception e) {
                pageShow = 1; 							//如果抛出异常，则取预设值
            }

            if (pageShow < 1) {								//如果当前页码小于1
                pageShow = 1;
            } else if (pageShow > pageCount && pageCount >= 1) { 	//如果当前页码大于总页数，且总页数>=1
                pageShow = pageCount;
            }

            String searchUrl = "";

            if (search.equals("") == false) {
                searchUrl = URLEncoder.encode(search, "UTF-8");		//进行URL编码，以便在地址栏传递
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
                countShowed = (pageShow - 1) * pageSize;					//（要略过的）之前的记录数
            }

            if (userRole == 1) {
                payList = payOrderMapper.listPayOrder(search, countShowed, pageSize);    //****获取当前页的记录列表
            }else {
                payList = payOrderMapper.listPayOrderByUserId(search, countShowed, pageSize, userId);
            }

            if (payList == null || payList.size() == 0) {
                msg = "查无记录。";
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

            if (request.getSession().getAttribute("msg") != null) {				//如果session中有消息。在详情页删除记录后保存的消息
                msg = request.getSession().getAttribute("msg").toString() + msg;//读取session中的消息
                request.getSession().removeAttribute("msg");					//从session中移除此键值
                request.setAttribute("msg", msg);								//将消息赋值给request
            }

            request.setAttribute("search", search);
            request.setAttribute("countShowed", countShowed);
            request.setAttribute("page", page);
            request.setAttribute("payList", payList);						//****传递对象
        }

        return false;
    }

    /**
     * 根据订单获取关联的购物车信息
     *
     * @param orderId
     * @return
     */
    @Override
    public List<CommodityCar> listCommodityCarOfPayOrder(Integer orderId) {
        return payOrderMapper.listCommodityCarOfPayOrder(orderId);
    }

    public boolean deleteByIdList (HttpServletRequest request, Integer[] orderIdArray) {		//被本类中的selectAll()方法调用

        String msg = "";

        try {
            for (int i = 0; i < orderIdArray.length; i++) {
                try {
                    int result = payOrderMapper.removePayOrder(orderIdArray[i]);		//****删除这条记录，本类中的方法

                    if (result == 0) {
                        msg = "删除记录失败！请重试。";
                        return false;
                    }

                } catch (Exception e) {
                    continue;												//略过此项
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
}
