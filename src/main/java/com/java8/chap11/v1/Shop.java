package com.java8.chap11.v1;

import com.java8.chap11.Util;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        this.random = new Random();
    }

    private double calculatePrice(String product){
        Util.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public String getName() {
        return name;
    }
}
