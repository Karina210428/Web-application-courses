package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AjaxJson extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<Participant> participantsList = daoFactory.getParticipantDAO().findAll();
        HttpSession session = request.getSession(false);
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        List<Participant> participantsListByLecturer = new ArrayList<Participant>();
        for (Participant p: participantsList) {
            if(p.getCourse().getLecturer().equals(lecturer)) {
                participantsListByLecturer.add(p);
            }
        }
        Gson gson = new Gson();
        String participantsJsonStr =  gson.toJson(participantsListByLecturer);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
