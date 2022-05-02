package com.commoditymanage.api.controller;

import com.commoditymanage.core.model.ApiResponse;
import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.entity.PayOrder;
import com.commoditymanage.service.CommodityCarService;
import com.commoditymanage.service.PayOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/payOrder")
@Api(tags = "订单管理接口")
public class PayOrderController {
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private CommodityCarService commodityCarService;

    @PostMapping("/savePayOrderOfShop")
    @ResponseBody
    public ApiResponse savePayOrderOfShop(CommodityCar car){
        payOrderService.savePayOrderOfShop(car);
        return ApiResponse.success(null);
    }

    @RequestMapping("/payOrderList")
    public ModelAndView payOrderList(HttpServletRequest request){
        payOrderService.listPayOrder(request);
        ModelAndView view = new ModelAndView("/payOrder/payOrderList");
        return view;
    }

    @GetMapping("/payOrderShow")
    public ModelAndView payOrderShow(Integer orderId){
        ModelAndView view = new ModelAndView("/payOrder/payOrderShow");
        PayOrder orderInfo = payOrderService.getPayOrderByOrderId(orderId);
        view.addObject("orderInfo",orderInfo);
        List<CommodityCar> carList = payOrderService.listCommodityCarOfPayOrder(orderId);
        view.addObject("carList",carList);
        return view;
    }

}
