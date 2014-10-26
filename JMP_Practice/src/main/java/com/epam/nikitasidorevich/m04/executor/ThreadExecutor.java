package com.epam.nikitasidorevich.m04.executor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadExecutor {

    private ThreadExecutor() {
    }

    public static void executeOneByOne(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void executeParallel(List<Thread> threads) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Thread thread : threads) {
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
