package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_order_flow_expert")
public class OrderFlowExpert {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "order_id")
  private Integer orderId;
  private Integer operation;
  private BigDecimal money;
  private Integer status;
  @Column(name = "cst_create")
  private Date cstCreate;
  @Column(name = "cst_modify")
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
