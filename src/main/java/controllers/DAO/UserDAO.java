package controllers.DAO;

import controllers.entity.User;

public interface UserDAO extends GenericDAO<User> {

    User getUser(String login);
    User getUserById(int id);
}