package com.cvc.certmap.handler;

import com.cvc.certmap.enums.GoodsTypeEnum;
import org.springframework.context.ApplicationListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther xiehuaxin
 * @create 2018-12-13 18:43
 * @todo
 */
public class WalletPayHandlerFactory implements ApplicationListener<WalletPayHandlerEvent> {

    private static Map<GoodsTypeEnum, WalletPayHandler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(WalletPayHandlerEvent event) {
        WalletPayHandler handler = (WalletPayHandler) event.getSource();
        this.registerHandler(handler);
    }

    private void registerHandler(WalletPayHandler handler) {
        GoodsTypeEnum goodsType = handler.getGoodsType();
        handlerMap.put(goodsType,handler);
    }

    public static WalletPayHandler getHandler(Integer goodsType) {
        GoodsTypeEnum goodsTypeEnum = GoodsTypeEnum.getEnumByType(goodsType);
        WalletPayHandler handler = handlerMap.get(goodsTypeEnum);
        return handler;
    }
}
