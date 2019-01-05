package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_standard")
public class Standard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  @Column(name = "user_id")
  private Integer userId;
  private BigDecimal price;
  @Column(name = "discount_price")
  private BigDecimal discountPrice;
  private String thumbnail;
  private Integer type;
  private String intro;
  @Column(name = "standard_number")
  private String standardNumber;
  @Column(name = "document_name")
  private String documentName;
  @Column(name = "document_id")
  private String documentId;
  private Integer status;
  @Column(name = "cst_create")
  private Date cstCreate;
  @Column(name = "cst_modify")
  private Date cstModify;


}
