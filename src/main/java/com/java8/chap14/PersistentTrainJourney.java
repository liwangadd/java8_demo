package com.java8.chap14;

import java.util.function.Consumer;

public class PersistentTrainJourney {

    public static void main(String[] args) {
        TrainJourney tj1 = new TrainJourney(40, new TrainJourney(30, null));
        TrainJourney tj2 = new TrainJourney(20, new TrainJourney(50, null));

        TrainJourney appended = append(tj1, tj2);
        System.out.println();

        TrainJourney appended2 = append(tj1, tj2);
        visit(appended2, tj-> System.out.println(tj.price +" - "));
        System.out.println();

        TrainJourney linked  = link(tj1, tj2);
        visit(linked, tj-> System.out.println(tj.price + " - "));
        System.out.println();
    }

    static class TrainJourney {
        public int price;
        public TrainJourney onward;

        public TrainJourney(int pricce, TrainJourney onward) {
            this.price = pricce;
            this.onward = onward;
        }
    }

    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    static void visit(TrainJourney journey, Consumer<TrainJourney> consumer) {
        if (journey != null) {
            consumer.accept(journey);
            visit(journey.onward, consumer);
        }
    }
}
