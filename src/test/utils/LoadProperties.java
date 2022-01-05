package test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    LoadProperties loadProperties;
    static Properties properties = new Properties();

    private LoadProperties() {

    }

    public static void init() {
        try {
            // /Users/willierose/Desktop/XYZBank/src/test/java/resources/properties.properties
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        init();
        return properties.getProperty(key);
    }
}
