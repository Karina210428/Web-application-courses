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

@WebServlet(name = "GetUpdateCourse")
public class GetUpdateCourse extends HttpServlet {
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
        HttpSession session = request.getSession(false);
        int id = (Integer) session.getAttribute("idCourse");
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        Course courseUpdate = daoFactory.getCourseDAO().getCourseById(id);

        Course course = new Course();
        course.setId(id);
        course.setName(courseUpdate.getName());
        course.setLecturer(lecturer);
        course.setAboutCourse(courseUpdate.getAboutCourse());
        course.setStartDate(courseUpdate.getStartDate());
        course.setFinishDate(courseUpdate.getFinishDate());
        String courseJsonStr = gson.toJson(course);
        response.getWriter().write(courseJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
