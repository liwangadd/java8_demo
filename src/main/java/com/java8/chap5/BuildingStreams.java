package com.java8.chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String... args) throws IOException {

        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

//        将数组转换成流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

//        使用iterate产生前十个10偶数
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

//        利用iterate产生斐波那契数列
        Stream.iterate(new int[]{0, 1}, pair -> new int[]{pair[1], pair[0] + pair[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(() -> 1)
                .limit(10)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                this.previous = this.current;
                this.current = nextValue;
                return previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        long uniqueWords = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        System.out.println("There are " + uniqueWords + " unique words in data.txt");
    }

}
