package com.cvc.certmap.dao;

import com.cvc.certmap.entity.OrderFlowPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-24 14:26
 * @todo
 */
@Repository
public interface OrderFlowPlatformDao extends JpaRepository<OrderFlowPlatform, Integer>{
}
