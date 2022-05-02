<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    String staticPath = basePath + "/static";
    request.setAttribute("basePath", basePath);
    request.setAttribute("staticPath", staticPath);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script>
        // 将基础路径写入js变量，用于js获取
        window.basePath = '${basePath}'
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
            text-decoration: none;
        }

        .header {
            height: 80px;
            text-align: center;
            background-color: pink;
            padding: 20px 0;
        }

        #title {
            font-size: 50px;
        }
    </style>
</head>
<body>
<div class="header">
    <h1 id="title">小区商品管理系统</h1>
    <p style="text-align: right;">
        <c:if test="${user!=null}">
            <a href="${basePath}/user/main">首页</a>&emsp;&emsp;
            <a href="${basePath}/user/userShow">我的</a>&emsp;&emsp;
            <c:if test="${user.userRole == 2}">
                <a href="${basePath}/commodityCar/commodityCarList">购物车</a>&emsp;&emsp;
            </c:if>
            <a href="${basePath}/user/logout">注销</a>&emsp;&emsp;&emsp;&emsp;
        </c:if>
    </p>
</div>
</body>
</html>



