package com.java8.chap11.v1;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String... args){
        execute("sequential", ()->bestPriceFinder.findPricesSequential("iPhone"));
        execute("parallel", ()->bestPriceFinder.findPricesParallel("iPhone"));
        execute("composed CompletableFuture", ()->bestPriceFinder.findPricesFuture("iPhone"));
        execute("combined USD Future in Java7", ()->bestPriceFinder.findPricesInUSDJava7("iPhone"));
        execute("combined USD CompletableFuture", ()->bestPriceFinder.findPricesInUSD("iPhone"));
        execute("combined USD CompletableFuture v2", ()->bestPriceFinder.findPricesInUSD2("iPhone"));
        execute("combined USD CompletableFuture v3", ()->bestPriceFinder.findPricesInUSD3("iPhone"));
    }

    private static void execute(String msg, Supplier<List<String>> s){
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = ((System.nanoTime()-start)/1000000);
        System.out.println(msg+" done in " + duration+" msecs");
    }

}
