package controllers.Servlets.loginCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getInstance().getProperty("path.page.login");
        return page;
    }
}
