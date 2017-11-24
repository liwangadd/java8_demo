package com.java8.chap11;

import java.util.concurrent.Future;

public class AsyncShopClient {

    public static void main(String[] args) {
        AsyncShop shop = new AsyncShop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPrice("iPhone");
        long incocationTime = ((System.nanoTime() - start) / 1000000);
        System.out.println("Invocation returned after " + incocationTime + " msecs");
        try {
            System.out.println("Price is " + futurePrice.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long retrivalTime = ((System.nanoTime() - start) / 1000000);
        System.out.println("Price returned after " + retrivalTime + " msecs");
    }

}
