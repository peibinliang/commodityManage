<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
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
<h3>订单详情</h3>

<c:if test="${ carList.size() > 0 && orderInfo != null}">        <%-- 这里使用了jstl标签 --%>
    <div class="divGrid">
        <table style="width:1150px; margin:0px auto;" class="table_border table_border_bg table_hover">
            <tr class="tr_header">
                <td>序号</td>
                <td>商品名称</td>
                <td>商品数量</td>
                <td>商品单价</td>
                <td>总价格</td>
            </tr>

            <c:forEach var="item" items="${ carList }" varStatus="status">
                <tr>
                    <td class="note">${status.index + countShowed + 1 }</td>
                    <td>
                        <a href="${basePath}/commodity/commodityShow?commodityId=${ item.commodityId }">${ item.commodityName }</a>
                    </td>
                    <td>${ item.num}</td>
                    <td>${ item.unitPrice }</td>
                    <td>${ item.totalPrice }</td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td colspan="2" class="left">
                    订单编号:${orderInfo.orderNo}
                </td>
                <td colspan="2" class="right">
                    ￥${orderInfo.orderPrice}
                </td>
            </tr>
        </table>
    </div>
    <div id="msg" class="msg">
        <a href="${basePath}/commodity/commodityList">去逛一下？</a>&emsp;
        <a href="${basePath}/payOrder/payOrderList">返回上一页</a>
    </div>
</c:if>
</body>
</html>
