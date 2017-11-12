package com.java8.chap5;

import com.java8.chap4.Dish;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapping {

    public static void main(String... args) {

        List<String> dishNames = Dish.menu.stream()
                .map(dish -> dish.getName())
                .collect(Collectors.toList());
        dishNames.forEach(System.out::println);

        System.out.println("-----------------------------------------");
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(word -> word.length())
                .collect(Collectors.toList());
        wordLengths.forEach(System.out::println);

        System.out.println("-----------------------------------------");
        words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(System.out::print);
        System.out.println();

        System.out.println("-----------------------------------------");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(Collectors.toList());
        pairs.forEach(pair-> System.out.println("("+pair[0] + ", "+pair[1]+")"));

        System.out.println("-----------------------------------------");
        List<int[]> pair2 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(pair-> System.out.println("("+pair[0] + ", "+pair[1]+")"));

    }

}
