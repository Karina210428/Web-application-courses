package controllers.ajax_json;

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

        Lecturer lecturer = new Lecturer();
        lecturer.setName(participant.getCourse().getLecturer().getName());
        lecturer.setPatronymic(participant.getCourse().getLecturer().getPatronymic());
        lecturer.setSurname(participant.getCourse().getLecturer().getSurname());
        daoFactory.getLecturerDAO().create(lecturer);
        lecturer = daoFactory.getLecturerDAO().getLectureByName(lecturer.getName(), lecturer.getPatronymic(), lecturer.getSurname());

        Course course = new Course();
        course.setName(participant.getCourse().getName());
        course.setLecturer(lecturer);
        daoFactory.getCourseDAO().create(course);
        course = daoFactory.getCourseDAO().getCourseByName(course.getName());

        Participant participant1 = new Participant();
        participant.setStudent(student);
        participant.setGrade(participant.getGrade());
        participant.setComment(participant.getComment());
        participant.setCourse(course);

        //daoFactory.getParticipantDAO().create(participant1);
        ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
        participantDAO.create(participant);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //response.getWriter().write(send);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
