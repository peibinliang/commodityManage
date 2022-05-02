package com.commoditymanage.interceptor;

import com.commoditymanage.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liangpeibin
 * @ClassName LoginInterceptor
 * @Description
 * @since 2022/4/27 20:06
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        if (url.indexOf("/login")>=0){
            return true;
        }
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        if (user!=null) {
            return true;
        }
        request.setAttribute("msg", "账号密码有误，请重新登录！");
        url = request.getScheme() + "://"+request.getServerName() + ":"+request.getServerPort() + request.getContextPath()+"/user/login";
        request.getRequestDispatcher(url).forward(request, response);
        return false;
    }
}
