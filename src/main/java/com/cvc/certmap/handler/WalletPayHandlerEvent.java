package com.cvc.certmap.handler;

import org.springframework.context.ApplicationEvent;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 18:30
 * @todo
 */
public class WalletPayHandlerEvent extends ApplicationEvent {


    public WalletPayHandlerEvent(WalletPayHandler source) {
        super(source);
    }

}
