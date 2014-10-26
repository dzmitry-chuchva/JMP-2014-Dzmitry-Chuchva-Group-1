package com.epam.nikitasidorevich.m04.option2;

public class SumArgumentsOptionImpl implements Runnable {
    private static final String NUMBER_REGEXP = "-?\\d+(\\.\\d+)?";
    private String[] arguments;

    public SumArgumentsOptionImpl(String[] arguments) {
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
