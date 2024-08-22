package com.soroko.carshop.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class PropertiesLoader {

    public static Properties loadProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
          .getClassLoader()
          .getResourceAsStream("application.properties");
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}