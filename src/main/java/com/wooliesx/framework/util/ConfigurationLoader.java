package com.wooliesx.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    public static Properties loadProperties() {

        Properties properties = new Properties();
        String fileName = null;
        String env = System.getProperty("env"); //This can be set
        //default env is prod
        if (env == null || env.equals("prod")) {
            fileName = "src/main/resources/wooliexconfig_prod.properties";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
