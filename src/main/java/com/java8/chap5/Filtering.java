package com.java8.chap5;

import com.java8.chap4.Dish;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {

    public static void main(String... args) {

        List<Dish> vegetarianMenu = Dish.menu.stream().filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        vegetarianMenu.forEach(System.out::println);

        System.out.println("-----------------------------------------");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");
        List<Dish> dishesLimit3 = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        dishesLimit3.forEach(System.out::println);

        System.out.println("-----------------------------------------");
        List<Dish> dishesSkip2 = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        dishesSkip2.forEach(System.out::println);

        System.out.println("-----------------------------------------");
        Dish.menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::println);
    }

}
