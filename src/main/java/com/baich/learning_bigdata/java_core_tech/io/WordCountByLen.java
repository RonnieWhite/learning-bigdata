package com.baich.learning_bigdata.java_core_tech.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-07
 * Time : 14:48
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WordCountByLen {
    private static String pathFile = "E:/IdeaProjects/learning_bigdata/src/main/java/com/white/learning_bigdata/java_core_tech/io/data.txt";

    public static void main(String[] args) {
        WordCountByLen wordCountByLen = new WordCountByLen();
        int i = wordCountByLen.traditional(pathFile);
        System.out.println("traditional: " + i);
        System.out.println("================");
        int j = wordCountByLen.lambda(pathFile);
        System.out.println("lambda: " + j);
        System.out.println("================");
        int k = wordCountByLen.parallelLambda(pathFile);
        System.out.println("parallelLambda: " + k);
    }

    public int traditional(String file) {
        int count = 0;
        try {
            String contents = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
            String[] words = contents.toLowerCase().split(" ");
            for (String word : words) {
                if (word.length() > 12) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int lambda(String file) {
        int count;
        try {
            String contents = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.toLowerCase().split(" "));
            count = (int) words.stream().filter(word -> word.length() > 12).count();
        } catch (IOException e) {
            e.printStackTrace();
            count = 0;
        }
        return count;
    }

    public int parallelLambda(String file) {
        int count;
        try {
            String contents = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.toLowerCase().split(" "));
            count = (int) words.parallelStream().filter(word -> word.length() > 12).count();
        } catch (IOException e) {
            e.printStackTrace();
            count = 0;
        }
        return count;
    }
}
