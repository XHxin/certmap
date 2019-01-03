package com.cvc.certmap.handler.pay.wallet;

import com.cvc.certmap.constant.Constants;
import com.cvc.certmap.dao.*;
import com.cvc.certmap.entity.Order;
import com.cvc.certmap.entity.Standard;
import com.cvc.certmap.enums.GoodsTypeEnum;
import com.cvc.certmap.enums.PayTypeEnum;
import com.cvc.certmap.enums.ResultEnum;
import com.cvc.certmap.exception.ApiException;
import com.cvc.certmap.handler.WalletPayHandler;
import com.cvc.certmap.handler.WalletPayHandlerEvent;
import com.cvc.certmap.result.Result;
import com.cvc.certmap.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 18:35
 * @todo
 */
public class BuyStandardHandler implements WalletPayHandler {

    @Autowired
    WalletUnitDao walletUnitDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    StandardDao standardDao;
    @Autowired
    CouponDao couponDao;
    @Autowired
    CouponTemplateDao couponTypeDao;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 笔记：
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     * 被@PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。被@PreDestroy修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
     */
    @PostConstruct
    public void init() {
        WalletPayHandlerEvent event = new WalletPayHandlerEvent(this);
        applicationContext.publishEvent(event);

    }

    @Override
    public GoodsTypeEnum getGoodsType() {
        return GoodsTypeEnum.STANDARD;
    }

    @Override
    public Order buy(Integer userId, Integer goodsId, BigDecimal couponMoney) {
        Standard standard = standardDao.findByIdAndStatus(goodsId, Constants.USABLE);
        if (standard != null) {
            BigDecimal actualPrice = calculateActualPrice(standard, couponMoney);
            Order order = new Order(Constants.PAY_SUCCESS, userId, GoodsTypeEnum.STANDARD.getType(), goodsId, PayTypeEnum.WALLET.getKey(),
                    standard.getPrice(), couponMoney, actualPrice, Constants.USABLE);
            return orderDao.save(order);
        }
        throw new ApiException(ResultEnum.FAILURE);
    }

    private BigDecimal calculateActualPrice(Standard standard, BigDecimal couponMoney) {
        BigDecimal goodsPrice = standard.getDiscountPrice() == null ? standard.getPrice() : standard.getDiscountPrice();
        return goodsPrice.subtract(couponMoney);
    }
}
