package com.cvc.certmap.constant;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 16:06
 * @todo
 */
public class Constants {

    /**系统默认角色1：用户  2：专家*/
    public static final int DEFAULT_ROLE = 1;

    /**系统统一“可用”标志*/
    public static final int USABLE = 1;
    /**系统统一“不可用”标志*/
    public static final int UNUSABLE = 0;

    public static final int PAY_FAILED = 0;
    public static final int PAY_SUCCESS = 1;
    public static final int PAY_REFUND = 2;

    /**收入*/
    public static final int OPERATION_INCOME = 0;
    /**支出*/
    public static final int OPERATION_SPENDING = 1;

    /**绑定类型*/
    public static final Integer BINDDING_TYPE_WECHAT = 1;
    public static final Integer BINDDING_TYPE_QQ = 0;

    /**支付宝支付回调参数，10000代表支付成功*/
    public static final String ALIPAY_SUCCESS_CODE = "10000";
}
