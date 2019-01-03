package com.cvc.certmap.enums;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 16:30
 * @todo
 */
public enum  GoodsTypeEnum {
    VIP(0,"购买会员"),
    STANDARD(2,"标准解读"),
    WALLET(7,"钱包充值"),
    COURSE(8,"购买视频课程");

    private int type;

    private String name;

    GoodsTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static GoodsTypeEnum getEnumByType(int type) {
        for (GoodsTypeEnum goodsTypeEnum : GoodsTypeEnum.values()) {
            if(goodsTypeEnum.getType() == type) {
                return goodsTypeEnum;
            }
        }
        throw new RuntimeException("没有该类型商品");
    }
    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
