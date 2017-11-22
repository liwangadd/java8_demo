package com.java8.chap9;

import java.util.function.Function;

public class Letter {

    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: \n" + text;
    }

    public static String addFooter(String text){
        return text+"\nKind regards";
    }

    public static String checkSpelling(String text){
        return text.replaceAll("C\\+\\+", "**censored**");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        String letter = addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling).apply("C++ stay away from me!");
        System.out.println(letter);
    }
}
