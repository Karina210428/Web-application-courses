package controllers.DAO;

import controllers.entity.Users;

public interface UsersDAO extends GenericDAO<Users> {

    Users getUser(String login);
}