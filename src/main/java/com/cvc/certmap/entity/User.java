package com.cvc.certmap.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mobile;
    @Column(name = "real_name")
    private String realName;
    private Integer role;
    private String email;
    private String unionId;
    private Integer sex;
    private String headImg;
    private String accessToken;
    private String qqOpenId;
    private Integer status;
    @Column(name = "cst_modify")
    private Date cstModify;

    @Column(name = "cst_create")
    private Date cstCreate;


    public User(String email, String mobile, String realName, Integer role, Integer status) {
        this.mobile = mobile;
        this.realName = realName;
        this.cstCreate = new Date();
        this.cstModify = new Date();
        this.role = role;
        this.email = email;
        this.status = status;
    }

}

