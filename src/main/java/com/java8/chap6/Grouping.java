package com.java8.chap6;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Grouping {

    public enum CaloricLevel{
        DIET, NORMAL, FAT
    }

    public static void main(String... args){
        System.out.println(groupDishesByType());
        System.out.println(groupDishNamesByType());
        System.out.println(groupCaloricDishesByType());
        System.out.println(groupDishedByTypeAndCaloricLevel());
        System.out.println(countDishesInGroups());
        System.out.println(mostCaloricDishesByTypeWithoutOptionals());
        System.out.println(sumCaloriesByType());
        System.out.println(caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType(){
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType(){
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType(){
        return Dish.menu.stream().filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel(){
        return Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy((Dish dish)->{
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));
    }

    private static Map<Dish.Type, Long> countDishesInGroups(){
        return Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals(){
        return Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.reducing((d1, d2)->d1.getCalories()>d2.getCalories()?d1:d2),
                                Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType(){
        return Dish.menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories))
        );
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType(){
        return Dish.menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.mapping(
                        (Dish dish)->{
                            if(dish.getCalories()<=400) return CaloricLevel.DIET;
                            else if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, Collectors.toSet()
                ))
        );
    }
}
