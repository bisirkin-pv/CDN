package settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private Properties properties = null;

    public PropertyReader(String propertyFileName) throws IOException {
        properties = new Properties();
        properties.load(new BufferedReader(new FileReader(propertyFileName)));
    }

    public String getProperties(String name){
        return properties.getProperty(name);
    }
}
