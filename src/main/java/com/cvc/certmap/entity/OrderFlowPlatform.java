package com.cvc.certmap.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderFlowPlatform {

  private Integer id;
  private Integer orderId;
  private BigDecimal money;
  private Integer operation;
  private Integer status;
  private Date cstCreate;
  private Date cstModify;

  public OrderFlowPlatform(Integer orderId, BigDecimal actualPrice, int operation, int status) {
    this.orderId = orderId;
    this.money = actualPrice;
    this.operation = operation;
    this.status = status;
    this.cstCreate = new Date();
    this.cstModify = new Date();
  }
}
