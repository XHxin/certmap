package com.cvc.certmap.service.impl;

import com.cvc.certmap.constant.Constants;
import com.cvc.certmap.dao.UserDao;
import com.cvc.certmap.dto.user.UserBinddingDto;
import com.cvc.certmap.dto.user.UserSignInDto;
import com.cvc.certmap.entity.User;
import com.cvc.certmap.enums.ResultEnum;
import com.cvc.certmap.enums.RoleEnum;
import com.cvc.certmap.result.Result;
import com.cvc.certmap.service.UserService;
import com.cvc.certmap.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 14:28
 * @todo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Result register(UserSignInDto signInDto) {
        if(signInDto.getRole() == RoleEnum.EXPERT.getRole() || signInDto.getRole() == RoleEnum.USER.getRole()){
            int status = getUserStatusByRole(signInDto.getRole());
            User user = new User(signInDto.getEmail(), signInDto.getMobile(), signInDto.getRealName(), signInDto.getRole(), status);
            userDao.save(user);
            return new Result(ResultEnum.SUCCESS);
        }
        return new Result(ResultEnum.WRONG_REQUEST_PARAMETER);
    }

    /**
     * 系统设计是注册专家账号需要审核才能使用
     * @param role 系统角色
     * @return status
     */
    private int getUserStatusByRole(Integer role) {
        int status = Constants.USABLE;
        if(role == RoleEnum.EXPERT.getRole()) {
            status = Constants.UNUSABLE;
        }
        return status;
    }

    @Override
    public Result loginByWechat(String unionId) {
        User user = userDao.findByUnionIdAndStatus(unionId, Constants.USABLE);
        return loginThird(user);
    }

    @Override
    public Result loginByQQ(String openId) {
        User user = userDao.findByQqOpenIdAndStatus(openId, Constants.USABLE);
        return loginThird(user);
    }

    private Result loginThird(User user) {
        if(user != null) {
            LoginVo loginVo = packLoginVo(user);
            return new Result(loginVo);
        }
        return new Result(ResultEnum.BIND_MOBILE);
    }

    private LoginVo packLoginVo(User user) {
        //TODO 打包登录后需要返回的信息
        return new LoginVo();
    }

    @Override
    public Result bindAccount(UserBinddingDto userBinddingDto) {
        //TODO 校验验证码
        User user = userDao.findByMobileAndStatus(userBinddingDto.getMobile(),Constants.USABLE);
        if(user == null) {
            boolean bindStatus = binded(user, userBinddingDto.getBinddingType());
            if(bindStatus) {
                return new Result(ResultEnum.MOBILE_IS_IN_USE);
            }else {
                return doBind(user,userBinddingDto);
            }
        }else {
            return registerAndBindOpenId(userBinddingDto);
        }
    }

    private Result doBind(User user, UserBinddingDto dto) {
        if(dto.getBinddingType().equals(Constants.BINDDING_TYPE_WECHAT)){
            user.setUnionId(dto.getOpenId());
        }else if(dto.getBinddingType().equals(Constants.BINDDING_TYPE_QQ)) {
            user.setQqOpenId(dto.getOpenId());
        }
        userDao.saveAndFlush(user);
        return new Result();
    }

    private boolean binded(User user, @NotNull Integer bindType) {
        if(bindType.equals(Constants.BINDDING_TYPE_WECHAT)){
            return (user.getUnionId() == null ? false : true);
        }else if(bindType.equals(Constants.BINDDING_TYPE_QQ)) {
            return (user.getQqOpenId() == null ? false : true);
        }
        return false;
    }

    private Result registerAndBindOpenId(UserBinddingDto userBinddingDto) {
        //TODO 注册和绑定
        return null;
    }
}
