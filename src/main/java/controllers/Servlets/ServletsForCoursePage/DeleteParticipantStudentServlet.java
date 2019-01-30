package controllers.Servlets.ServletsForCoursePage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Participant;
import controllers.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteParticipantStudentServlet")
public class DeleteParticipantStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br!=null){
            json = br.readLine();
        }
        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(json.toString());

        int index = jsonObject.get("id").getAsInt();
        List<Participant> participantsList = daoFactory.getParticipantDAO().findAll();
        HttpSession session = request.getSession(false);
        Student student = (Student) session.getAttribute("student");
        List<Participant> participantsListByStudent = new ArrayList<Participant>();
        for (Participant p: participantsList) {
            if(p.getStudent().equals(student)) {
                participantsListByStudent.add(p);
            }
        }
        daoFactory.getParticipantDAO().delete(participantsListByStudent.get(index));
        participantsListByStudent.remove(index);
        String participantsJsonStr = gson.toJson(participantsListByStudent);
        response.getWriter().write(participantsJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
