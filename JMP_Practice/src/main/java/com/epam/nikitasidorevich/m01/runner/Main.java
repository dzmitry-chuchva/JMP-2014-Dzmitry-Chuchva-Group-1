package com.epam.nikitasidorevich.m01.runner;

import com.epam.nikitasidorevich.m01.classloader.JarClassLoader;
import com.epam.nikitasidorevich.m01.option.Option;
import com.epam.nikitasidorevich.m01.util.ConfigManager;

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

    private static void loadOptions(String[] args, List<Integer> optionIndexes) {
        ConfigManager configManager = ConfigManager.getInstance();
        ClassLoader classLoader = new JarClassLoader(configManager.getProperty(ConfigManager.PATH_TO_JAR));
        Option option = null;
        for (int i = 0; i < optionIndexes.size() - 1; i++) {
            try {
                Class<?> loadedClass = classLoader.loadClass(configManager.getProperty(args[optionIndexes.get(i)]));
                if (Option.class.isAssignableFrom(loadedClass) && !loadedClass.isInterface()) {
                    option = (Option) loadedClass.newInstance();
                    String optionArgumnents[] = Arrays.copyOfRange(args, optionIndexes.get(i) + 1, optionIndexes.get(i+1));
                    option.run(optionArgumnents);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //-sumArgumentsOption -1 x aa AAa 3.3 1.57 - -- -sortArgumentsOption 123 -123 123.12 abcd123 0 24,2 - -- -findPalindromesOption madam sir 121 zzzz sald -x-
    public static void main(String[] args) {
        List<Integer> optionIndexes = getOptionIndexesFromProgramArguments(args);
        loadOptions(args, optionIndexes);
    }
}
