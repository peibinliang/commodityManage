package com.commoditymanage.service;


import com.commoditymanage.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *   用户服务类
 * </p>
 *
 * @author ~
 * @since 2022/4/10
 */
public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 移除用户
     * @param userId
     * @return
     */
    boolean removeUser(Integer userId);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean modifyUser(User user);

    /**
     * 用户列表
     * @param request
     * @return
     */
    Boolean listUser(HttpServletRequest request);

    /**
     * 根据用户Id获取用户详情
     * @param userId
     * @return
     */
    User getUserByUserId(Integer userId);

    /**
     * 根据用户名称获取用户详情
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 忘记密码
     * @param user
     * @return
     */
    Boolean modifyPassword(User user);
}
