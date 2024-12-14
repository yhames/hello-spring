package com.example.hellospring;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public List<String> sortByLength(List<String> strings) {
        strings.sort(Comparator.comparingInt(String::length));
        return strings;
    }
    public static void main(String[] args) {
        Sort sort = new Sort();
        List<String> scores = sort.sortByLength(Arrays.asList("a", "bb", "ccc", "dddd", "eeeee"));
        scores.forEach(System.out::println);
    }
}
