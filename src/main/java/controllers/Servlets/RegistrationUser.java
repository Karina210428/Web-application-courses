package controllers.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Student;
import controllers.entity.User;

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

        String login = jsonObject.get("login").toString();
        String password = jsonObject.get("password").toString();

        User user = new User();
        user.setLogin(login.substring(1,login.length()-1));
        user.setPassword(password.substring(1,password.length()-1));
        user.setIdRole(2);
        daoFactory.getUserDAO().create(user);
        user = daoFactory.getUserDAO().getUser(user.getLogin());

        String name = jsonObject.get("firstName").toString();
        String  surname = jsonObject.get("lastName").toString();

        Student student = new Student();
        student.setId_auth(user.getId());
        student.setFirstName(name.substring(1,name.length()-1));
        student.setLastName(surname.substring(1,surname.length()-1));
        daoFactory.getStudentDAO().create(student);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
