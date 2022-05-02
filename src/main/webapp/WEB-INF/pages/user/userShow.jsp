<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户详情</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
<h3>用户详情</h3>
<c:if test="${ userInfo != null }">
    <table style="width:900px; margin:0px auto;" class="table_border table_padding10">
        <tr class="tr_header">
            <td>项目</td>
            <td>内容&emsp;&emsp;&emsp;&emsp;</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td class="left">${ userInfo.userName }</td>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td class="left">${ userInfo.nickName }</td>
        </tr>
        <tr>
            <td>角色</td>
            <td class="left">
                <c:if test="${ userInfo.userRole == 1 }">
                    平台管理员
                </c:if>
                <c:if test="${ userInfo.userRole == 2 }">
                    普通用户
                </c:if>
            </td>
        </tr>
        <tr>
            <td>手机号码</td>
            <td class="left">${ userInfo.phoneNumber }</td>
        </tr>
        <tr>
            <td>备注</td>
            <td class="left">${userInfo.note}</td>
        </tr>
        <tr>
            <td>注册时间</td>
            <td class="left">${userInfo.gmtCreate}</td>
        </tr>
        <tr>
            <td>更新时间</td>
            <td class="left">${ userInfo.gmtModify }</td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="modifyUser?userId=${ userInfo.userId }">修改</a>&emsp;
                <c:if test="${ userInfo.userId != user.userId }"> <!-- 不是自己才显示删除链接 -->
                    <a href="userDeleteDo?userId=${ userInfo.userId }"
                       onclick="return confirm('确定要删除吗？');">删除</a></c:if>
            </td>
        </tr>
    </table>
</c:if>
</body>
</html>
