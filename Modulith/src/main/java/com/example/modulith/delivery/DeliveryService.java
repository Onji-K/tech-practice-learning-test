package com.example.modulith.delivery;

import com.example.modulith.shop.internal.ShopServiceImpl;

public class DeliveryService {
    public void printShopName() {
        ShopServiceImpl shopService = new ShopServiceImpl();
        System.out.println(shopService.getShopName());
    }
}
