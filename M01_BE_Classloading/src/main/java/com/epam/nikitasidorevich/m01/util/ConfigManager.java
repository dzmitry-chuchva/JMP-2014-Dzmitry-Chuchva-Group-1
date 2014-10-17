package com.epam.nikitasidorevich.m01.util;

import java.util.ResourceBundle;

/**
 * Utility class, that extracts from the properties-file (resource bundle)
 * information, which is necessary for the functioning of the application
 * Design pattern Singleton used.
 */
public class ConfigManager {
    public static final String PATH_TO_JAR = "path-to-jar";
    private static final String BUNDLE_NAME = "com.epam.nikitasidorevich.m01.config";

    private static ConfigManager instance;
    private ResourceBundle resourceBundle;

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String property) {
        return (String)resourceBundle.getObject(property);
    }
}
