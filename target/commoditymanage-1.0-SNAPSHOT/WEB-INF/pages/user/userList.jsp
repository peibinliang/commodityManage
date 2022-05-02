<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
    <style>
        img{
            width: 15px;
            height: 15px;
        }
    </style>
</head>
<body>
<form action="" method="post">		<!-- 表单提交给对应的Servlet类UserList -->
    <div class="divGrid note right" style="margin-top:5px;">
        &emsp;&emsp;搜索：
        <input type="text" name="search" value="${ search }" style="width:80px;">
        <input type="submit" name="buttonQuery" value="搜索">
        <span class="note">（在用户名、真实姓名和角色中搜索）</span>
    </div>

    <h3>用户列表</h3>

    <c:if test="${ userList.size() > 0 }">		<%-- 这里使用了jstl标签 --%>
        <div class="divGrid">
            <table style="width:900px; margin:0px auto;" class="table_border table_border_bg table_hover">
                <tr class="tr_header">
                    <td>序号</td>
                    <td>用户名</td>
                    <td>真实姓名</td>
                    <td>手机号码</td>
                    <td>用户角色</td>
                    <td>创建时间</td>
                    <td>更新时间</td>
                    <td style="width:95px;">详情/修改</td>
                    <td style="width:46px;">选择</td>
                </tr>

                <c:forEach var="item" items="${ userList }" varStatus="status">
                    <tr>
                        <td class="note">${status.index + countShowed + 1 }</td>
                        <td>${ item.userName }</td>
                        <td>${ item.nickName }</td>
                        <td>${ item.phoneNumber }</td>
                        <td>${ item.userRole }</td>
                        <td>${ item.gmtCreate }</td>
                        <td>${ item.gmtModify }</td>
                        <td>
                            <a href="userShow?userId=${ item.userId }" title="详情">
                                <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce76.png" border="0" /></a>&emsp;
                            <a href="modifyUser?userId=${ item.userId }" title="修改">
                                <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce73.png" border="0" /></a>
                        </td>
                        <td>
                            <input type="checkbox" name="userId" value="${ item.userId }"
                                   <c:if test="${ item.userId == user.userId }">disabled='disabled'</c:if>
                            >
                        </td>
                    </tr>
                </c:forEach>

                <tr>
                    <td colspan="9" class="note" style="text-align:right; height:50px;">
                        （<a href="userAdd">新添用户</a>）
                        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                        <input type="submit" name="buttonDelete" value="删除"
                               onclick="return confirm('确认删除所选记录？');">
                        &emsp;
                        <label>全选:<input type="checkbox" name="checkboxAll"
                                         onchange="checkAll()"></label>&emsp;
                    </td>
                </tr>
            </table>
        </div>

        <div class="divGrid note right">
                ${ page }
        </div>

    </c:if>
</form>
</body>
<script type="text/javascript">
    function checkAll() {
        var checkboxList = document.getElementsByName("userId");			//获取复选框列表，得到数组
        var checkboxAll  = document.getElementsByName("checkboxAll")[0];	//全选复选框

        for (var i = 0; i < checkboxList.length; i++) {						//对于列表中的每一个复选框
            checkboxList[i].checked = checkboxAll.checked;					//此复选框的勾选情况与全选复选框一致
        }
    }
</script>
</html>
