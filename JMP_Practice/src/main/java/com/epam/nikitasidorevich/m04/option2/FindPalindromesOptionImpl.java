package com.epam.nikitasidorevich.m04.option2;

public class FindPalindromesOptionImpl implements Runnable {
    private String[] arguments;

    public FindPalindromesOptionImpl(String[] arguments) {
        this.arguments = arguments;
    }

    private void printArguments(String... arguments) {
        for (String argument : arguments) {
            System.out.print(argument + " ");
        }
        System.out.println();
    }

    private String reverseArgument(String argument) {
        String reversedArgument = "";
        int length = argument.length();
        for (int i = length - 1; i >= 0; i--) {
            reversedArgument = reversedArgument + argument.charAt(i);
        }
        return reversedArgument;
    }

    @Override
    public void run() {
        System.out.print("Initial state of arguments: ");
        printArguments(arguments);

        System.out.print("Palindromes found: ");
        for (String argument : arguments) {
            String reversedArgument = reverseArgument(argument);
            if (argument.equals(reversedArgument)) {
                System.out.print(argument + " ");
            }
        }
        System.out.println();
    }
}
