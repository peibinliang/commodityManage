<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
    <%@include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/styles/table.css">
</head>
<body>
<h3>商品详情</h3>
<c:if test="${ commodityInfo != null }">
    <table style="width:900px; margin:0px auto;" class="table_border table_padding10">
        <tr class="tr_header">
            <td>项目</td>
            <td>内容&emsp;&emsp;&emsp;&emsp;</td>
        </tr>
        <tr>
            <td>商品名称</td>
            <td class="left">${ commodityInfo.commodityName }</td>
        </tr>
        <tr>
            <td>商品类型</td>
            <td class="left">${ commodityInfo.commodityType }</td>
        </tr>
        <tr>
            <td>商品编号</td>
            <td class="left">${ commodityInfo.commodityNo }</td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td class="left">${ commodityInfo.commodityDesc }</td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td class="left">${ commodityInfo.price }</td>
        </tr>
        <tr>
            <td>促销价</td>
            <td class="left">${ commodityInfo.discountPrice }</td>
        </tr>
        <c:if test="${user.userRole == 1}">
        <tr>
            <td>库存</td>
            <td class="left">${ commodityInfo.stock }</td>
        </tr>
        <tr>
            <td>是否上架</td>
            <td class="left">
                <c:if test="${ commodityInfo.status == 0 }">
                    未上架
                </c:if>
                <c:if test="${ commodityInfo.status == 1 }">
                    已上架
                </c:if>
            </td>
        </tr>
        <tr>
            <td>注册时间</td>
            <td class="left">${commodityInfo.gmtCreate}</td>
        </tr>
        <tr>
            <td>更新时间</td>
            <td class="left">${ commodityInfo.gmtModify }</td>
        </tr>
        </c:if>
        <c:if test="${user.userRole == 2}">
            <tr>
                <td>数量</td>
                <td class="left">
                    <input type="button" style="width: 20px;height: 20px;line-height: 15;" value="-" onclick="sub();">&emsp;
                    <input type="text" id = "number" value="1" style="width: 40px;text-align: center;" onblur="check()">&emsp;
                    <input type="button" style="width: 20px;height: 20px;line-height: 15;" value="+" onclick="add();">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="添加到购物车" onclick="addShopCar()"
                            <c:if test="${ commodityInfo.stock == 0 }">disabled='disabled'</c:if>
                           <c:if test="${ commodityInfo.status == 0 }">disabled='disabled'</c:if>
                    >&emsp;&emsp;
                    <input type="button" value="直接购买" onclick="buy()"
                           <c:if test="${ commodityInfo.stock == 0 }">disabled='disabled'</c:if>
                           <c:if test="${ commodityInfo.status == 0 }">disabled='disabled'</c:if>
                    >
                </td>
            </tr>
        </c:if>
        <c:if test="${user.userRole == 1}">
        <tr>
            <td colspan="2">
                <a href="modifyCommodity?commodityId=${ commodityInfo.commodityId }">修改</a>&emsp;
                    <a href="removeCommodity?commodityId=${ commodityInfo.commodityId }"
                       onclick="return confirm('确定要删除吗？');">删除</a>
            </td>
        </tr>
        </c:if>
    </table>
    <a href="${basePath}/commodity/commodityList">返回商品列表</a>
</c:if>
</body>
<script type="text/javascript">
    function sub() {
        let num = $('#number').val()
        if (num <= 1){
            alert("已经是最小了")
        }else {
            $('#number').val(parseInt(num)-1)
        }
    }

    function add(){
        let num = $('#number').val()
        if (num > ${commodityInfo.stock}){
            alert("库存不足")
        }else {
            $('#number').val(parseInt(num)+1)
        }
    }

    function check(){
        let num = $('#number').val()
        if (num > ${ commodityInfo.stock }){
            alert("库存不足")
            $('#number').val(1)
        }
    }

    function addShopCar(){
        let param = {
            'userId' : ${ user.userId },
            'commodityId' : ${ commodityInfo.commodityId },
            'num' : $('#number').val(),
            'unitPrice' : ${ commodityInfo.discountPrice == null ? commodityInfo.price : commodityInfo.discountPrice },
            'totalPrice' : 0
        }

        param.totalPrice = (param.num * param.unitPrice).toFixed(2);

        $.post(basePath+"/commodityCar/saveCommodityCar",param,function (resp){
            if (resp.success){
                alert("成功添加到购物车")
                window.location.reload();
                return
            }
            alert("添加到购物车失败！")
        })

    }

    function buy(){
        let param = {
            'userId' : ${ user.userId },
            'commodityId' : ${ commodityInfo.commodityId },
            'num' : $('#number').val(),
            'unitPrice' : ${ commodityInfo.discountPrice == null ? commodityInfo.price : commodityInfo.discountPrice },
            'totalPrice' : 0
        }

        param.totalPrice = (param.num * param.unitPrice).toFixed(2);

        $.post(basePath+"/payOrder/savePayOrderOfShop",param,function (resp){
            if (resp.success){
                alert("购买成功,可到我的订单查看详情");
                window.location.reload();
                return;
            }
            alert("购买失败！"+JSON.parse(resp).message)
        })

    }
</script>
</html>
