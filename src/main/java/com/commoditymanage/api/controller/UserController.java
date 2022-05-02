package com.commoditymanage.api.controller;

import com.commoditymanage.api.BaseController;
import com.commoditymanage.core.model.ApiResponse;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author ~
 * @date 2022/04/09 17:56
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户模块接口")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView("/user/login");
        return model;
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public ApiResponse loginCheck(User user, HttpServletRequest request){
        System.out.println("user==="+user);
        User result = userService.login(user);
        System.out.println("result==="+result);
        request.getSession().setAttribute("user",result);
        return ApiResponse.success(null);
    }

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView model = new ModelAndView("/commodity/main");
        return model;
    }

    @GetMapping("/userShow")
    public ModelAndView userShow(Integer userId){
        User user = userService.getUserByUserId(userId);
        ModelAndView model = new ModelAndView("/user/userShow");
        model.addObject("userInfo",user);
        return model;
    }

    @GetMapping("/modifyUser")
    public ModelAndView modifyUser(Integer userId){
        User user = userService.getUserByUserId(userId);
        ModelAndView model = new ModelAndView("/user/modifyUser");
        model.addObject("userInfo",user);
        return model;
    }

    @PostMapping("/modifyUserDo")
    @ResponseBody
    public ApiResponse modifyUserDo(User user,HttpServletRequest request){
        System.out.println("user===="+user);
        userService.modifyUser(user);
        User user1 = userService.getUserByUserId(user.getUserId());
        request.getSession().invalidate();
        request.getSession().setAttribute("user",user1);
        return ApiResponse.success(null);
    }

    @RequestMapping("/userList")
    public ModelAndView userList(HttpServletRequest request){
        userService.listUser(request);
        ModelAndView model = new ModelAndView("/user/userList");
        return model;
    }

    @GetMapping("/userAdd")
    public ModelAndView userAdd(){
        ModelAndView model = new ModelAndView("/user/userAdd");
        return model;
    }

    @PostMapping("/userAddDo")
    @ResponseBody
    public ApiResponse userAddDo(User user){
        userService.saveUser(user);
        return ApiResponse.success(null);
    }

    @GetMapping("/userRegister")
    public ModelAndView userRegister(){
        ModelAndView model = new ModelAndView("/user/userRegister");
        return model;
    }

    @GetMapping("/forgetPassword")
    public ModelAndView forgetPassword(){
        ModelAndView model = new ModelAndView("/user/forgetPassword");
        return model;
    }

    @PostMapping("/modifyPassword")
    @ResponseBody
    public ApiResponse modifyPassword(User user){
        userService.modifyPassword(user);
        return ApiResponse.success(null);
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().invalidate();
        ModelAndView model = new ModelAndView("/user/login");
        return model;
    }
}
