package com.java8.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuttingIntoPractice {
    public static void main(String... args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        tr2011.forEach(System.out::println);
        System.out.println();

        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        cities.forEach(System.out::println);
        System.out.println();

        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        traders.forEach(System.out::println);
        System.out.println();

        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .map(trader -> trader.getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2)->n1+" "+n2);
        System.out.println(traderStr);
        System.out.println();

        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);
        System.out.println();

        Integer MilanTransactionSum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::sum);
        System.out.println(MilanTransactionSum);
        System.out.println();

        Integer max = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::max);
        System.out.println(max);
        System.out.println();

        Optional<Transaction> minTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1);
        minTransaction.ifPresent(System.out::println);
    }
}
