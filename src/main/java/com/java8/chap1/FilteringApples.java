package com.java8.chap1;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"), new Apple(120, "red"));
        List<Apple> greenApples = FilteringApples.filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        List<Apple> heavyApples = FilteringApples.filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        List<Apple> greenApples2 = FilteringApples.filterApples(inventory, apple -> "green".equals(apple.getColor()));
        System.out.println(greenApples2);

        List<Apple> heavyApples2 = FilteringApples.filterApples(inventory, apple -> apple.getWeight() > 150);
        System.out.println(heavyApples2);

        List<Apple> result = inventory.stream().filter(apple -> "green".equals(apple.getColor()))
                .collect(Collectors.toList());
        System.out.println(result);

        inventory.sort(Comparator.comparing(Apple::getColor));
        System.out.println(inventory);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if ("green".equals(a.getColor())) {
                result.add(a);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if (a.getWeight() > 150) {
                result.add(a);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple a) {
        return "green".equals(a.getColor());
    }

    public static boolean isHeavyApple(Apple a) {
        return a.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    public static class Apple {

        private int weight;
        private String color;

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

}
