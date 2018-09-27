package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DeleteCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();

        Course course = gson.fromJson(json,Course.class);
        int id = course.getId();
        Course courseToFindByIndex = daoFactory.getCourseDAO().findAll().get(id);
        //Course participantForDelete = participantsListToFindByID.get(id);
        daoFactory.getCourseDAO().delete(courseToFindByIndex);
        List<Course> coursesList = daoFactory.getCourseDAO().findAll();
        String participantsJsonStr = gson.toJson(coursesList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
