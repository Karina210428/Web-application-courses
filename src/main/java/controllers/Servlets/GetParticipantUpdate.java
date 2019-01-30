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

public class GetParticipantUpdate extends HttpServlet {
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
        int id = (Integer) session.getAttribute("idParticipant");

        List<Participant> participantsToUpdate = daoFactory.getParticipantDAO().findAll();
        Participant participantToUpdate = new Participant();
        for (Participant p : participantsToUpdate) {
            if(p.getId() == id){
                participantToUpdate = p;
            }
        }

        Student student = new Student();
        student.setFirstName(participantToUpdate.getStudent().getFirstName());
        student.setLastName(participantToUpdate.getStudent().getLastName());
        student.setId(participantToUpdate.getStudent().getId());

        Course course = daoFactory.getCourseDAO().getCourseById(participantToUpdate.getCourse().getId());

        Participant participant1 = new Participant();
        participant1.setStudent(student);
        participant1.setGrade(participantToUpdate.getGrade());
        participant1.setComment(participantToUpdate.getComment());
        participant1.setCourse(participantToUpdate.getCourse());
        participant1.setId(participantToUpdate.getId());
        String participantJsonStr = gson.toJson(participant1);
        response.getWriter().write(participantJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
