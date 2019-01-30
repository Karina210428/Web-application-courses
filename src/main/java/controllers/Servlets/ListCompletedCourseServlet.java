package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;
import controllers.entity.Participant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ListCompletedCourseServlet")
public class ListCompletedCourseServlet extends HttpServlet {
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
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format1.parse(p.getCourse().getFinishDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //date.compareTo()
                Date now = new Date();
                if(now.compareTo(date)==-1){
                    participantsListByLecturer.add(p);
                }
            }
        }
        Gson gson = new Gson();
        String participantsJsonStr =  gson.toJson(participantsListByLecturer);
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
