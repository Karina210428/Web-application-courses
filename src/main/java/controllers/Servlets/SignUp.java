package controllers.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.DAO.DAOFactory;
import controllers.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@WebServlet(name = "SignUp")
public class SignUp extends HttpServlet {
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
        JsonObject jsonObject = (JsonObject) jsonParser.parse(str.toString());

        String occupation = "";
        String login = jsonObject.get("login").toString();
        String password = jsonObject.get("password").toString();
        Boolean flag = false;

        List<Users> usersList = daoFactory.getUserDAO().findAll();

        for (Users user:usersList) {
            if(user.getLogin().equalsIgnoreCase(login.substring(1,login.length()-1))
                    && user.getPassword().equalsIgnoreCase(password.substring(1,password.length()-1))){
                occupation=user.getOccupation();
                flag=true;
            }else flag=false;
        }

        JsonObject jsonObjectPut = new JsonObject();

        if(flag==true && occupation.equalsIgnoreCase("student")){
            jsonObjectPut.addProperty("flag",true);
            jsonObjectPut.addProperty("occupation","student");
        }else if(flag==true && occupation.equalsIgnoreCase("teacher")){
            jsonObjectPut.addProperty("flag",true);
            jsonObjectPut.addProperty("occupation","teacher");
        }else if(flag==false){
            jsonObjectPut.addProperty("flag",false);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObjectPut.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
