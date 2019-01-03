package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_standard")
public class Standard {

  private Integer id;
  private String title;
  private Integer userId;
  private BigDecimal price;
  private BigDecimal discountPrice;
  private String thumbnail;
  private Integer type;
  private String intro;
  private String standardNumber;
  private String documentName;
  private String documentId;
  private Integer status;
  private Date cstCreate;
  private Date cstModify;


}
