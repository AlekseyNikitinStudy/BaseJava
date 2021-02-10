package ru.javawebinar.basejava;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {-1,9,8,7,2,2,1,12,10};
        System.out.println(Arrays.toString(values) + " -> " + minValue(values));

        List<Integer> integers = Arrays.asList(0,1,2,3,4,5);
        System.out.println(integers + " -> " + oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        if (values == null) {
            return 0;
        }

        AtomicReference<Integer> result = new AtomicReference<>(0);
        AtomicReference<Integer> index = new AtomicReference<>(0);
        Arrays.stream(values)
                .filter(value -> value < 10 && value > 0)
                .distinct()
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(value -> {
                    result.set(result.get() + value * (int) Math.pow(10, index.get()));
                    index.set(index.get() + 1);
                });

        return result.get();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers == null) {
            return new LinkedList<>();
        }

        Map<Boolean,List<Integer>> mapOddsEvens = integers.stream()
                .collect(Collectors.partitioningBy(value -> value % 2 == 0));
        
        return (mapOddsEvens.get(false).size() % 2) == 0 ? mapOddsEvens.get(true) : mapOddsEvens.get(false);
    }
}
