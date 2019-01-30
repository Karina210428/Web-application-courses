package controllers.Servlets.servletsForAdmin;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletAdminInfo")
public class ServletAdminInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<User> users = new ArrayList<>();
        HttpSession session = request.getSession(false);
        if(session.getAttribute("admin").equals("admin")){
            users = daoFactory.getUserDAO().findAll();
        }
        Gson gson = new Gson();
        String usersJsonStr =  gson.toJson(users);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(usersJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
