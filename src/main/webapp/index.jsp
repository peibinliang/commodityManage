<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    String staticPath = basePath + "/static";
    request.setAttribute("basePath", basePath);
    request.setAttribute("staticPath", staticPath);
%>
<head>
    <title>小区商品管理系统</title>
    <link rel="stylesheet" href="${staticPath}/styles/index.css" />
    <style>
        .config{
            margin: auto;
        }
    </style>
</head>
<body>
<br><br><br><br>
<div class="config">
    <h1>小区管理系统</h1>
    <ul>
        <li><label>持久层</label><span>MyBatis</span></li>
        <li><label>视图层</label><span>JSP</span></li>
        <li><label>单元测试</label><span>JUnit</span></li>
        <li><label>Swagger</label><span>有（采用Swagger-Bootstrap-UI）</span></li>
        <li><label>Lombok</label><span>有</span></li>
<%--        <li><label>示例代码</label><span>有</span><a href="${basePath}/user/manage" target="_blank">查看示例功能</a></li>--%>
        <li><label>数据库</label><span>MySql数据库</span></li>
<%--        <li><label>测试</label><span>测试</span><a href="${basePath}/user/remove" target="_blank">111</a></li>--%>
        <li><label>路途开始</label><span>开始购物--></span><a href="${basePath}/user/login">去登录</a></li>
    </ul>
</div>
</body>
</html>
