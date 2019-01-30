package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

public class RecordingIdCourseToSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder str = new StringBuilder();
        String json = "";
        if(br!=null){
            str.append(br.readLine());
        }
        Gson gson = new Gson();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(str.toString());

        int id = jsonObject.get("id").getAsInt();
        HttpSession session = request.getSession(false);

        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        Course courseToUpdate= daoFactory.getCourseDAO().getCourseByLectureId(lecturer.getId()).get(id);
        session.setAttribute("idCourse", courseToUpdate.getId());
        String courseJsonStr = gson.toJson(courseToUpdate);
        response.getWriter().write(courseJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
