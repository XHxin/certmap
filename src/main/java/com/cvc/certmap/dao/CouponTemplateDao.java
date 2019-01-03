package com.cvc.certmap.dao;

import com.cvc.certmap.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 14:17
 * @todo
 */
@Repository
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Integer> {
}
