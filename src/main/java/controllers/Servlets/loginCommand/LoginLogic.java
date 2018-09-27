package controllers.Servlets.loginCommand;

import controllers.DAO.DAOFactory;
import controllers.entity.Users;

import java.util.List;

public class LoginLogic {

    public static  boolean checkLogin(String enterLogin, String enterPass) {
        List<Users> usersList = DAOFactory.getDAOFactory().getUserDAO().findAll();
        boolean flag = false;
        for (Users user : usersList) {
            if (user.getLogin().equalsIgnoreCase(enterLogin)
                    && user.getPassword().equalsIgnoreCase(enterPass)) {
                return flag = true;
            }
        }
        return flag;
    }

    public static Users defineUser(String login, String password){
        return DAOFactory.getDAOFactory().getUserDAO().getUser(login);
    }
}
