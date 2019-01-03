package com.cvc.certmap.dao;

import com.cvc.certmap.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-21 11:09
 * @todo
 */
@Repository
public interface StandardDao extends JpaRepository<Standard, Integer> {
    Standard findByIdAndStatus(Integer goodsId, int usable);
}
