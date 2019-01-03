package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_coupon_template")
public class CouponTemplate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private BigDecimal money;
  @Column(name = "begin_time")
  private Date beginTime;

  @Column(name = "dead_line")
  private Date deadLine;
  private String scope;

  @Column(name = "scope_num")
  private String scopeNum;
  private Integer overlying;

  @Column(name = "is_share")
  private Integer isShare;
  private Integer status;

  @Column(name = "cst_create")
  private Date cstCreate;

  @Column(name = "cst_modify")
  private Date cstModify;

}
