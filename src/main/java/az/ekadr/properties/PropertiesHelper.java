package az.ekadr.properties;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesHelper {
    @SneakyThrows
    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.load(new FileReader(new File("C:\\Users\\vusal\\Desktop\\config.properties")));
        return properties;
    }
}
