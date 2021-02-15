package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {-1,9,8,7,6,6,2,2,1,12,10};
        System.out.println(Arrays.toString(values) + " -> " + minValue(values));

        List<Integer> integers = Arrays.asList(8,9);
        System.out.println(integers + " -> " + oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        if (values == null) {
            return 0;
        }

        return Arrays.stream(values)
                .distinct()
                .filter(value -> value < 10 && value > 0)
                .sorted()
                .reduce(0, (identity, value) -> identity * 10 + value);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers == null) {
            return new LinkedList<>();
        }

        Map<Boolean,List<Integer>> mapOddsEvens = integers.stream()
                .collect(Collectors.partitioningBy(value -> value % 2 == 0));

        return mapOddsEvens.get((mapOddsEvens.get(false).size() % 2) != 0);
    }
}
