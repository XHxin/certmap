package com.cvc.certmap.dao;

import com.cvc.certmap.entity.WalletUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-14 11:15
 * @todo
 */
@Repository
public interface WalletUnitDao extends JpaRepository<WalletUnit,Integer> {
    WalletUnit findByIdAndStatus(Integer goodsId, int usable);
}
