package org.the.score.common.utils;

import lombok.Data;
import java.io.IOException;
import java.util.Properties;

@Data
public class FilesUtility {
    private static final Properties automationProperties = new Properties();
    private static final Properties applicationProperties = new Properties();

    public static void init() {
        try {
            automationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("automation.properties"));
           } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getAutomationProperties(String property) {
        return automationProperties.getProperty(property);
    }

    public static String getApplicationProperties(String property) {
        return applicationProperties.getProperty(property);
    }


}
