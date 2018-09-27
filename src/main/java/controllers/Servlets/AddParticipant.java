package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.DAO.ParticipantDAO;
import controllers.entity.Course;
import controllers.entity.Lecturer;
import controllers.entity.Participant;
import controllers.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddParticipant extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        Participant participant = gson.fromJson(json,Participant.class);

        Student student = new Student();
        student.setFirstName(participant.getStudent().getFirstName());
        student.setLastName(participant.getStudent().getLastName());
        daoFactory.getStudentDAO().create(student);
        student = daoFactory.getStudentDAO().getStudentByName(student.getFirstName(), student.getLastName());

        HttpSession session = request.getSession(false);
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");

        Course course = daoFactory.getCourseDAO().findAll().get(participant.getCourse().getId());

        Participant participant1 = new Participant();
        participant1.setStudent(student);
        participant1.setGrade(participant.getGrade());
        participant1.setComment(participant.getComment());
        participant1.setCourse(course);

        //daoFactory.getParticipantDAO().create(participant1);
        ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
        participantDAO.create(participant1);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson("OK"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
