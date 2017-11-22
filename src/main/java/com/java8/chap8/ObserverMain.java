package com.java8.chap8;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {

    public static void main(String... args){
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action");

        Feed feedLambda = new Feed();
        feedLambda.registerObserver((String tweet)->{
            if(tweet!=null&&tweet.contains("money")){
                System.out.println("Breaking news in NY!" + tweet);
            }
        });
        feed.registerObserver((String tweet)->{
            if(tweet!=null&&tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        feedLambda.notifyObservers("Money money money, give me money!");
    }

    interface Observer{
        void inform(String tweet);
    }

    interface Subject{
        void registerObserver(Observer observer);
        void notifyObservers(String tweet);
    }

    public static class NYTimes implements Observer{

        @Override
        public void inform(String tweet) {
            if(tweet!=null&&tweet.contains("money")){
                System.out.println("Breaking news in NY!" + tweet);
            }
        }
    }

    public static class Guardian implements Observer{

        @Override
        public void inform(String tweet) {
            if(tweet!=null&&tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        }
    }

    public static class LeMonde implements Observer{

        @Override
        public void inform(String tweet) {
            if(tweet!=null&&tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

    public static class Feed implements Subject{

        private List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(observer -> observer.inform(tweet));
        }
    }
}
