package com.cvc.certmap.service;

import com.cvc.certmap.result.Result;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 13:29
 * @todo
 */
public interface PayService {
    
    Result addVipByApple(Integer userId, Double money);

    Result addCoinByApple(Integer userId, Double money);

    Result addVipByWechat(Integer userId, Double money);

    Result addCoinByWechat(Integer userId, Double money);

    Result addVipByAliPay(Integer userId, Double money);

    Result addCoinByAliPay(Integer userId, BigDecimal money);

    Result walletPay(Integer userId, Integer goodsId, Integer goodsType, Integer[] couponIds);

    void addCoinByAliPayNotify(HttpServletRequest request);
}
