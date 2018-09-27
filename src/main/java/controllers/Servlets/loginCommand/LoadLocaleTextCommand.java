package controllers.Servlets.loginCommand;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;

public class LoadLocaleTextCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        MessageManager manager = MessageManager.getInstance();
        manager.changeResource(request.getSession(false));
        JsonObject data = new JsonObject();
        for(String key: manager.getBundle().keySet()){
            data.addProperty(key,manager.getString(key).substring(1,manager.getString(key).length()-1));
        }
        return  data.toString();
    }
}
