package controllers.Servlets.loginCommand;

import java.util.ResourceBundle;

public class MessageManager1 {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.messages");
    private MessageManager1(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
