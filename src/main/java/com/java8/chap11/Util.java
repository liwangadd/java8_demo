package com.java8.chap11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Util {

    private static final Random RANDOM = new Random();
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.CHINA));

    public static void delay(){
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number){
        synchronized (FORMAT) {
            return Double.parseDouble(FORMAT.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures){
        return CompletableFuture.supplyAsync(()->futures.stream().map(future->future.join()).collect(Collectors.toList()));
    }

}
