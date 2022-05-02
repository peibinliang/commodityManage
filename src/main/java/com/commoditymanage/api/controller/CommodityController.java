package com.commoditymanage.api.controller;

import com.commoditymanage.api.BaseController;
import com.commoditymanage.core.model.ApiResponse;
import com.commoditymanage.entity.Commodity;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.CommodityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commodity")
@Api(tags = "商品管理接口")
public class CommodityController extends BaseController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/commodityList")
    public ModelAndView commodityList(HttpServletRequest request){
        commodityService.listCommodity(request);
        ModelAndView model = new ModelAndView("/commodity/commodityList");
        User user = (User)request.getSession().getAttribute("user");
        model.addObject("user",user);
        return model;
    }

    @GetMapping("/commodityShow")
    public ModelAndView commodityShow(Integer commodityId){
        Commodity commodityInfo = commodityService.getCommodityByCommodityId(commodityId);
        ModelAndView model = new ModelAndView("/commodity/commodityShow");
        model.addObject("commodityInfo",commodityInfo);
        return model;
    }

    @GetMapping("/modifyCommodity")
    public ModelAndView modifyUser(Integer commodityId){
        Commodity commodity = commodityService.getCommodityByCommodityId(commodityId);
        ModelAndView model = new ModelAndView("/commodity/modifyCommodity");
        model.addObject("commodityInfo",commodity);
        return model;
    }

    @PostMapping("/modifyCommodityDo")
    @ResponseBody
    public ApiResponse modifyCommodityDo(Commodity commodity,HttpServletRequest request){
        commodityService.modifyCommodity(commodity);
        return ApiResponse.success(null);
    }

    @GetMapping("/commodityAdd")
    public ModelAndView commodityAdd(){
        ModelAndView view = new ModelAndView("/commodity/commodityAdd");
        return view;
    }

    @PostMapping("/commodityAddDo")
    @ResponseBody
    public ApiResponse commodityAddDo(Commodity commodity){
        commodityService.saveCommodity(commodity);
        return ApiResponse.success(null);
    }

    @RequestMapping("/removeCommodity")
    public String removeCommodity(Integer commodityId){
        commodityService.removeCommodity(commodityId);
        return "redirect: commodityList";
    }

}
