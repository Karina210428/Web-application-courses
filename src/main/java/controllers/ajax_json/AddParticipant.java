package controllers.ajax_json;

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

public class AddParticipant extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();
        Participant participant = gson.fromJson(json,Participant.class);
        System.out.println(participant.getComment());
        System.out.println(participant.getCourse());
        System.out.println(participant.getStudent());
        System.out.println(participant.getGrade());
        daoFactory.getParticipantDAO().create(participant);

        List<Participant> participantList = daoFactory.getParticipantDAO().findAll();
        String [] prtcpntJson = new String[1];
        prtcpntJson[0] = gson.toJson(participant.getId());
        prtcpntJson[1] = gson.toJson(participantList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String send = prtcpntJson[0];
        send = send.substring(0, send.length()-1);
        send+=",\"List\":" + prtcpntJson[0]+"}";
        response.getWriter().write(send);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
