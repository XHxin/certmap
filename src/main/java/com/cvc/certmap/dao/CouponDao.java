package com.cvc.certmap.dao;

import com.cvc.certmap.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 14:32
 * @todo
 */
@Repository
public interface CouponDao extends JpaRepository<Coupon, Integer>, JpaSpecificationExecutor<Coupon>{
}
