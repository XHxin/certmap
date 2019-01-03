package com.cvc.certmap.service.impl;

import com.cvc.certmap.constant.Constants;
import com.cvc.certmap.dao.CouponDao;
import com.cvc.certmap.entity.Coupon;
import com.cvc.certmap.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 17:57
 * @todo
 */
@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponDao couponDao;


}
