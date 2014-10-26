package com.epam.nikitasidorevich.m04.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesManager {

    private PropertiesManager() {
    }

    public static Properties loadProperties(String filename) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(filename)) {
            properties.load(inputStream);
            return properties;
        }
    }
}
