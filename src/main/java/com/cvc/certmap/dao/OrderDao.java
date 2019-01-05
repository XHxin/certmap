package com.cvc.certmap.dao;

import com.cvc.certmap.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 11:51
 * @todo
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    Order findByOrderNum(String outTradeNo);
}
