package controllers.Servlets;

import com.google.gson.Gson;
import controllers.DAO.DAOFactory;
import controllers.entity.Lecturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteProfile extends HttpServlet {
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

        HttpSession session = request.getSession(true);
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
        daoFactory.getLecturerDAO().delete(lecturer);
        response.getWriter().write(""+ lecturer.getName()+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
