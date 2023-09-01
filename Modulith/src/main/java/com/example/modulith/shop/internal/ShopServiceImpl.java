package com.example.modulith.shop.internal;

import com.example.modulith.shop.ShopService;
import org.springframework.stereotype.Component;

@Component
public class ShopServiceImpl implements ShopService {

    @Override
    public String getShopName() {
        return "Hello World!";
    }
}
