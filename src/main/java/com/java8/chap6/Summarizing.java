package com.java8.chap6;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Summarizing {

    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }

    private static long howManyDishes(){
        return Dish.menu.stream().count();
    }

    private static Dish findMostCaloricDish(){
        return Dish.menu.stream().collect(Collectors.reducing((Dish d1,Dish d2)->d1.getCalories()>d2.getCalories()?d1:d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator(){
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        Collector<Dish, ?, Optional<Dish>> moreCaloricOf = Collectors.maxBy(dishCaloriesComparator);
        return Dish.menu.stream().collect(moreCaloricOf).get();
//        BinaryOperator<Dish> moreCaloricOf2 = BinaryOperator.maxBy(dishCaloriesComparator);
//        return Dish.menu.stream().collect(Collectors.reducing(moreCaloricOf2)).get();
    }

    private static int calculateTotalCalories(){
        return Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    }

    private static double calculateAverageCalories(){
        return Dish.menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics(){
        return Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu(){
        return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
    }

    private static String getShortMenuCommaSeparated(){
        return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }

}
