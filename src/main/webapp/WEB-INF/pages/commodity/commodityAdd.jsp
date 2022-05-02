
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增商品</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
<h3>新增商品</h3>
<table style="width:900px; margin:0px auto;" class="table_border table_padding10 mainContainer">
    <tr class="tr_header">
        <td>项目</td>
        <td>内容&emsp;&emsp;&emsp;&emsp;</td>
    </tr>
    <tr>
        <td>商品名称</td>
        <td class="left">
            <input type="text" name="CommodityName" maxlength="45">&ensp;
            <span class="msg">*</span>
        </td>
    </tr>
    <tr>
        <td>商品类型</td>
        <td class="left">
            <input type="text" name="CommodityType" maxlength="45">&ensp;
            <span class="msg">*</span>
        </td>
    </tr>
    <tr>
        <td>商品介绍</td>
        <td class="left">
            <input type="text" name="commodityDesc">&ensp;
            <span class="msg">*</span>
        </td>
    </tr>
    <tr>
        <td>商品价钱</td>
        <td class="left">
            <input type="text" name="price">&ensp;
            <span class="msg">*</span>
        </td>
    </tr>
    <tr>
        <td>促销价格</td>
        <td class="left">
            <input type="text" name="discountPrice">
        </td>
    </tr>
    <tr>
        <td>库存</td>
        <td class="left">
            <input type="text" name="stock">&emsp;
            <span class="msg">*</span>
        </td>
    </tr>
    <tr>
        <td>是否上架</td>
        <td class="left">
            <label><input type="radio" name="status" value="0">下架</label>&ensp;
            <label><input type="radio" name="status" value="1">上架</label>
            <span class="msg">*默认不上架</span>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="提交" onclick="ajax();">
            &emsp;&emsp;
            <a href="commodityList">返回商品列表</a>
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    function ajax(){
        let param = {
            'CommodityName' : $('.mainContainer [name=CommodityName]').val(),
            'CommodityType' : $('.mainContainer [name=CommodityType]').val(),
            'commodityDesc' : $('.mainContainer [name=commodityDesc]').val(),
            'price':$('.mainContainer [name=price]').val(),
            'discountPrice': $('.mainContainer [name=discountPrice]').val(),
            'stock':$('.mainContainer [name=stock]').val(),
            'status':$('.mainContainer [name=status]:checked').val(),
        }

        console.log(param);

        if (param.CommodityName == null || param.CommodityName === ""){
            alert('商品名称不能为空');
            return;
        }

        if (param.CommodityType == null || param.CommodityType === ""){
            alert("商品类型不能为空");
            return;
        }

        if (param.commodityDesc == null || param.commodityDesc === ""){
            alert("商品介绍不能为空");
            return;
        }

        if (param.price == null || param.price === ""){
            alert("商品价格不能为空");
            return;
        }

        if (param.discountPrice == null || param.discountPrice === ""){
            param.discountPrice = null;
        }

        if (param.stock == null || param.stock === null){
            alert("商品库存不能为空")
            return;
        }

        if (param.status == null){
            param.status = 0;
        }

        let url = "/commodity/commodityAddDo";

        $.post(basePath+url,param,function(resp){
            if (resp.success){
                let targetUrl = basePath+"/commodity/commodityList";
                window.location.href = targetUrl;
                return;
            }
            alert(resp);
        });
    }
</script>
</html>
