package com.epam.ns.opt1;

import com.epam.ns.opt1.runner.MyOptionRunner;
import com.epam.ns.opt1.runner.OptionRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String OPTION_REGEXP = "-[a-zA-Z0-9]*Option";

    private static ArrayList<Integer> getOptionIndexesFromProgramArguments(String[] args) {
        ArrayList<Integer> optionIndexes = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches(OPTION_REGEXP)) {
                optionIndexes.add(i);
            }
        }
        optionIndexes.add(args.length);
        return optionIndexes;
    }

    public static void main(String[] args) {
        OptionRunner optionRunner = new MyOptionRunner();

        List<Integer> optionIndexes = getOptionIndexesFromProgramArguments(args);
        executeOption(args, optionIndexes, optionRunner);
    }

    private static void executeOption(String[] args, List<Integer> optionIndexes, OptionRunner optionRunner) {
        for (int i = 0; i < optionIndexes.size() - 1; i++) {
            String type = args[optionIndexes.get(i)];

            String[] optionArgumnents = Arrays.copyOfRange(args, optionIndexes.get(i) + 1, optionIndexes.get(i + 1));

            optionRunner.executeOption(type, optionArgumnents);
        }
    }
}
