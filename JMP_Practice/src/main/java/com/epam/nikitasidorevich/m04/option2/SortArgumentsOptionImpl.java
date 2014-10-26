package com.epam.nikitasidorevich.m04.option2;

import java.util.Arrays;

public class SortArgumentsOptionImpl implements Runnable {
    private String[] arguments;

    public SortArgumentsOptionImpl(String[] arguments) {
        this.arguments = arguments;
    }

    private void printArguments(String... arguments) {
        for (String argument : arguments) {
            System.out.print(argument + " ");
        }
        System.out.println();
    }

    @Override
    public void run() {
        System.out.print("Initial state of arguments: ");
        printArguments(arguments);
        Arrays.sort(arguments);
        System.out.print("Arguments sorted alphabetically: ");
        printArguments(arguments);
    }
}
