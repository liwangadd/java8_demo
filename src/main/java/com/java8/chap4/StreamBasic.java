package com.java8.chap4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamBasic {

    public static void main(String ...args){

        System.out.println(getLowCaloricDishesNamesInJava7(Dish.menu));
        System.out.println("----------------------------------");
        System.out.println(getLowCaloricDishesNamesInJava8(Dish.menu));

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish: dishes){
            if(dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> result = new ArrayList<>();
        for(Dish dish: lowCaloricDishes){
            result.add(dish.getName());
        }
        return result;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        List<String> result = dishes.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(dish -> dish.getName())
                .collect(Collectors.toList());
        return result;
    }
}
