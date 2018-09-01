package controllers.DAO.jdbc;

import controllers.DAO.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDAOFactory extends DAOFactory {

    public static JdbcDAOFactory instance;
    private static DataSource dataSource;

    private static String user = "user";//Логин пользователя
    private static String password = "1";//Пароль пользователя
    private static String url = "jdbc:mysql://localhost:3306/Electives";//URL адрес
    private static String driver = "com.mysql.jdbc.Driver";//Имя драйвера


    private JdbcDAOFactory() {

    }

    public static synchronized JdbcDAOFactory getFactory(){
        if(instance==null) instance = new JdbcDAOFactory();
        return instance;
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/electives",
                "root",
                "root");
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new JdbcStudentDAO();
    }

    @Override
    public CourseDAO getCourseDAO() {
        return new JdbcCourseDAO();
    }

    @Override
    public LecturerDAO getLecturerDAO() {
        return new JdbcLecturerDAO();
    }

    @Override
    public ParticipantDAO getParticipantDAO() {
        return new JdbcParticipantDAO();
    }

    @Override
    public UsersDAO getUserDAO() {
        return new JdbcUsersDAO();
    }
}
