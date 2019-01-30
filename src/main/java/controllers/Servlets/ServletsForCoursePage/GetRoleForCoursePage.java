package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.DAO.DAOFactory;
import controllers.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "GetRoleForCoursePage")
public class GetRoleForCoursePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JsonObject jobj = new JsonObject();
        String role =  "";
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        HttpSession session = request.getSession(false);
        Enumeration e = session.getAttributeNames();
        List<Course> coursesList = null;
        while (e.hasMoreElements()){
            String element = e.nextElement().toString();
            if(element.equals("student")){
                role= "student";
            }else if(element.equals("lecturer")){
                role="lecturer";
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        String courseJsonStr =  gson.toJson(role);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(courseJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
