package controllers.DAO.jdbc;

import controllers.DAO.UsersDAO;
import controllers.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUsersDAO extends JdbcGenericDAO<Users> implements UsersDAO {

    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_LOGIN = "login";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_OCCUPATION = "occupation";



    @Override
    protected String getSelectQuery() {
        return "Select * from users";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO users(login, password, occupation) VALUES (?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from users where id = ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "update users set login = ?, password = ?, occupation = ? where id = ?";
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Users entity) throws SQLException {
        try {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getOccupation());
            statement.setInt(4,entity.getId());
        }catch (Exception e){
        System.out.println(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Users entity) throws SQLException {
        try {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getOccupation());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Users entity) throws SQLException {
        statement.setInt(1,entity.getId());
    }

    @Override
    protected List<Users> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Users> list = new ArrayList<>();
        while( resultSet.next() ){
            try {
                Users user = new Users();
                user.setId(resultSet.getInt(USER_COLUMN_ID));
                user.setLogin(resultSet.getString(USER_COLUMN_LOGIN));
                user.setPassword(resultSet.getString(USER_COLUMN_PASSWORD));
                user.setOccupation(resultSet.getString(USER_COLUMN_OCCUPATION));
                list.add(user);
            }catch(SQLException ex) {
                // Logger.getLogger(JdbcStudentDao.class.getName()).log(Level.ERROR, null, ex);
            }
        }
        return list;
    }

    @Override
    public Users getUser(String login) {
        List<Users> user;
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
}
