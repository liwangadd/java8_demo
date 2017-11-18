package com.java8.chap6;

import sun.font.TrueTypeFont;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitionPrimeNumbers {

    public static void main(String... args) {
        System.out.println(partitionPrimes(100));
        System.out.println(partitionPrimesWithCustomCollector(100));
        System.out.println(partitionPrimesWithInlineCollector(100));
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                Collectors.partitioningBy(PartitionPrimeNumbers::isPrime)
        );
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollectors());
    }

    private static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, candidate - 1)
                .limit((long) (Math.floor(Math.sqrt(candidate)) - 1))
                .noneMatch(i -> candidate % i == 0);
    }

    static boolean isPrime(List<Integer> primes, Integer candidate) {
        boolean result = primes.stream().anyMatch(i -> candidate % i == 0);
        return !result;
    }

    public static class PrimeNumbersCollectors implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> {
                HashMap<Boolean, List<Integer>> supplier = new HashMap<>();
                supplier.put(true, new ArrayList<>());
                supplier.put(false, new ArrayList<>());
                return supplier;
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                acc.get(isPrime(acc.get(true), candidate)).add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return i -> i;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithInlineCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                () -> new HashMap<Boolean, List<Integer>>() {{
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }},
                (acc, candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate),
                (map1, map2) -> {
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                }
        );
    }

}
