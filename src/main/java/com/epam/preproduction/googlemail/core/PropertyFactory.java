package com.epam.preproduction.googlemail.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Viktoriia on 26.06.2015.
 */
public class PropertyFactory {
    public static final String PROPERTIES_FILE = "./tests.properties";
    private static Properties properties;

    public static void getInstance() {
        properties = new Properties();

        try (InputStream inputStream = Files.newInputStream(Paths.get(".").resolve(PROPERTIES_FILE))) {
            properties.load(inputStream);
            setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        getInstance();
        return properties.getProperty(property);
    }

    public static void setProperties(Properties properties) {
        PropertyFactory.properties = properties;
    }

}




