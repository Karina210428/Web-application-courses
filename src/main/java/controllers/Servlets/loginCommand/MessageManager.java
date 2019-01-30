package controllers.Servlets.loginCommand;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private static MessageManager instance;

    private MessageManager(){
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public static MessageManager getInstance()
    {
        if (instance == null)
            instance = new MessageManager();
        return instance;
    }

    private ResourceBundle resourceBundle;
    private final String resourceName = "lable";

    public void changeResource(HttpSession session){
        String jsonLocale = session.getAttribute("locale").toString();
        Locale current = new Locale(jsonLocale);
        resourceBundle = ResourceBundle.getBundle(resourceName,current);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }

    public ResourceBundle getBundle(){
        return resourceBundle;
    }

}
