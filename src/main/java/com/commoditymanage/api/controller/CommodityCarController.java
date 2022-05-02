package com.commoditymanage.api.controller;

import com.commoditymanage.core.model.ApiResponse;
import com.commoditymanage.entity.CommodityCar;
import com.commoditymanage.service.CommodityCarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commodityCar")
@Api(tags = "购物车管理接口")
public class CommodityCarController {
    @Autowired
    private CommodityCarService commodityCarService;

    @PostMapping("/saveCommodityCar")
    @ResponseBody
    public ApiResponse saveCommodityCar(CommodityCar commodityCar){
        commodityCarService.saveCommodityCar(commodityCar);
        return ApiResponse.success(null);
    }

    @RequestMapping("/commodityCarList")
    public ModelAndView commodityCarList(HttpServletRequest request){
        commodityCarService.listCommodityCarByUserId(request);
        ModelAndView view = new ModelAndView("/commodityCar/commodityCarList");
        return view;
    }
}
