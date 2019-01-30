package controllers.Servlets.servletsForAdmin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Student;
import controllers.entity.User;
import controllers.entity.Lecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "ServletChangeRole")
public class ServletChangeRole extends HttpServlet {
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

        HttpSession session = request.getSession(false);
        User user  = (User) session.getAttribute("updateUserRole");

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(json.toString());

        int id = jsonObject.get("index").getAsInt();
        user.setIdRole(id);
        daoFactory.getUserDAO().update(user);

        Student student = daoFactory.getStudentDAO().getStudentByIdAuth(user.getId());
        Lecturer lecturer = new Lecturer();
        lecturer.setName(student.getFirstName());
        lecturer.setSurname(student.getLastName());
        lecturer.setId_auth(student.getId_auth());
        daoFactory.getLecturerDAO().create(lecturer);
        daoFactory.getStudentDAO().delete(student);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
