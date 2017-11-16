package com.java8.chap6;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );

    public static void main(String... args){
        groupImperatively();
        groupFunctionally();
    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for(Transaction transaction : transactions){
            Currency currency = transaction.getCurrency();
            if(transactionsByCurrencies.containsKey(currency)){
               transactionsByCurrencies.get(currency).add(transaction);
            }else{
                List<Transaction> transactionsForCurrency = new ArrayList<>();
                transactionsForCurrency.add(transaction);
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
        }
        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally(){
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    public enum Currency{
        EUR, USD, JPY, GBP, CHF
    }

    public static class Transaction{
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "currency=" + currency +
                    ", value=" + value +
                    '}';
        }
    }

}
