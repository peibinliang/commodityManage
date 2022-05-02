package com.commoditymanage.service.impl;

import com.commoditymanage.dao.UserMapper;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean saveUser(User user) {
        if (Objects.nonNull(userMapper.getUserByUserName(user.getUserName()))){
            throw new RuntimeException("用户名已存在");
        }
        return userMapper.saveUser(user)>0;
    }

    /**
     * 移除用户
     *
     * @param userId
     * @return
     */
    @Override
    public boolean removeUser(Integer userId) {
        return userMapper.removeUser(userId)>0;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean modifyUser(User user) {
        User oldUser = getUserByUserId(user.getUserId());
        if(oldUser.getUserName().equals(user.getUserName()) || user.getUserName().length()<1){
            user.setUserName(null);
        }else if (oldUser.getNickName().equals(user.getNickName()) || user.getNickName().length()<1){
            user.setNickName(null);
        }else if (oldUser.getPassword().equals(user.getPassword()) || user.getPassword().length() < 6){
            user.setPassword(null);
        }else if (oldUser.getUserRole().equals(user.getUserRole())){
            user.setUserRole(null);
        }else if (oldUser.getNote().equals(user.getNote()) || user.getNote().length()<1){
            user.setNote(null);
        }
        return userMapper.modifyUser(user)>0;
    }

    /**
     * 用户列表
     * @param request
     * @return
     */
    @Override
    public Boolean listUser(HttpServletRequest request) {
        List<User> userList = new ArrayList<User>(); 		//记录列表
        String search = ""; 								//搜索的内容
        int countShowed = 0;	 							//（要略过的）之前的记录数
        int pageShow = 1; 									//当前页码
        String page = ""; 									//页码链接组
        String msg = "";

        try {
            if (request.getParameter("buttonDelete") != null) { 				//如果单击了删除按钮

                String[] userIdArray = request.getParameterValues("userId"); 	//获取ID列表
                Integer[] userIdList = new Integer[userIdArray.length];
                for (int i = 0;i<userIdList.length;i++ ){
                    userIdList[i] = Integer.parseInt(userIdArray[i]);
                }
                if (userIdArray != null) {
                    deleteByIdList(request, userIdList);						//****删除所选。调用在本类中定义的方法
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

            int countRow = userMapper.countUser(search);	//****获得记录总数

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

            userList = userMapper.selectAll(search, countShowed, pageSize);	//****获取当前页的记录列表

            if (userList == null || userList.size() == 0) {
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
            request.setAttribute("userList", userList);							//****传递对象
        }

        return false;
    }

    public boolean isMe (HttpServletRequest request, Integer userId) {	//被本类中的DeleteUser()和deleteByIdList()方法调用

        User myUser = (User) request.getSession().getAttribute("user");

        if (myUser == null) {
            return false;
        }

        Integer myUserId = myUser.getUserId();

        if (myUserId == userId) {									//如果是用户自己
            return true;
        }

        return false;
    }

    public boolean deleteByIdList (HttpServletRequest request, Integer[] userIdArray) {		//被本类中的selectAll()方法调用

        String msg = "";

        try {
            for (int i = 0; i < userIdArray.length; i++) {
                try {
                    if (isMe(request, userIdArray[i])) {					//****调用本类中的方法isMe()。如果是自己
                        msg = "不许删除自己。";
                        return false;
                    }

                    int result = userMapper.removeUser(userIdArray[i]);		//****删除这条记录，本类中的方法

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

    /**
     * 根据用户Id获取用户详情
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

    /**
     * 根据用户名称获取用户详情
     *
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        if (userName.equals("")){
            return null;
        }
        return userMapper.getUserByUserName(userName.trim());
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        User result = userMapper.getUserByUserName(userName);
        System.out.println("result======"+result);
        if (Objects.isNull(result)){
            throw new RuntimeException("用户不存在");
        }else if (result.getPassword().equals(password)){
            System.out.println("result===="+result);
            return result;
        }else {
            throw new RuntimeException("登录失败！账号或者密码错误");
        }
    }

    /**
     * 忘记密码
     *
     * @param user
     * @return
     */
    @Override
    public Boolean modifyPassword(User user) {
        User userByUserName = getUserByUserName(user.getUserName());
        if (Objects.isNull(userByUserName)){
            throw new RuntimeException("用户不存在");
        }else if (!userByUserName.getPhoneNumber().equals(user.getPhoneNumber())){
            throw new RuntimeException("用户们和手机号不对应");
        }
        user.setUserId(userByUserName.getUserId());
        return userMapper.modifyPassword(user)>0;
    }


}
