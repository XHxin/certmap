package com.cvc.certmap.service;

import com.cvc.certmap.dto.user.UserBinddingDto;
import com.cvc.certmap.dto.user.UserSignInDto;
import com.cvc.certmap.result.Result;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 14:27
 * @todo
 */
public interface UserService {
    Result register(UserSignInDto signInDto);

    Result loginByWechat(String openId);

    Result loginByQQ(String openId);

    Result bindAccount(UserBinddingDto userBinddingDto);
}
