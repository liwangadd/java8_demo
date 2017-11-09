package com.java8.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String argsp[]) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"), new Apple(120, "red"));
        List<Apple> greenApples = filterApple(inventory, new AppleColorPredicate());
        System.out.println(greenApples);

        List<Apple> heavyApples = filterApple(inventory, new AppleWeightPredicate());
        System.out.println(heavyApples);

        List<Apple> redAndHeavyApple = filterApple(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApple);

    }

    public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple a: inventory){
            if(p.test(a)){
                result.add(a);
            }
        }
        return result;
    }

    interface ApplePredicate{
        public boolean test(Apple a);
    }

    public static class AppleWeightPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150;
        }
    }

    public static class AppleColorPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple a) {
            return "green".equals(a.getColor());
        }
    }

    public static class AppleRedAndHeavyPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple a) {
            return "red".equals(a.getColor()) && a.getWeight() > 150;
        }
    }

    public static class  Apple{
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
