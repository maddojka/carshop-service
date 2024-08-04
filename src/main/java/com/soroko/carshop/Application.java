package com.soroko.carshop;

import com.soroko.carshop.controller.CarShopManager;

public class Application {

    public static void main(String[] args) {
        CarShopManager carShopManager = new CarShopManager();
        carShopManager.startCarShopLoop();
    }
}