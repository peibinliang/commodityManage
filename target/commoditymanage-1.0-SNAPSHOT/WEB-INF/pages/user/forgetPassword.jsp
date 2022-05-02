
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
    <h3>忘记密码</h3>
    <table style="width:900px; margin:0px auto;" class="table_border table_padding10 mainContainer">
        <tr class="tr_header">
            <td>项目</td>
            <td>内容&emsp;&emsp;&emsp;&emsp;</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td class="left">
                <input type="text" name="userName" maxlength="45">&ensp;
                <span class="msg">*</span>
            </td>
        </tr>
        <tr>
            <td>手机号码</td>
            <td class="left">
                <input type="text" name="phoneNumber">
                <span class="msg">*</span>
            </td>
        </tr>
        <tr>
            <td>新密码</td>
            <td class="left">
                <input type="password" name="password1">
                <span class="msg">*密码不少于六位</span>
            </td>
        </tr>
        <tr>
            <td>新密码确认</td>
            <td class="left">
                <input type="password" name="password2">
                <span class="msg">*密码不少于六位</span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="修改密码" onclick="ajax();">
            </td>
        </tr>
    </table>
</body>
<script type="text/javascript">
    function ajax(){
        let param = {
            'userName' : $('.mainContainer [name=userName]').val(),
            'password' : $('.mainContainer [name=password1]').val(),
            'password2' : $('.mainContainer [name=password2]').val(),
            'phoneNumber':$('.mainContainer [name=phoneNumber]').val(),
        }

        if (param.userName == null || param.userName === ""){
            alert('用户名不能为空');
        }

        if (param.password == null || param.password === "" || param.password2 == null || param.password2 === ""){
            alert("密码不能为空");
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
            alert("手机号码不能为空")
        }else if(!param.phoneNumber.match(regex)){
            alert("请录入正确的手机号码！");
        }

        let url = "/user/modifyPassword";

        $.post(basePath+url,param,function(resp){
            if (resp.success){
                let targetUrl = basePath+"/user/login";
                window.location.href = targetUrl;
                return;
            }
            alert(resp);
        });
    }
</script>
</html>
