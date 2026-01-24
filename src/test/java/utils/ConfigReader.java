package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static{
        try{
            properties = new Properties();
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

            if(input==null){
                throw new RuntimeException("config.properties not found");
            }

            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties",e);
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}
