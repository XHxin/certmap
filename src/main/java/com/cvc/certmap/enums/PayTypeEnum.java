package com.cvc.certmap.enums;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 16:46
 * @todo
 */
public enum PayTypeEnum {
    ALIPAY(2,"支付宝"),
    WALLET(4,"钱包支付");

    private int key;
    private String value;

    PayTypeEnum(int key, String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
