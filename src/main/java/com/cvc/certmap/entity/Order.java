package com.cvc.certmap.entity;


import com.cvc.certmap.util.OrderUtil;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_order")
public class Order {

  /*public static final int DEFAULT = -1;
  public static final int VIP = 0;
  public static final int REPORT = 1;
  public static final int STANDARD = 2;
  public static final int CONSULT = 4;
  public static final int NEWS = 5;
  public static final int E_BUSINESS = 6;
  public static final int WALLET = 7;
  public static final int COURSE = 8;
  public static final int COLUMN = 9;
  public static final int REWARD = 10;*/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "order_num")
  private String orderNum;

  @Column(name = "pay_status")
  private Integer payStatus;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "goods_type")
  private Integer goodsType;

  @Column(name = "goods_id")
  private Integer goodsId;

  @Column(name = "pay_type")
  private Integer payType;

  @Column(name = "original_price")
  private BigDecimal originalPrice;

  @Column(name = "coupon_price")
  private BigDecimal couponPrice;

  @Column(name = "actual_price")
  private BigDecimal actualPrice;
  private Integer status;

  @Column(name = "cst_create")
  private Date cstCreate;

  @Column(name = "cst_modify")
  private Date cstModify;

  public Order() {
  }

  public Order(int payStatus, Integer userId, int goodsType, Integer goodsId, int payType, BigDecimal price, BigDecimal couponMoney, BigDecimal actualPrice, int status) {
    this.orderNum = OrderUtil.generateOrderNum(goodsType);
    this.payStatus = payStatus;
    this.userId = userId;
    this.goodsType = goodsType;
    this.goodsId = goodsId;
    this.payType = payType;
    this.originalPrice = price;
    this.couponPrice = couponMoney;
    this.actualPrice = actualPrice;
    this.status = status;
    this.cstCreate = new Date();
    this.cstModify = new Date();
  }
}
