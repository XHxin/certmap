package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_order_flow_platform")
public class OrderFlowPlatform {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "order_id")
  private Integer orderId;
  private BigDecimal money;
  private Integer operation;
  private Integer status;
  @Column(name = "cst_create")
  private Date cstCreate;
  @Column(name = "cst_modify")
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
