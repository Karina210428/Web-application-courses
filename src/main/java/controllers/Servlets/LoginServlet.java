package controllers.Servlets;

import controllers.Servlets.loginCommand.ActionCommand;
import controllers.Servlets.loginCommand.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private Logger log = Logger.getLogger(LoginServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Information");
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String answer = null;
        answer = command.execute(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if(answer!=null){
            response.getWriter().write(answer);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
