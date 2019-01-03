package com.cvc.certmap.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderFlowExpert {

  private Integer id;
  private Integer userId;
  private Integer orderId;
  private Integer operation;
  private BigDecimal money;
  private Integer status;
  private Date cstCreate;
  private Date cstModify;


  public OrderFlowExpert(Integer userId, Integer orderId, int operation, BigDecimal actualPrice, int status) {
    this.userId = userId;
    this.orderId = orderId;
    this.operation = operation;
    this.money = actualPrice;
    this.status = status;
    this.cstCreate = new Date();
    this.cstModify = new Date();
  }
}
