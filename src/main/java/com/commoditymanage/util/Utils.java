package com.commoditymanage.util;

import java.util.Date;
import java.util.Random;

public class Utils{
    /**
     * 商品编号生成器
     * @return commodityNo 商品编号
     */
    public static String generateCommodityNo(){
        String commodityNo = "CN";
        Date now = new Date();
        Random random = new Random();
        long num = (now.getTime())/8+random.nextInt(100);
        commodityNo += num;
        return commodityNo;
    }

    /**
     * 生成购物车编码
     * @return commodityCarNo 购物车编码
     */
    public static String generateCommodityCarNo(){
        String commodityCarNo = "CCN";
        Date now = new Date();
        Random random = new Random();
        long num = (now.getTime())/12+random.nextInt(10);
        commodityCarNo += num;
        return commodityCarNo;
    }

    /**
     * 订单编号生成器
     * @return orderNo 订单编号
     */
    public static String generatePayOrderNo(){
        String orderNo = "PON";
        Date now = new Date();
        Random random = new Random();
        long num = (now.getTime())+random.nextInt(10000);
        orderNo += num;
        return orderNo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateCommodityCarNo());
        }
    }
}
