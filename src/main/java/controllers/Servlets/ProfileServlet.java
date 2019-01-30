package controllers.Servlets;

import com.google.gson.JsonObject;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;
import controllers.entity.Student;
import controllers.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        JsonObject jsonObject = new JsonObject();
        HttpSession session = request.getSession(false);
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()){
            String element = e.nextElement().toString();
            if(element.equals("student")){
                Student student = (Student) session.getAttribute("student");
                User user = daoFactory.getUserDAO().getUserById(student.getId_auth());
                jsonObject.addProperty("role","student");
                jsonObject.addProperty("name",student.getFirstName());
                jsonObject.addProperty("surname",student.getLastName());
                jsonObject.addProperty("login",user.getLogin());
            }else if(element.equals("lecturer")){
                Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
                User user = daoFactory.getUserDAO().getUserById(lecturer.getId_auth());
                jsonObject.addProperty("role","lecturer");
                jsonObject.addProperty("name",lecturer.getName());
                jsonObject.addProperty("surname",lecturer.getSurname());
                jsonObject.addProperty("login",user.getLogin());
            }
        }
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
