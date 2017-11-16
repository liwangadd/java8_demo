package com.java8.chap6;

import java.util.stream.Collectors;

public class Reducing {

    public static void main(String... args){
        System.out.println(calculateTotalCalories());
        System.out.println(calculateTotalCaloriesUsingSum());
        System.out.println(calculateTotalCaloriesWithMethodReference());
        System.out.println(calculateTotalCaloriesWithoutCollectors());
    }

    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (a, b) -> a + b));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return Dish.menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
    }

    private static int calculateTotalCaloriesUsingSum(){
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }

}
