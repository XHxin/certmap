package com.cvc.certmap.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 11:10
 * @todo
 */
@Data
@Entity
@Table(name = "cm_wallet_unit")
public class WalletUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal price;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    private Integer status;

    @Column(name = "cst_create")
    private Date cstCreate;

    @Column(name = "cst_modify")
    private Date cstModify;


}
