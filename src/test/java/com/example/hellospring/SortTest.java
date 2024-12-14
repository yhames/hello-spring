package com.example.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    void sort() {
        List<String> strings = sort.sortByLength(Arrays.asList("aa", "b"));
        Assertions.assertThat(strings).containsExactly("b", "aa");
    }

    @Test
    void sort3Items() {
        List<String> strings = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));
        Assertions.assertThat(strings).containsExactly("b", "aa", "ccc");
    }

    @Test
    void sortAlreadySorted() {
        List<String> strings = sort.sortByLength(Arrays.asList("a", "bb", "ccc", "dddd", "eeeee"));
        Assertions.assertThat(strings).containsExactly("a", "bb", "ccc", "dddd", "eeeee");
    }
}
