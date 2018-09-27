package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;



public class AjaxJson extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<Participant> participantsList = daoFactory.getParticipantDAO().findAll();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        //serialization
        String participantsJsonStr =  gson.toJson(participantsList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
