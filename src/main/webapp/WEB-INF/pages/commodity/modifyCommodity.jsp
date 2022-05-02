<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品信息编辑</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
<h3>商品信息编辑</h3>
<c:if test="${ commodityInfo != null }">
    <table style="width:900px; margin:0px auto;" class="table_border table_padding10 mainContainer">
        <tr class="tr_header">
            <td>项目</td>
            <td>内容&emsp;&emsp;&emsp;&emsp;</td>
        </tr>
        <tr>
            <td>商品名称</td>
            <td class="left">
                <input type="text" name="commodityName" value="${ commodityInfo.commodityName}" maxlength="45">&ensp;
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>商品类型</td>
            <td class="left">
                <input type="text" name="commodityType" value="${ commodityInfo.commodityType }" maxlength="45">&ensp;
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>商品编号</td>
            <td class="left">
                    ${ commodityInfo.commodityNo }
            </td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td class="left">
                <input type="text" name="commodityDesc" value="${ commodityInfo.commodityDesc }">
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td class="left">
                <input type="text" name="price" value="${ commodityInfo.price }">
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>促销价</td>
            <td class="left">
                <input type="text" name="discountPrice" value="${ commodityInfo.discountPrice }">
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>库存</td>
            <td class="left">
                <input type="text" name="stock" value="${ commodityInfo.stock }">
                <span class="msg">*留空则不修改</span>
            </td>
        </tr>
        <tr>
            <td>是否上架</td>
            <td class="left">
                <label><input type="radio" name="status" value="0"
                              <c:if test="${ commodityInfo.status == 0 }">checked='checked'</c:if>
                >下架</label>&ensp;
                <label><input type="radio" name="status" value="1"
                              <c:if test="${ commodityInfo.status == 1 }">checked='checked'</c:if>
                >上架</label>&ensp;
            </td>
        </tr>
        <tr>
            <td>创建时间</td>
            <td class="left">${commodityInfo.gmtCreate}</td>
        </tr>
        <tr>
            <td>更新时间</td>
            <td class="left">${ commodityInfo.gmtModify }</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交" onclick="ajax();">
                &emsp;&emsp;
                <a href="${basePath}/commodity/commodityShow?commodityId=${ commodityInfo.commodityId }">返回商品详情页</a>
            </td>
        </tr>
    </table>
</c:if>
<br>
    <a href="${basePath}/commodity/commodityList">返回商品列表</a>
</body>
<script type="text/javascript">
    function ajax() {
        var param = {
            'commodityId':${ commodityInfo.commodityId },
            'commodityName': $('.mainContainer [name=commodityName]').val(),
            'commodityType': $('.mainContainer [name=commodityType]').val(),
            'commodityDesc': $('.mainContainer [name=commodityDesc]').val(),
            'price':$('.mainContainer [name=price]').val(),
            'discountPrice': $('.mainContainer [name=discountPrice]').val(),
            'stock':$('.mainContainer [name=stock]').val(),
            'status': $('.mainContainer [name=status]:checked').val()
        }

        if (param.commodityName == null || param.commodityName === "") {
            param.commodityName = null
        }

        if (param.commodityType == null || param.commodityType === "") {
            param.commodityType = null
        }

        if (param.commodityDesc == null || param.commodityDesc === ""){
            param.commodityDesc = null
        }

        if (param.price == null || param.price === ""){
            param.price = null
        }
        if (param.discountPrice == null || param.discountPrice === ""){
            param.discountPrice = null
        }

        if (param.stock == null || param.stock === ""){
            param.stock = null
        }

        if (param.status == null){
            param.commodityDesc = null
        }

        var url = "/commodity/modifyCommodityDo";

        $.post(basePath + url, param, function (resp) {
            if (resp.success) {
                var targetUrl = basePath + "/commodity/commodityShow?commodityId=" +${commodityInfo.commodityId};
                window.location.href = targetUrl;
                return;
            }
            alert("修改失败");
        });
    }
</script>
</html>
