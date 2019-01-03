package com.cvc.certmap.enums;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 16:28
 * @todo
 */
public enum RoleEnum {
    USER(1,"用户"),
    EXPERT(2,"专家");

    private int role;

    private String name;

    RoleEnum(int role, String name){
        this.role = role;
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
