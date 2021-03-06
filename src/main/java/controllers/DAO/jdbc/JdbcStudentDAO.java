package controllers.DAO.jdbc;

import controllers.DAO.StudentDAO;
import controllers.Servlets.loginCommand.ConfigurationManager;
import controllers.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDAO extends JdbcGenericDAO<Student> implements StudentDAO {

    public static final String STUDENT_COLUMN_ID = "idStudent";
    public static final String STUDENT_COLUMN_FIRST_NAME = "first_name";
    public static final String STUDENT_COLUMN_LAST_NAME = "last_name";
    public static final String STUDENT_COLUMN_ID_AUTH = "id_auth";

    @Override
    protected String getSelectQuery() {
        return ConfigurationManager.getInstance().getProperty("STUDENT_SELECT_QUERY");
    }

    @Override
    protected String getCreateQuery() {
        return ConfigurationManager.getInstance().getProperty("STUDENT_CREATE_QUERY");
    }

    @Override
    protected String getUpdateQuery() {
        return ConfigurationManager.getInstance().getProperty("STUDENT_UPDATE_QUERY");
    }

    @Override
    protected String getDeleteQuery() {
        return ConfigurationManager.getInstance().getProperty("STUDENT_DELETE_QUERY");
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Student entity) throws SQLException {
        try{
            statement.setString(1,entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3,entity.getId());
            statement.setInt(3,entity.getId_auth());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Student entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3,entity.getId_auth());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Student entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }

    @Override
    protected List<Student> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Student> list = new ArrayList<>();
        while( resultSet.next() ){
            try {
                Student student = new Student();
                student.setId(resultSet.getInt(STUDENT_COLUMN_ID));
                student.setFirstName(resultSet.getString(STUDENT_COLUMN_FIRST_NAME));
                student.setLastName(resultSet.getString(STUDENT_COLUMN_LAST_NAME));
                student.setId_auth(resultSet.getInt(STUDENT_COLUMN_ID_AUTH));
                list.add(student);
            }catch(SQLException ex) {
               // Logger.getLogger(JdbcStudentDao.class.getName()).log(Level.ERROR, null, ex);
            }
        }
        return list;
    }

    @Override
    public Student getStudentByName(String firstName, String lastName) {
        List<Student> list;
        String sql = "SELECT * FROM student WHERE first_name = ? AND last_name = ?";
        try (Connection connection = JdbcDAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Student getStudentById(int id) {
        List<Student> list;
        String sql = "SELECT * FROM student WHERE idStudent = ?";
        try (Connection connection = JdbcDAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Student getStudentByIdAuth(int id) {
        List<Student> list;
        String sql = "SELECT * FROM student WHERE id_auth =  ?";
        try (Connection connection = JdbcDAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (SQLException e) {
            return null;
        }
    }
}
