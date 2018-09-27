package controllers.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        String id =  jsonObject.get("id").toString();

        HttpSession session = request.getSession(false);
        session.setAttribute("idParticipant", Integer.parseInt(id));

        int id1 = (Integer) session.getAttribute("idParticipant");

        List<Participant> participantsListToFindByID = daoFactory.getParticipantDAO().findAll();
        Participant participantToUpdate = participantsListToFindByID.get(id1);

        String participantsJsonStr = gson.toJson(participantToUpdate);

        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
