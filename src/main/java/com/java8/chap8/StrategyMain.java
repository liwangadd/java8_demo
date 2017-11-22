package com.java8.chap8;

public class StrategyMain {

    public static void main(String... args){
        Validator v1 = new Validator(new IsNumber());
        System.out.println(v1.validation("aaaaaaa"));
        Validator v2 = new Validator(new IsAllLowerCase());
        System.out.println(v2.validation("bbbbb"));

        Validator v3 = new Validator((String s)->s.matches("\\d+"));
        System.out.println(v3.validation("aaaa"));
        Validator v4 = new Validator((String s)->s.matches("[a-z]+"));
        System.out.println(v4.validation("bbbb"));
    }

    interface ValidationStrategy{
        boolean execute(String s);
    }

    private static class IsAllLowerCase implements ValidationStrategy{

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    private static class IsNumber implements ValidationStrategy{

        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    private static class Validator{
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validation(String s){
            return strategy.execute(s);
        }
    }

}
