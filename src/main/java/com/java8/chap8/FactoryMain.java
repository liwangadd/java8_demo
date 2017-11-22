package com.java8.chap8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryMain {

    private static Map<String, Supplier<Product>> map = new HashMap<>();
    static{
        map.put("loan", loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static void main(String... args){
        Product p1 = ProductFactory.createProduct("loan");
        Supplier<Product> loanSupplier = loan::new;
        Product p2 = loanSupplier.get();
        Product p3 = ProductFactory.createProdcutLambda("bond");
    }

    private static class ProductFactory{
        public static Product createProduct(String name){
            switch (name){
                case "loan": return new loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }

        static Product createProdcutLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p!=null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
    }

    private static interface Product{}
    private static class loan implements Product{}
    private static class Stock implements Product{}
    private static class Bond implements Product{}


}
