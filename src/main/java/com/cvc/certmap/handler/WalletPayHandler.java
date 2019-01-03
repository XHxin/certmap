package com.cvc.certmap.handler;

import com.cvc.certmap.entity.Order;
import com.cvc.certmap.enums.GoodsTypeEnum;
import com.cvc.certmap.result.Result;

import java.math.BigDecimal;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 18:27
 * @todo
 */
public interface WalletPayHandler {

    GoodsTypeEnum getGoodsType();

    Order buy(Integer userId, Integer goodsId, BigDecimal couponMoney);
}
