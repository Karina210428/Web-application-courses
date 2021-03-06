package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Course;
import controllers.entity.Lecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "EditCourse")
public class EditCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }
        Gson gson = new Gson();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        Course courseJSON = gson.fromJson(json, Course.class);
        HttpSession session = request.getSession(false);
        int id = (Integer) session.getAttribute("idCourse");
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");

        Course course = new Course();
        course.setId(id);
        course.setName(courseJSON.getName());
        course.setLecturer(lecturer);
        course.setAboutCourse(courseJSON.getAboutCourse());
        course.setStartDate(courseJSON.getStartDate());
        course.setFinishDate(courseJSON.getFinishDate());
        daoFactory.getCourseDAO().update(course);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
