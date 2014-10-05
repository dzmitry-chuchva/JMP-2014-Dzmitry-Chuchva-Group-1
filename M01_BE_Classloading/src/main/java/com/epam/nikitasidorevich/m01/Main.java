package com.epam.nikitasidorevich.m01;

import com.epam.nikitasidorevich.m01.util.ConfigManager;

public class Main {

    private static void processArgument(String argument) {
        ConfigManager configManager = ConfigManager.getInstance();

        if(argument.startsWith("-")) {
            String optionValue = configManager.getProperty(argument);
            System.out.println(optionValue);
        }
    }

    public static void main(String[] args) {
        for (String argument : args) {
            processArgument(argument);
        }
    }
}
