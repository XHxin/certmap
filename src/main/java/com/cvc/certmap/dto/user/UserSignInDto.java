package com.cvc.certmap.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 15:28
 * @todo
 */
@Data
public class UserSignInDto {

    /**
     * 笔记：
     * NotBlank、@NotNull、@NotEmpty三者之间的区别
     * 1.@NotNull：不能为null，但可以为空字符串
     * 2.@NotEmpty：不能为null，并且长度必须大于0
     * 3.@NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
     *
     *
     * @Pattern
     * 平白字符模式 模式：Pattern.LITERAL          启用这个模式之后，所有元字符、转义字符都被看成普通的字符，不再具有其他意义。
     * 启用 dotall 模式：Pattern.DOTALL            启用dotall模式，一般情况下，点号（.）匹配任意字符，但不匹配换行符，启用这个模式之后，点号还能匹配换行符。
     * 启用注释：Pattern.COMMENTS                  启用注释，开启之后，正则表达式中的空格以及#号行将被忽略。
     * 忽略大小写：Pattern.CASE_INSENSITIVE 示例   有的时候，需要进行忽略大小写的匹配。该例子实现匹配摄氏温度和华氏温度，对于以C、c、F和f结尾的温度值都能匹配。
     * 多行模式：Pattern.MULTILINE 示例            我测试了一下，也就是说如果没有 MULTILINE 标志的话， ^ 和 $ 只能匹配输入序列的开始和结束；否则，就可以匹配输入序列内部的行结束符。
     *
     */

    @Pattern(regexp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$", flags = Pattern.Flag.COMMENTS)
    private String mobile;

    @NotBlank
    private String realName;

    @NotNull
    private Integer role;

    @Pattern(regexp = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

}
