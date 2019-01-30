package controllers.Servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.util.List;

@WebServlet(name = "DeleteProfile")
public class DeleteProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<User> users = daoFactory.getUserDAO().findAll();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(session.getAttribute("user").toString());
        int id = jsonObject.get("id").getAsInt();
        daoFactory.getUserDAO().delete(daoFactory.getUserDAO().getUserById(id));
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()){
            String element = e.nextElement().toString();
            if(element.equals("student")){
                daoFactory.getStudentDAO().delete ((Student) session.getAttribute("student"));
            }else if(element.equals("lecturer")){
                daoFactory.getLecturerDAO().delete ((Lecturer) session.getAttribute("lecturer"));
            }
        }
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("SignUp.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
