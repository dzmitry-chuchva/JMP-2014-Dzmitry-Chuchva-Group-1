package com.epam.nikitasidorevich.m04.runner;

import com.epam.nikitasidorevich.m01.classloader.JarClassLoader;
import com.epam.nikitasidorevich.m04.executor.ThreadExecutor;
import com.epam.nikitasidorevich.m04.util.PropertiesManager;

import java.lang.reflect.Constructor;
import java.util.*;

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

    private static List<Thread> loadOptions(String[] args, List<Integer> optionIndexes) {
        List<Thread> threads = new ArrayList<Thread>();
        ThreadGroup threadGroup = new ThreadGroup("OptionThreads");
        try {
            Properties properties = PropertiesManager.loadProperties("m04_config.properties");
            ClassLoader classLoader = new JarClassLoader(properties.getProperty("path-to-jar"));
            Runnable runnable = null;
            for (int i = 0; i < optionIndexes.size() - 1; i++) {
                Class<?> loadedClass = classLoader.loadClass(properties.getProperty(args[optionIndexes.get(i)]));
                if (Runnable.class.isAssignableFrom(loadedClass) && !loadedClass.isInterface()) {
                    String optionArgumnents[] = Arrays.copyOfRange(args, optionIndexes.get(i) + 1, optionIndexes.get(i + 1));
                    Constructor constructor = loadedClass.getConstructor(String[].class);
                    Object arg = optionArgumnents;
                    runnable = (Runnable) constructor.newInstance(arg);
                    threads.add(new Thread(threadGroup, runnable));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return threads;
    }


    public static void main(String[] args) {
        List<Integer> optionIndexes = getOptionIndexesFromProgramArguments(args);
        List<Thread> threads = loadOptions(args, optionIndexes);
        System.out.println("Options found: ");
        for (int i = 0; i < optionIndexes.size() - 1; i++) {
            System.out.println(args[optionIndexes.get(i)]);
        }
        System.out.println();

        System.out.println("Enter 1 for one-by-one execution or 2 for parallel: ");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        switch (userChoice) {
            case "1" :
                ThreadExecutor.executeOneByOne(threads);
                break;
            case "2" :
                ThreadExecutor.executeParallel(threads);
                break;
            default:
                System.out.println("Wrong input.");
                System.exit(0);
        }
        Vector<Integer> v = new Vector<Integer>();
        v.add(1);
        Iterator<Integer> it = v.iterator();
        v.add(2);
        while(it.hasNext())
            System.out.println(it.next());
    }
}
