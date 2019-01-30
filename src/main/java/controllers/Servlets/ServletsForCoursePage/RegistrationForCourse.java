package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.DAO.ParticipantDAO;
import controllers.entity.Course;
import controllers.entity.Participant;
import controllers.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "RegistrationForCourse")
public class RegistrationForCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }

        HttpSession session = request.getSession(true);
        Student student = (Student) session.getAttribute("student");
        Gson gson = new Gson();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        int listItem = gson.fromJson(json,Integer.TYPE);

        Course course = daoFactory.getCourseDAO().findAll().get(listItem);

        Participant participantNew = new Participant();
        participantNew.setStudent(student);
        participantNew.setCourse(course);
        participantNew.setComment("");

        ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
        participantDAO.create(participantNew);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson("OK"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
