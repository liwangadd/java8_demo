package com.java8.chap11;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", ()->bestPriceFinder.findPricesSequential("iPhone"));
        execute("parallel", ()->bestPriceFinder.findPricesParallel("iPhone"));
        execute("composed CompletableFuture", ()->bestPriceFinder.findPricesFutures("iPhone"));
        bestPriceFinder.printPriceStream("iPhone");
    }

    private static void execute(String msg, Supplier<List<String>> s){
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = ((System.nanoTime() - start)/1000000);
        System.out.println(msg+" done in " + duration + " msecs");
    }

}
