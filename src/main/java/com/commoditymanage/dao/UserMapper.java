package com.commoditymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commoditymanage.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 移除用户
     * @param userId
     * @return
     */
    Integer removeUser(@Param("userId") Integer userId);


    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Integer saveUser(@Param("user") User user);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getUserByUserId(@Param("userId") Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer modifyUser(@Param("user") User user);

    /**
     * 计算搜索出来的用户条数
     * @param search
     * @return
     */
    Integer countUser(@Param("search") String search);

    /**
     * 用户列表搜索
     * @param search
     * @param countShowed
     * @param pageSize
     * @return
     */
    List<User> selectAll(@Param("search") String search, @Param("countShowed") int countShowed, @Param("pageSize") int pageSize);

    /**
     * 忘记密码
     * @param user
     * @return
     */
    Integer modifyPassword(@Param("user") User user);
}
