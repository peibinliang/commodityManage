<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车列表</title>
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
        <span class="note">（在商品名称中搜索）</span>
    </div>

    <h3>购物车列表</h3>

    <c:if test="${ carList.size() > 0 }">		<%-- 这里使用了jstl标签 --%>
        <div class="divGrid">
            <table style="width:900px; margin:0px auto;" class="table_border table_border_bg table_hover">
                <tr class="tr_header">
                    <td>序号</td>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>总价</td>
                    <td style="width:95px;">详情</td>
                    <td style="width:46px;">选择</td>
                </tr>

                <c:forEach var="item" items="${ carList }" varStatus="status">
                    <tr>
                        <td class="note">${status.index + countShowed + 1 }</td>
                        <td>${ item.commodityName }</td>
                        <td>${ item.num }</td>
                        <td>${ item.unitPrice }</td>
                        <td>${ item.totalPrice }</td>
                        <td>
                            <a href="${basePath}/commodity/commodityShow?commodityId=${ item.commodityId }" title="详情">
                                <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce76.png" border="0" /></a>&emsp;
                        </td>
                        <td>
                            <input type="checkbox" name="carId" value="${ item.carId }">
                        </td>
                    </tr>
                </c:forEach>

                <tr>
                    <td colspan="9" class="note" style="text-align:right; height:50px;">
                        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                        <input type="submit" name="buttonDelete" value="移除购物车"
                               onclick="return confirm('确认删除所选记录？');">&emsp;
                        <input type="submit" name="buttonPay" value="付款">
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
    <div id="msg" class="msg">${ msg }</div>
    <div id="msg" class="msg">
        <a href="${basePath}/commodity/commodityList">去逛一下？</a>
    </div>
</form>
</body>
<script type="text/javascript">
    function checkAll() {
        var checkboxList = document.getElementsByName("carId");			//获取复选框列表，得到数组
        var checkboxAll  = document.getElementsByName("checkboxAll")[0];	//全选复选框

        for (var i = 0; i < checkboxList.length; i++) {						//对于列表中的每一个复选框
            checkboxList[i].checked = checkboxAll.checked;					//此复选框的勾选情况与全选复选框一致
        }
    }
</script>
</html>
