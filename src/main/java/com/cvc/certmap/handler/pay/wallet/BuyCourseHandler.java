package com.cvc.certmap.handler.pay.wallet;

import com.cvc.certmap.constant.Constants;
import com.cvc.certmap.dao.CourseDao;
import com.cvc.certmap.dao.OrderDao;
import com.cvc.certmap.entity.Course;
import com.cvc.certmap.entity.Order;
import com.cvc.certmap.entity.Standard;
import com.cvc.certmap.enums.GoodsTypeEnum;
import com.cvc.certmap.enums.PayTypeEnum;
import com.cvc.certmap.enums.ResultEnum;
import com.cvc.certmap.exception.ApiException;
import com.cvc.certmap.handler.WalletPayHandler;
import com.cvc.certmap.result.Result;
import com.cvc.certmap.util.OrderUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @auther xiehuaxin
 * @create 2018-12-18 11:08
 * @todo
 */
public class BuyCourseHandler implements WalletPayHandler {

    @Autowired
    CourseDao courseDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public GoodsTypeEnum getGoodsType() {
        return null;
    }

    @Override
    public Order buy(Integer userId, Integer goodsId, BigDecimal couponMoney) {
        Course course = courseDao.findByIdAndStatus(goodsId, Constants.USABLE);
        if(course != null) {
            BigDecimal actualPrice = calculateActualPrice(course, couponMoney);
            Order order = new Order(Constants.PAY_SUCCESS, userId, GoodsTypeEnum.COURSE.getType(), goodsId, PayTypeEnum.WALLET.getKey(),
                    course.getPrice(), couponMoney, actualPrice, Constants.USABLE);
            return orderDao.save(order);
        }
       throw new ApiException(ResultEnum.FAILURE);
    }

    private BigDecimal calculateActualPrice(Course course, BigDecimal couponMoney) {
        BigDecimal goodsPrice = course.getDiscountPrice() == null ? course.getPrice() : course.getDiscountPrice();
        return goodsPrice.subtract(couponMoney);
    }
}
