package com.epam.ns.opt1.option.impl;

import com.epam.ns.opt1.option.Option;

public class SumArgumentsOptionImpl implements Option {
    private static final String NUMBER_REGEXP = "-?\\d+(\\.\\d+)?";
    private void printArguments(String... arguments) {
        for (String argument : arguments) {
            System.out.print(argument + " ");
        }
        System.out.println();
    }
    @Override
    public void run(String... arguments) {
        System.out.print("Initial state of arguments: ");
        printArguments(arguments);
        double sum = 0;
        for (String argument : arguments) {
            argument = argument.replaceAll(",",".");
            if (argument.matches(NUMBER_REGEXP)) {
                sum += Double.parseDouble(argument);
            }
        }
        System.out.print("Sum of numbers found: " + sum + "\n");
    }
}