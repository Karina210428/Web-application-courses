package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewPagination")
public class ViewPagination extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String spageId = request.getParameter("page");
        int pageid = Integer.parseInt(spageId);
        int total=2;
        if(pageid==1){

        }else{
            pageid = pageid-1;
            pageid=pageid*total+1;
        }
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<Participant> participantList = daoFactory.getParticipantDAO().getRecords(pageid,total);
        Gson gson = new Gson();
        //serialization
        //String participantsJsonStr =  gson.toJson(participantsListByLecturer);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-16");
       // response.getWriter().write(participantsJsonStr);
    }
}
