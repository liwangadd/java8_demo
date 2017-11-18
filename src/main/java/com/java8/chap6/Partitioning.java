package com.java8.chap6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Partitioning {

    public static void main(String... args){
        System.out.println(partitionByVegetarian());
        System.out.println(vegetarianDishesByType());
        System.out.println(mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegetarian(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian(){
        return Dish.menu.stream().collect(
                Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get))
        );
    }

}
