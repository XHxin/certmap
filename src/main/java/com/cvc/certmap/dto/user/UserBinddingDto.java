package com.cvc.certmap.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @auther xiehuaxin
 * @create 2018-12-29 15:30
 * @todo
 */
@Data
public class UserBinddingDto {

    @Pattern(regexp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$", flags = Pattern.Flag.COMMENTS)
    private String mobile;

    @NotEmpty
    private String code;

    @NotEmpty
    private String openId;

    //绑定类型：0-qq  1-wechat
    @NotNull
    private Integer binddingType;
}
