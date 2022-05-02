<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <%@include file="../common/head.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        *{
            margin: 0;
            padding: 0;
            text-decoration: none;
        }
        .mainContainer{
            margin-top: 50px;
            padding: 20px;
            border: aquamarine 3px solid;
            border-radius: 10%;
        }
    </style>
</head>
<body>
    <br><br><br><br>
    <div class="mainContainer" style="width:300px; margin:0px auto; text-align: center;">
        <table>
            <tr>
                <td colspan="2">
                    <h3>用户登录</h3>
                </td>
            </tr>
            <tr>
                <td>
                    账号:
                </td>
                <td>
                    <input type="text" name="userName">
                </td>
            </tr>
            <tr>
                <td>
                    密码:
                </td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="登录" onclick="ajax()">
                </td>
            </tr>
            <tr>
                <td>
                    <a href="forgetPassword">忘记密码?</a>
                </td>
                <td>
                    <a href="userRegister">没有账号?去注册</a>
                </td>
            </tr>
        </table>
    </div>
    <div style="margin: 10px auto;text-align: center;">
        管理员账号:zhangsanfeng/admim<br>密码:qwerty/admin<br>
        平台用户账号:zhangsan/badBoy<br>密码:123456/123456
    </div>
</body>
<script type="text/javascript">

    function ajax() {												//应用Ajax技术实现登录
        console.log("点击登录了");
        var param = {
            'userName':$('.mainContainer [name=userName]').val(),
            'password':$('.mainContainer [name=password]').val()
        }
        console.log("param="+param.userName)
        var url = "/user/loginCheck";

        $.post(basePath+url, param, function(resp) {
            console.log("进入登录验证")
            if (resp.success){
                console.log("登录成功！")
                var targetUrl = basePath+"/user/main"
                console.log("目标地址:"+targetUrl)
                window.location.href = targetUrl;
                console.log("登录参数:"+param)
                return;
            }
            alert("登录失败！账号或者密码错误！");
        });
    }

</script>
</html>