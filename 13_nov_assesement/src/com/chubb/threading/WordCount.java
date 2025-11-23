package com.chubb.threading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Users\\kunal\\chubb_workspace\\BasicUnderstanding\\src\\com\\chubb\\threading\\reading.txt";
        int traditionalCount = countWordTraditional(filepath);
        System.out.println("Traditional Count : " + traditionalCount);
        long functionalCount = countWordFunctional(filepath);
        System.out.println("Functional Count : " + functionalCount);
    }
    public static int countWordTraditional(String filepath) throws IOException {
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");

                for (String word : words) {
                    if (word.toLowerCase().equals("india")) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
    public static long countWordFunctional(String filepath) throws IOException {

        return Files.lines(Paths.get(filepath))
                .flatMap(line -> Arrays.stream(line.split("\\W+")))
                .map(String::toLowerCase)
                .filter(word -> word.equals("india"))
                .count();
    }
}
