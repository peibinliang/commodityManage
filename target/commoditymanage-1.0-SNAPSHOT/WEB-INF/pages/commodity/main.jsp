<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h3>用户首页</h3>
<c:if test="${user != null}">
    <table class="table_border table_border_bg table_hover" style="width:600px; margin:0 auto;">
        <tr height="50" class="tr_header">
            <th colspan="2">
                用户功能列表
            </th>
        </tr>

        <tr height="50">
            <td width="30%">
                登录相关
            </td>
            <td class="left">&emsp;
                <a href="userShow?userId=${ user.userId }">个人信息</a>&emsp;&emsp;
                <a href="logout">退出登录</a>&emsp;&emsp;
            </td>
        </tr>

        <c:if test="${ user.userRole == 2}">
            <tr height=""50>
                <td>
                    商品管理
                </td>
                <td class="left">&emsp;
                    <a href="${basePath}/commodity/commodityList/">商品列表</a>&emsp;&emsp;
                    <a href="${basePath}/commodityCar/commodityCarList">我的购物车</a>&emsp;&emsp;
                </td>
            </tr>
            <tr height="50">
                <td>
                    我的订单
                </td>
                <td>&emsp;
                    <a href="${basePath}/payOrder/payOrderList">列表</a>&emsp;&emsp;
                </td>
            </tr>
        </c:if>

        <c:if test="${ user.userRole == 1 }">
            <tr height="50">
                <td>
                    商品管理
                </td>
                <td class="left">&emsp;
                    <a href="${basePath}/commodity/commodityList/">列表</a>&emsp;&emsp;
                    <a href="${basePath}/commodity/commodityAdd/">新添</a>&emsp;&emsp;
                </td>
            </tr>
            <tr height="50">
                <td>
                    商品订单管理
                </td>
                <td class="left">&emsp;
                    <a href="${basePath}/payOrder/payOrderList">列表</a>
                </td>
            </tr>
        </c:if>

        <c:if test="${ user.userRole == 1 }">
            <tr height="50">
                <td>
                    用户管理
                </td>
                <td class="left">&emsp;
                    <a href="userList">列表</a>&emsp;&emsp;
                    <a href="userAdd">新添</a>&emsp;&emsp;
                </td>
            </tr>
        </c:if>
    </table>
</c:if>

</body>
</html>
