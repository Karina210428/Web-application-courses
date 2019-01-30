package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Course;
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
import java.util.List;

public class UpdateParticipant extends HttpServlet {
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

        Participant participant = gson.fromJson(json,Participant.class);
        HttpSession session = request.getSession(false);
        int id = (Integer) session.getAttribute("idParticipant");

        List<Participant> participantsToUpdate = daoFactory.getParticipantDAO().findAll();
        Participant participantToUpdate = new Participant();
        for (Participant p : participantsToUpdate) {
            if(p.getId() == id){
                participantToUpdate = p;
            }
        }

        Student student = new Student();
        student.setFirstName(participant.getStudent().getFirstName());
        student.setLastName(participant.getStudent().getLastName());
        student.setId(participantToUpdate.getStudent().getId());
        daoFactory.getStudentDAO().update(student);

        Course course = daoFactory.getCourseDAO().getCourseById(participant.getCourse().getId());

        Participant participant1 = new Participant();
        participant.setStudent(student);
        participant.setGrade(participant.getGrade());
        participant.setComment(participant.getComment());
        participant.setCourse(participant.getCourse());
        participant.setId(participantToUpdate.getId());
        daoFactory.getParticipantDAO().update(participant);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
