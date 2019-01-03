package com.cvc.certmap.controller;

import com.cvc.certmap.result.Result;
import com.cvc.certmap.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 11:52
 * @todo
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    PayService payService;

    @PutMapping("/aliPay/coin")
    public Result addCoinByAliPay(@RequestParam Integer userId,
                                  @RequestParam String token,
                                  @RequestParam BigDecimal money) {
        return payService.addCoinByAliPay(userId, money);
    }

    public void addCoinByAliPayNotify(@RequestParam HttpServletRequest request) {
        payService.addCoinByAliPayNotify(request);
    }

    @PutMapping("/aliPay/vip")
    public Result addVipByAliPay(@RequestParam Integer userId,
                                 @RequestParam String token,
                                 @RequestParam Double money) {
        return payService.addVipByAliPay(userId, money);
    }

    @PutMapping("/wechat/coin")
    public Result addCoinByWechat(@RequestParam Integer userId,
                                  @RequestParam String token,
                                  @RequestParam Double money) {
        return payService.addCoinByWechat(userId, money);
    }

    @PutMapping("/wechat/vip")
    public Result addVipByWechat(@RequestParam Integer userId,
                                 @RequestParam String token,
                                 @RequestParam Double money) {
        return payService.addVipByWechat(userId, money);
    }

    @PutMapping("/apple/coint")
    public Result addCoinByApple(@RequestParam Integer userId,
                                 @RequestParam String token,
                                 @RequestParam Double money) {
        return payService.addCoinByApple(userId, money);
    }

    @PutMapping("/apple/vip")
    public Result addVipByApple(@RequestParam Integer userId,
                                @RequestParam String token,
                                @RequestParam Double money) {
        return payService.addVipByApple(userId, money);
    }

    @PostMapping("/wallet")
    public Result payByWallet(@RequestParam Integer userId,
                              @RequestParam String token,
                              @RequestParam Integer goodsType,
                              @RequestParam Integer goodsId,
                              @RequestParam Integer[] couponIds) {
        return payService.walletPay(userId, goodsId, goodsType, couponIds);
    }

    @PostMapping("/distribution")
    public Result distribute() {
        //TODO
        return null;
    }

    @PostMapping("/integral")
    public Result integralPay() {
        //TODO
        return null;
    }

    @PostMapping(name = "/special_price")
    public Result specialPriceBuy() {
        //TODO
        return null;
    }

}
