package com.cvc.certmap.dao;

import com.cvc.certmap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 14:29
 * @todo
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByIdAndStatus(Integer userId, int usable);

    User findByUnionIdAndStatus(String unionId, int usable);

    User findByQqOpenIdAndStatus(String openId, int usable);

    User findByMobileAndStatus(@Pattern(regexp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$", flags = Pattern.Flag.COMMENTS) String mobile, int usable);
}
