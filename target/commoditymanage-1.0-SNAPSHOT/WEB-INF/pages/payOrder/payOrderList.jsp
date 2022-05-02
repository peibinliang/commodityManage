<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
    <style>
        img {
            width: 15px;
            height: 15px;
        }
    </style>
</head>
<body>
<form action="" method="post">        <!-- 表单提交给对应的Servlet类UserList -->
    <div class="divGrid note right" style="margin-top:5px;">
        &emsp;&emsp;搜索：
        <input type="text" name="search" value="${ search }" style="width:80px;">
        <input type="submit" name="buttonQuery" value="搜索">
        <c:if test="${user.userRole == 2}">
            <span class="note">（在商品名称中搜索）</span>
        </c:if>
        <c:if test="${user.userRole == 1}">
            <span class="note">（在商品名称、用户名称中搜索）</span>
        </c:if>
    </div>

    <h3>订单列表</h3>

    <c:if test="${ payList.size() > 0 }">        <%-- 这里使用了jstl标签 --%>
        <div class="divGrid">
            <table style="width:900px; margin:0px auto;" class="table_border table_border_bg table_hover">
                <tr class="tr_header">
                    <td>序号</td>
                    <td>订单号</td>
                    <td>商品数量</td>
                    <td>下单时间</td>
                    <td>支付时间</td>
                    <td>订单金额</td>
                    <c:if test="${user.userRole == 1}">
                        <td>所属用户</td>
                    </c:if>
                    <td style="width:95px;">详情</td>
                    <c:if test="${user.userRole == 2}">
                        <td style="width:46px;">选择</td>
                    </c:if>
                </tr>

                <c:forEach var="item" items="${ payList }" varStatus="status">
                    <tr>
                        <td class="note">${status.index + countShowed + 1 }</td>
                        <td>${ item.orderNo }</td>
                        <td>${ item.num }</td>
                        <td>${ item.payTime }</td>
                        <td>${ item.payTime }</td>
                        <td>${ item.orderPrice }</td>
                        <c:if test="${user.userRole == 1}">
                            <td>${ item.userName }</td>
                        </c:if>
                        <td>
                            <a href="${basePath}/payOrder/payOrderShow?orderId=${ item.orderId }" title="详情">
                                <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce76.png" border="0"/></a>&emsp;
                        </td>
                        <c:if test="${user.userRole == 2}">
                            <td>
                                <input type="checkbox" name="orderId" value="${ item.orderId }">
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${user.userRole == 2}">
                    <tr>
                        <td colspan="9" class="note" style="text-align:right; height:50px;">
                            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                            <input type="submit" name="buttonDelete" value="删除订单"
                                   onclick="return confirm('确认删除所选记录？');">&emsp;
                            <label>全选:<input type="checkbox" name="checkboxAll"
                                             onchange="checkAll()"></label>&emsp;
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>

        <div class="divGrid note right">
                ${ page }
        </div>
    </c:if>
    <div id="msg" class="msg">${ msg }</div>
    <div id="msg" class="msg">
        <a href="${basePath}/commodity/commodityList">去逛一下？</a>
    </div>
</form>
</body>
<script type="text/javascript">
    function checkAll() {
        var checkboxList = document.getElementsByName("orderId");			//获取复选框列表，得到数组
        var checkboxAll = document.getElementsByName("checkboxAll")[0];	//全选复选框

        for (var i = 0; i < checkboxList.length; i++) {						//对于列表中的每一个复选框
            checkboxList[i].checked = checkboxAll.checked;					//此复选框的勾选情况与全选复选框一致
        }
    }
</script>
</html>
