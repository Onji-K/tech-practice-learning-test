package com.example.modulith.shop;

import com.example.modulith.shop.internal.ShopServiceImpl;

public class Depend {
    public void printShopName() {
        ShopServiceImpl shopService = new ShopServiceImpl();
        System.out.println(shopService.getShopName());
    }
}
