package com.cvc.certmap.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cvc.certmap.constant.Constants;
import com.cvc.certmap.dao.*;
import com.cvc.certmap.entity.*;
import com.cvc.certmap.entity.Order;
import com.cvc.certmap.enums.GoodsTypeEnum;
import com.cvc.certmap.enums.PayTypeEnum;
import com.cvc.certmap.enums.ResultEnum;
import com.cvc.certmap.exception.ApiException;
import com.cvc.certmap.handler.WalletPayHandler;
import com.cvc.certmap.handler.WalletPayHandlerFactory;
import com.cvc.certmap.result.Result;
import com.cvc.certmap.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 13:29
 * @todo
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    CouponDao couponDao;
    @Autowired
    WalletUnitDao walletUnitDao;
    @Autowired
    OrderFlowExpertDao orderFlowExpertDao;
    @Autowired
    OrderFlowPlatformDao orderFlowPlatformDao;

    private final static Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Value("aliPay.appId")
    private String appId;
    @Value("aliPay.privateKey")
    private String privateKey;
    @Value("aliPay.publicKey")
    private String publicKey;
    @Value("aliPay.getaway")
    private String getaway;
    @Value("aliPay.charset")
    private String charset;
    @Value("aliPay.partner")
    private String partner;
    @Value("aliPay.addCoinNotifyUrl")
    private String addCoinNotifyUrl;

    @Override
    public Result addVipByApple(Integer userId, Double money) {
        return null;
    }

    @Override
    public Result addCoinByApple(Integer userId, Double money) {
        return null;
    }

    @Override
    public Result addVipByWechat(Integer userId, Double money) {
        return null;
    }

    @Override
    public Result addCoinByWechat(Integer userId, Double money) {
        return null;
    }

    @Override
    public Result addVipByAliPay(Integer userId, Double money) {
        return null;
    }

    @Override
    public Result addCoinByAliPay(Integer userId, BigDecimal money) {
        Order order = generateAddCoinOrder(userId, money);
        AlipayClient alipayClient = new DefaultAlipayClient(getaway, appId, privateKey, "json", charset, publicKey, "RSA2");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = generateAlipayTradeAppPayModel(order);
        request.setBizModel(model);
        request.setNotifyUrl(addCoinNotifyUrl);
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return new Result(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return new Result(ResultEnum.HANDLE_FAILURE);
    }

    @Override
    public void addCoinByAliPayNotify(HttpServletRequest request) {
        Order order = aliPayNotifyHandler(request);
        if(order != null) {
            addCoinHandler(order);
        }
    }

    /**
     * 1. 修改订单状态
     * 2. 给用户钱包添加钱
     * 3. 写平台流水
     * @param order
     */
    private void addCoinHandler(Order order) {
        updateStatusToSuccess(order);
        doAddCoin(order);
        generatePlatformOrderFlow(order);
    }

    private void doAddCoin(Order order) {
        Integer userId = order.getUserId();
        User user = userDao.findByIdAndStatus(userId, Constants.USABLE);
        if(user != null){
            WalletUnit unit = walletUnitDao.findByIdAndStatus(order.getGoodsId(), Constants.USABLE);
            if(unit != null) {
                user.setBalance(user.getBalance().add(unit.getPrice()));
                user.setCstModify(new Date());
                userDao.saveAndFlush(user);
            }
            log.error("充值的钱包套餐找不到，套餐编号为：" + order.getGoodsId());
        }
        log.error("订单找不到对应的购买者，订单编号为：" + order.getOrderNum());
    }

    private void updateStatusToSuccess(Order order) {
        order.setCstModify(new Date());
        order.setPayStatus(Constants.PAY_SUCCESS);
        orderDao.saveAndFlush(order);
    }

    private Order aliPayNotifyHandler(HttpServletRequest request) {
        Order order = null;
        Map<String, String> paramsMap = getAliPayNotifyParams(request);
        boolean flag = checkAliPaySignature(paramsMap);
        if (flag == true) {
            String code = paramsMap.get("code");
            if(Constants.ALIPAY_SUCCESS_CODE.equals(code)) {
                String outTradeNo = paramsMap.get("out_trade_no");
                String totalFee = paramsMap.get("buyer_pay_amount"); // 交易金额
                String sellerId = paramsMap.get("seller_id");
                order = orderDao.findByOrderNum(outTradeNo);
                if(order == null) {
                    throw new ApiException(ResultEnum.ORDER_MISSING);
                }
                if(Constants.PAY_SUCCESS == order.getPayStatus()){
                    throw new ApiException(ResultEnum.DO_NOT_REPETITION);
                }
                if(order.getActualPrice().compareTo(new BigDecimal(totalFee)) > 0 || order.getActualPrice().compareTo(new BigDecimal(totalFee)) < 0) {
                    throw new ApiException(ResultEnum.BUYER_PAY_AMOUNT_NOT_EQUAL);
                }
                if(!partner.equals(sellerId)) {
                    throw new ApiException(ResultEnum.SELLER_ID_NOT_EQUAL);
                }

                return order;
            }
            log.error("支付宝支付失败：" + paramsMap.get("sub_msg"));
        }
        log.error("支付宝支付回调验签不正确");
        return order;
    }

    private boolean checkAliPaySignature(Map<String, String> paramsMap) {
        boolean flag = false;
        try {
            flag = AlipaySignature.rsaCheckV1(paramsMap, publicKey, charset, "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private Map<String, String> getAliPayNotifyParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    private AlipayTradeAppPayModel generateAlipayTradeAppPayModel(Order order) {
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(order.getOrderNum());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(order.getActualPrice().toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        return model;
    }

    private Order generateAddCoinOrder(Integer userId, BigDecimal money) {
        Order order = new Order(Constants.PAY_FAILED, userId, GoodsTypeEnum.WALLET.getType(), 0, PayTypeEnum.ALIPAY.getKey(), money, new BigDecimal("0"), money, Constants.USABLE);
        return orderDao.save(order);
    }

    @Override
    public Result walletPay(Integer userId, Integer goodsId, Integer goodsType, Integer[] couponIds) {
        User user = userDao.findByIdAndStatus(userId, Constants.USABLE);
        if (user != null) {
            BigDecimal couponMoney = calculateCouponMoney(couponIds, userId);

            //校验商品类型是否正确
            GoodsTypeEnum goodsTypeEnum = GoodsTypeEnum.getEnumByType(goodsType);
            WalletPayHandler handler = WalletPayHandlerFactory.getHandler(goodsTypeEnum.getType());
            Order order = handler.buy(userId, goodsId, couponMoney);
            generateOrderFlow(order);
            return new Result();
        }
        return new Result(ResultEnum.FAILURE);
    }

    public BigDecimal calculateCouponMoney(Integer[] couponIds, Integer userId) {
        BigDecimal couponMoney = new BigDecimal("0");
        List<Coupon> coupons = getAvailableCoupons(couponIds, userId);
        if (!coupons.isEmpty()) {
            for (Coupon coupon : coupons) {
                couponMoney = couponMoney.add(coupon.getMoney());
            }
        }
        return couponMoney;
    }

    private List<Coupon> getAvailableCoupons(Integer[] couponIds, Integer userId) {
        Specification<Coupon> specification = new Specification<Coupon>() {
            @Override
            public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> id = root.get("id");
                Path<Integer> used = root.get("used");
                Path<Integer> user = root.get("userId");
                Path<Integer> status = root.get("status");
                Path<Date> beginTime = root.get("beginTime");
                Path<Date> deadLine = root.get("deadLine");
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(status, Constants.USABLE));
                predicates.add(criteriaBuilder.equal(used, Coupon.UNUSED));
                CriteriaBuilder.In<Integer> in = criteriaBuilder.in(id);
                for (int i = 0; i <= couponIds.length; i++) {
                    in.value(couponIds[i]);
                }
                predicates.add(criteriaBuilder.and(in));
                predicates.add(criteriaBuilder.equal(user, userId));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(beginTime, new Date()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(deadLine, new Date()));
                Predicate[] p = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(p));
            }
        };
        return couponDao.findAll(specification);
    }

    private void generateOrderFlow(Order order) {
        generateExpertOrderFlow(order);
        generatePlatformOrderFlow(order);
    }

    private void generatePlatformOrderFlow(Order order) {
        OrderFlowExpert orderFlowExpert = new OrderFlowExpert(order.getUserId(), order.getId(), Constants.OPERATION_INCOME, order.getActualPrice(), Constants.USABLE);
        orderFlowExpertDao.save(orderFlowExpert);
    }

    private void generateExpertOrderFlow(Order order) {
        OrderFlowPlatform orderFlowPlatform = new OrderFlowPlatform(order.getId(), order.getActualPrice(), Constants.OPERATION_INCOME, Constants.USABLE);
        orderFlowPlatformDao.save(orderFlowPlatform);
    }
}
