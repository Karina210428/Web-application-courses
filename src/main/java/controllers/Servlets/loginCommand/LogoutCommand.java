package controllers.Servlets.loginCommand;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("url",page);
        request.getSession().invalidate();
        return jsonObject.toString();
    }
}
