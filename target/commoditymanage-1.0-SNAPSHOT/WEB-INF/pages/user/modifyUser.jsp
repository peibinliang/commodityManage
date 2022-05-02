<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息编辑</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
<h3>用户信息编辑</h3>
<c:if test="${ userInfo != null }">
    <table style="width:900px; margin:0px auto;" class="table_border table_padding10 mainContainer">
        <tr class="tr_header">
            <td>项目</td>
            <td>内容&emsp;&emsp;&emsp;&emsp;</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td class="left">
                <input type="text" name="userName" value="${ userInfo.userName }" maxlength="45">&ensp;
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>用户昵称</td>
            <td class="left">
                <input type="text" name="nickName" value="${ userInfo.nickName }" maxlength="45">&ensp;
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td class="left">
                <input type="password" name="password1">
                <span class="msg">*密码不修改请留空(密码不少于六位)</span>
            </td>
        </tr>
        <tr>
            <td>密码确认</td>
            <td class="left">
                <input type="password" name="password2" onblur="checkpwd()">
                <span class="msg">*密码不修改请留空(密码不少于六位)</span>
            </td>
        </tr>
        <tr>
            <td>手机号码</td>
            <td class="left">
                <input type="text" name="phoneNumber" value="${ userInfo.phoneNumber }">
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>用户角色</td>
            <td class="left">
                <label><input type="radio" name="role" value="1"
                              <c:if test="${ userInfo.userRole == 2 }">disabled='disabled'</c:if>
                              <c:if test="${ userInfo.userRole == 1 }">checked='checked'</c:if>
                >平台管理员</label>&ensp;
                <label><input type="radio" name="role" value="2"
                              <c:if test="${ userInfo.userRole == 2 }">disabled='disabled'</c:if>
                              <c:if test="${ userInfo.userRole == 2 }">checked='checked'</c:if>
                >普通用户</label>&ensp;
            </td>
        </tr>
        <tr>
            <td>备注</td>
            <td class="left">
                <input type="text" name="note" value="${userInfo.note}" maxlength="45">
            </td>
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
                <input type="submit" value="提交" onclick="ajax();">
                &emsp;&emsp;
                <a href="userShow?userId=${ userInfo.userId }">返回用户详情页</a>
            </td>
        </tr>
    </table>
</c:if>
<br>
<c:if test="${ userInfo.userRole == 1 }">
    <a href="userList">返回用户列表</a>
</c:if>
</body>
<script type="text/javascript">
    function ajax(){
        var param = {
            'userId':${ userInfo.userId },
            'userName':$('.mainContainer [name=userName]').val(),
            'nickName':$('.mainContainer [name=nickName]').val(),
            'password':$('.mainContainer [name=password1]').val(),
            'password2':$('.mainContainer [name=password2]').val(),
            'phoneNumber':$('.mainContainer [name=phoneNumber]').val(),
            'userRole':$('.mainContainer [name=role]:checked').val(),
            'note':$('.mainContainer [name=note]').val()
        }

        if (param.userName == null || param.userName === ""){
            param.userName = null
        }

        if (param.nickName == null || param.nickName === ""){
            param.nickName = null
        }

        if ((param.password == null || param.password === "" ) && (param.password2 == null || param.password2 === "")){
            param.password = null
        }else if (param.password !== param.password2){
            alert("两次密码不一样！")
            $('.mainContainer [name=password1]').val("")
            $('.mainContainer [name=password2]').val("")
        }else if(param.password.length <6){
            alert("密码长度不应小于六位")
            $('.mainContainer [name=password1]').val("")
            $('.mainContainer [name=password2]').val("")
        }

        let regex = /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$/;
        if (param.phoneNumber == null || param.phoneNumber === ""){
            param.phoneNumber = null
        }else if(!param.phoneNumber.match(regex)){
            alert("请录入正确的手机号码！");
        }

        var url = "/user/modifyUserDo";

        $.post(basePath+url, param, function(resp) {
            if (resp.success){
                var targetUrl = basePath+"/user/userShow?userId="+${userInfo.userId};
                window.location.href = targetUrl;
                return;
            }
            alert("修改失败");
        });
    }

    function checkpwd(){
        var password1 = $('.mainContainer [name=password1]').val()
        var password2 = $('.mainContainer [name=password2]').val()
        if (password1 != password2){
            alert("两次输入密码不一致！")
            $('.mainContainer [name=password1]').val("")
            $('.mainContainer [name=password2]').val("")
        }
    }
</script>
</html>
