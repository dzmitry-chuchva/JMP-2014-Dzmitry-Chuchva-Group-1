package com.epam.nikitasidorevich.m01.classloader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends SecureClassLoader {
    private String jarFilePath;

    public JarClassLoader(String jarFilePath) {
        this.jarFilePath = jarFilePath;
    }

    public JarClassLoader(String jarFilePath, ClassLoader parent) {
        super(parent);
        this.jarFilePath = jarFilePath;
    }

    private String getPathToClass(String className) {
        String pathToClass = className.replace('.', '/') + ".class";
        return pathToClass;
    }

    private byte[] getClassBytes(String className) {
        byte[] classBytes = null;
        try {
            JarFile jarFile = new JarFile(jarFilePath);
            JarEntry jarEntry = jarFile.getJarEntry(className);
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int size = inputStream.available();
            classBytes = new byte[size];
            dataInputStream.readFully(classBytes);
            dataInputStream.close();
        } catch (IOException e) {
            System.out.println("I/O error occured.");
        }
        return classBytes;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String pathToClass = getPathToClass(name);
        byte[] classBytes = getClassBytes(pathToClass);
        Class loadedClass = defineClass(name, classBytes, 0, classBytes.length);
        return loadedClass;
    }
}
