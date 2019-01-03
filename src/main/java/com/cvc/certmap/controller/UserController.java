package com.cvc.certmap.controller;

import com.cvc.certmap.dto.user.UserBinddingDto;
import com.cvc.certmap.dto.user.UserSignInDto;
import com.cvc.certmap.result.Result;
import com.cvc.certmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 14:26
 * @todo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public Result register(@RequestBody UserSignInDto signInDto) {
        return userService.register(signInDto);
    }

    @PostMapping("/login/wechat/{openId}")
    public Result loginByWechat(@PathVariable String openId) {
        return userService.loginByWechat(openId);
    }

    @PostMapping("/login/qq/{openId}")
    public Result loginByQQ(@PathVariable String openId) {
        return userService.loginByQQ(openId);
    }

    @PostMapping("/bindding")
    public Result bindAccount(@RequestBody UserBinddingDto userBinddingDto) {
        return userService.bindAccount(userBinddingDto);
    }
}
