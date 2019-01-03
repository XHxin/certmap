package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  @Column(name = "course_type")
  private Integer courseType;

  @Column(name = "father_id")
  private Integer fatherId;
  private String link;
  private BigDecimal price;
  private BigDecimal discountPrice;
  private Date cstCreate;
  private Date cstModify;
  private Integer status;

}
