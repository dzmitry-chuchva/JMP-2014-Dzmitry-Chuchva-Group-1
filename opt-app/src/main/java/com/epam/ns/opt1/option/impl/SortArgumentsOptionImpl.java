package com.epam.ns.opt1.option.impl;

import com.epam.ns.opt1.option.Option;

import java.util.Arrays;

public class SortArgumentsOptionImpl implements Option {
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
        Arrays.sort(arguments);
        System.out.print("Arguments sorted alphabetically: ");
        printArguments(arguments);
    }
}