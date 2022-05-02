<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理列表</title>
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
<form action="" method="post"> <!-- 表单提交给对应的Servlet类UserList -->
    <div class="divGrid note right" style="margin-top:5px;">
        &emsp;&emsp;搜索：
        <input type="text" name="search" value="${ search }" style="width:80px;">
        <input type="submit" name="buttonQuery" value="搜索">
        <span class="note">（在商品名称、类型和编号、描述中搜索）</span>
    </div>

    <h3>商品管理列表</h3>

    <c:if test="${ commodityList.size() > 0 }">        <%-- 这里使用了jstl标签 --%>
        <div class="divGrid">
            <table style="width:1150px; margin:0px auto;" class="table_border table_border_bg table_hover">
                <tr class="tr_header">
                    <td>序号</td>
                    <td>商品名称</td>
                    <td>商品类型</td>
                    <td>商品编号</td>
                    <td>商品简介</td>
                    <td>原价</td>
                    <td>促销价</td>
                    <c:if test="${user.userRole == 1}">
                        <td>库存</td>
                        <td>是否上架</td>
                        <td>创建时间</td>
                        <td>更新时间</td>
                        <td style="width:95px;">详情/修改</td>
                        <td style="width:46px;">选择</td>
                    </c:if>
                    <c:if test="${user.userRole == 2}">
                        <td>详情</td>
                    </c:if>
                </tr>

                <c:forEach var="item" items="${ commodityList }" varStatus="status">
                    <tr>
                        <td class="note">${status.index + countShowed + 1 }</td>
                        <td>${ item.commodityName }</td>
                        <td>${ item.commodityType }</td>
                        <td>${ item.commodityNo }</td>
                        <td>${ item.commodityDesc }</td>
                        <td>${ item.price }</td>
                        <td>${ item.discountPrice }</td>
                        <c:if test="${user.userRole == 1}">
                            <td>${ item.stock }</td>
                            <td>${ item.status == 0 ? "未上架" : "已上架" }</td>
                            <td>${ item.gmtCreate }</td>
                            <td>${ item.gmtModify }</td>
                            <td>
                                <a href="${basePath}/commodity/commodityShow?commodityId=${ item.commodityId }"
                                   title="详情">
                                    <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce76.png" border="0"/></a>&emsp;
                                <a href="${basePath}/commodity/modifyCommodity?commodityId=${ item.commodityId }"
                                   title="修改">
                                    <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce73.png" border="0"/></a>
                            </td>
                            <td>
                                <input type="checkbox" name="commodityId" value="${ item.commodityId }">
                            </td>
                        </c:if>
                        <c:if test="${user.userRole == 2}">
                            <td>
                                <a href="${basePath}/commodity/commodityShow?commodityId=${ item.commodityId }"
                                   title="详情">
                                    <img src="https://pic.imgdb.cn/item/626d32e4239250f7c520ce76.png" border="0"/></a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${user.userRole == 1}">
                    <tr>
                        <td colspan="13" class="note" style="text-align:right; height:50px;">
                            （<a href="${basePath}/commodity/commodityAdd">新添商品</a>）
                            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                            <input type="submit" name="buttonDelete" value="删除"
                                   onclick="return confirm('确认删除所选记录？');">
                            &emsp;
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
</form>
<div id="msg" class="msg">${ msg }</div>
</body>
<script type="text/javascript">
    function checkAll() {
        var checkboxList = document.getElementsByName("commodityId"); //获取复选框列表，得到数组
        var checkboxAll = document.getElementsByName("checkboxAll")[0]; //全选复选框

        for (var i = 0; i < checkboxList.length; i++) { //对于列表中的每一个复选框
            checkboxList[i].checked = checkboxAll.checked; //此复选框的勾选情况与全选复选框一致
        }
    }
</script>
</html>
