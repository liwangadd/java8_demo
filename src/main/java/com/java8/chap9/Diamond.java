package com.java8.chap9;

public class Diamond {

    public static void main(String[] args) {
        new D().hello();
    }

    interface A{
        default public void hello(){
            System.out.println("Hello from A");
        }
    }

    interface B extends A{}

    interface C extends A{}

    static class D implements B, C{

    }

}
