package com.cvc.certmap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 15:12
 * @todo
 */
public class OrderUtil {

    private static long orderNum = 0L;
    private static long payNum = 0L;
    private static String date;

    public static synchronized String generateOrderNum(int prefix) {
        String str = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        if (date == null || !str.equals(date)) {//不是同一毫米初始化
            date = str;
            orderNum = 0L; // 同一毫秒生成多少个订单
        }
        orderNum++;
        long orderSn = Long.parseLong((date));
        orderSn += orderNum;
        return "" + prefix + orderSn;
    }
}
