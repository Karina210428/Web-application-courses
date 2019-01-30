package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Course;
import controllers.entity.Lecturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(false);
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        Course courseToFindByIndex = daoFactory.getCourseDAO().getCourseByLectureId(lecturer.getId()).get(id);
        daoFactory.getCourseDAO().delete(courseToFindByIndex);
        List<Course> coursesList = daoFactory.getCourseDAO().getCourseByLectureId(lecturer.getId());
        String participantsJsonStr = gson.toJson(coursesList);
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
