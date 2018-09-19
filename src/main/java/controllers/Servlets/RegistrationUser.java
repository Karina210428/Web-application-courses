package controllers.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;
import controllers.entity.Student;
import controllers.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrationUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
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
      //  JsonArray obj = gson.fromJson(str.toString(),JsonArray.class);

        JsonObject jsonObject = (JsonObject) jsonParser.parse(str.toString());

        String occupation =  jsonObject.get("occupation").toString();
        String login = jsonObject.get("login").toString();
        String password = jsonObject.get("password").toString();

        Users user = new Users();
        user.setOccupation(occupation.substring(1, occupation.length()-1));
        user.setLogin(login.substring(1,login.length()-1));
        user.setPassword(password.substring(1,password.length()-1));
        daoFactory.getUserDAO().create(user);
        user = daoFactory.getUserDAO().getUser(user.getLogin());

        if(user.getOccupation().equalsIgnoreCase("student")) {
            Student student = new Student();
            student.setFirstName(jsonObject.get("firstName").toString().substring(1,jsonObject.get("firstName").toString().length()-1));
            student.setLastName(jsonObject.get("lastName").toString().substring(1,jsonObject.get("lastName").toString().length()-1));
            student.setId_auth(user.getId());
            daoFactory.getStudentDAO().create(student);
           // student = daoFactory.getStudentDAO().getStudentByName(student.getFirstName(), student.getLastName());
        }else if (occupation=="teacher") {
            Lecturer lecturer = new Lecturer();
            lecturer.setName(jsonObject.get("firstName").toString());
            lecturer.setPatronymic(jsonObject.get("patronymic").toString());
            lecturer.setSurname(jsonObject.get("lastName").toString());
            lecturer.setId_auth(user.getId());
            daoFactory.getLecturerDAO().create(lecturer);
            //lecturer = daoFactory.getLecturerDAO().getLectureByName(lecturer.getName(), lecturer.getPatronymic(), lecturer.getSurname());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
