package com.commoditymanage.service;

import com.commoditymanage.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 示例模块测试用例
 * @author ~
 * @date 2022/04/09 17:56
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void removeUser(){
        print(userService.removeUser(1));
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setUserName("luohao");
        user.setPassword("123456");
        user.setNickName("罗灏");
        user.setUserRole(1);
        userService.saveUser(user);
    }

    @Test
    public void getUser(){
        userService.getUserByUserId(1);
    }

    @Test
    public void login(){
        User user = new User();
        user.setUserName("zhangsan");
        user.setPassword("123456");
        userService.login(user);
    }

}
