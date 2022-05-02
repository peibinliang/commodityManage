
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
  <%@include file="../common/head.jsp" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
  <h3>用户注册</h3>
  <table style="width:900px; margin:0px auto;" class="table_border table_padding10 mainContainer">
    <tr class="tr_header">
      <td>项目</td>
      <td>内容&emsp;&emsp;&emsp;&emsp;</td>
    </tr>
    <tr>
      <td>用户名</td>
      <td class="left">
        <input type="text" name="userName" value="${ userInfo.userName }" maxlength="45" onblur="checkUserName()">&ensp;
        <span class="msg">*</span>
      </td>
    </tr>
    <tr>
      <td>用户昵称</td>
      <td class="left">
        <input type="text" name="nickName" value="${ userInfo.nickName }" maxlength="45" onblur="checkNickName()">&ensp;
        <span class="msg">*</span>
      </td>
    </tr>
    <tr>
      <td>密码</td>
      <td class="left">
        <input type="password" name="password1">
        <span class="msg">*密码不少于六位</span>
      </td>
    </tr>
    <tr>
      <td>密码确认</td>
      <td class="left">
        <input type="password" name="password2" onblur="checkPwd()">
        <span class="msg">*密码不少于六位</span>
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
      <td>备注</td>
      <td class="left">
        <input type="text" name="note" value="${userInfo.note}" maxlength="45">
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="注册" onclick="ajax();">
      </td>
    </tr>
  </table>
</body>
<script type="text/javascript">
  function checkUserName(){
    let userName = $('.mainContainer [name=userName]').val();
    if (userName == null || userName === ""){
      alert('用户名不能为空');
    }
  }

  function checkNickName(){
    let nickName = $('.mainContainer [name=nickName]').val();
    if (nickName == null || nickName === ""){
      alert("用户昵称不能为空");
    }
  }

  function checkPwd(){
    let password1 = $('.mainContainer [name=password1]').val()
    let password2 = $('.mainContainer [name=password2]').val()
    if (password1 == null || password1 === "" || password2 == null || password2 === ""){
      alert("密码不能为空");
    }else if (password1 !== password2){
      alert("两次密码不一样！")
      $('.mainContainer [name=password1]').val("")
      $('.mainContainer [name=password2]').val("")
    }else if(password1.length <6){
      alert("密码长度不应小于六位")
      $('.mainContainer [name=password1]').val("")
      $('.mainContainer [name=password2]').val("")
    }
  }

  function ajax(){
    let param = {
      'userName' : $('.mainContainer [name=userName]').val(),
      'nickName' : $('.mainContainer [name=nickName]').val(),
      'password' : $('.mainContainer [name=password1]').val(),
      'password2' : $('.mainContainer [name=password2]').val(),
      'phoneNumber':$('.mainContainer [name=phoneNumber]').val(),
      'userRole':2,
      'note':$('.mainContainer [name=note]').val()
    }

    if (param.userName == null || param.userName === ""){
      alert('用户名不能为空');
    }

    if (param.nickName == null || param.nickName === ""){
      alert("用户昵称不能为空");
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

    let url = "/user/userAddDo";

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
