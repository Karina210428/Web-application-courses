package controllers.DAO.jdbc;

import controllers.DAO.UserDAO;
import controllers.Servlets.loginCommand.ConfigurationManager;
import controllers.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDAO extends JdbcGenericDAO<User> implements UserDAO {

    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_LOGIN = "login";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_ID_ROLE = "idRole";
    @Override
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("USERS_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("USERS_CREATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("USERS_DELETE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("USERS_UPDATE_QUERY");
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {
        try {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3,entity.getIdRole());
            statement.setInt(4, entity.getId());
        }catch (Exception e){
        System.out.println(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, User entity) throws SQLException {
        try {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3,entity.getIdRole());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User entity) throws SQLException {
        statement.setInt(1,entity.getId());
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while( resultSet.next() ){
            try {
                User user = new User();
                user.setId(resultSet.getInt(USER_COLUMN_ID));
                user.setLogin(resultSet.getString(USER_COLUMN_LOGIN));
                user.setPassword(resultSet.getString(USER_COLUMN_PASSWORD));
                user.setIdRole(resultSet.getInt(USER_COLUMN_ID_ROLE));
                list.add(user);
            }catch(SQLException ex) {
                // Logger.getLogger(JdbcStudentDao.class.getName()).log(Level.ERROR, null, ex);
            }
        }
        return list;
    }

    @Override
    public User getUser(String login) {
        List<User> user;
        String sql = "SELECT * FROM users WHERE login = ? ";
        try (Connection connection = JdbcDAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs);
            if (user == null ) {
                return null;
            }
            return user.get(0);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        List<User> user;
        String sql = "SELECT * FROM users WHERE id = ? ";
        try (Connection connection = JdbcDAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            user = parseResultSet(rs);
            if (user == null ) {
                return null;
            }
            return user.get(0);
        } catch (SQLException e) {
            return null;
        }
    }
}
