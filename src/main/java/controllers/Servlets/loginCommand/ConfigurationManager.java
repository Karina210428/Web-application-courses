package controllers.Servlets.loginCommand;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager ourInstance;
    private ResourceBundle resourceBundle;
    private ConfigurationManager(){}

    public static ConfigurationManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new ConfigurationManager();
            ourInstance.resourceBundle = ResourceBundle.getBundle("config");
        }
        return ourInstance;
    }

    public  String getProperty(String key){
        return (String)this.resourceBundle.getObject(key);
    }
}
