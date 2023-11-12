package common;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
@Singleton
public class Configuration {
    private Properties propertiesxml;

    private Configuration() {
        try {
            propertiesxml = new Properties();
            propertiesxml.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream(Utilities.PROPERTIESXML));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String getPropertyXML(String key) {
        return propertiesxml.getProperty(key);
    }

}
