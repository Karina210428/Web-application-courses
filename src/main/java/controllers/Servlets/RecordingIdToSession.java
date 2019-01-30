package controllers.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecordingIdToSession extends HttpServlet {
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

        int id =  jsonObject.get("id").getAsInt();
        HttpSession session = request.getSession(false);
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        List<Participant> participantsListByLecturer = new ArrayList<Participant>();
        for (Participant p: daoFactory.getParticipantDAO().findAll()) {
            if(p.getCourse().getLecturer().equals(lecturer)) {
                participantsListByLecturer.add(p);
            }
        }
        Participant participantToUpdate = participantsListByLecturer.get(id);
        session.setAttribute("idParticipant", participantToUpdate.getId());
        String participantsJsonStr = gson.toJson(participantToUpdate);
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
