package com.java8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    public static void main(String ...args) throws IOException {

        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---------------------");

        String oneLine = processFile(reader -> reader.readLine());
        System.out.println(oneLine);
        String twoLine = processFile(reader -> reader.readLine() + reader.readLine());
        System.out.println(twoLine);

    }

    public static String processFileLimited() throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))){
            return reader.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))){
            return p.process(reader);
        }
    }

    public interface BufferedReaderProcessor{
        public String process(BufferedReader reader) throws IOException;
    }

}
