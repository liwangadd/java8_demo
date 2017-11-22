package com.java8.chap11.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ShopMain {

    public static void main(String... args){
        Shop shop = new Shop("bestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favourite product");
        long invocationTime = ((System.nanoTime() - start) / 1000000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        System.out.println("Doing something else...");

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f\n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = ((System.nanoTime() - start) / 1000000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

}
