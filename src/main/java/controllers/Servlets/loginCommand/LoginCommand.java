package controllers.Servlets.loginCommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand{

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String data = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if(LoginLogic.checkLogin(login,password)) {

            Users current = LoginLogic.defineUser(login,password);
            String jsonUser = new Gson().toJson(current);

            HttpSession session = request.getSession(false);
            session.setAttribute("user",jsonUser);

            JsonObject jobj = new JsonObject();
            String urlToRedirect = current.getOccupation() == "teacher"
            ? "Main.html" : "Main.html";

            jobj.addProperty("url",urlToRedirect);
            data = jobj.toString();
        }else {
            MessageManager manager = MessageManager.getInstance();
            manager.changeResource(request.getSession(false));
            data = new Gson().toJson(manager.getString("message.login.error"));
        }

        return data;
    }
}
