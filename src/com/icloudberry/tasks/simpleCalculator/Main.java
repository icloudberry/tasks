package com.icloudberry.tasks.simpleCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().interpret(1, new String[]{"+", "*"}, new int[]{1, 3}));
    }

    public int interpret(int value, String[] commands, int[] args) {
        if (commands.length != args.length) return -1;
        int res = value;
        for (int i = 0; i < commands.length; i++) {
            if (unknown(commands[i])) return -1;
            res = apply(res, commands[i], args[i]);
        }
        return res;
    }

    private Map<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();

    {
        operations.put("+", (a, b) -> a + b);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
    }

    private boolean unknown(String command) {
        return !operations.containsKey(command);
    }

    // do in java8 style - map with string <-> function
    private int apply(int value, final String command, int arg) {
        return operations.get(command).apply(value, arg);
    }

}
