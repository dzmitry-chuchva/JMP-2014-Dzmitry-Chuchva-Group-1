package com.epam.nikitasidorevich.m04.output;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OutputBufferImpl implements Output {
    private ArrayList<String> outputStrings;
    private int capacity;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public OutputBufferImpl(int capacity) {
        this.outputStrings = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public OutputBufferImpl() {
        //default capacity
        this(10);
    }

    @Override
    public void printAll() {
        lock.readLock().lock();
        try {
            for (String outputString : outputStrings) {
                System.out.println(outputString);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void writeToOutput(String s) {
        lock.writeLock().lock();
        try {
            if (outputStrings.size() < capacity) {
                outputStrings.add(s);
            } else {
                printAll();
                flush();
                outputStrings.add(s);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void flush() {
        lock.writeLock().lock();
        try {
            outputStrings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
