package com.java8.chap10;

import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ReadPositiveIntParam {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(5, readDurationImperative(props, "a"));
        assertEquals(0, readDurationImperative(props, "b"));
        assertEquals(0, readDurationImperative(props, "c"));
        assertEquals(0, readDurationImperative(props, "d"));

        assertEquals(5, readDurationWithOptional(props, "a"));
        assertEquals(0, readDurationWithOptional(props, "b"));
        assertEquals(0, readDurationWithOptional(props, "c"));
        assertEquals(0, readDurationWithOptional(props, "d"));
    }

    public static int readDurationImperative(Properties props, String name){
        String value = props.getProperty(name);
        if(value!=null){
            try{
                int i = Integer.parseInt(value);
                if(i>0){
                    return i;
                }
            }catch (NumberFormatException e){
            }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name){
        Integer result = Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::s2i)
                .filter(i -> i > 0)
                .orElse(0);
        return result;
    }

    public static Optional<Integer> s2i(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();
        }
    }

}
