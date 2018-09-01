package controllers.DAO;

import controllers.DAO.jdbc.JdbcDAOFactory;

public abstract class DAOFactory {
    public abstract StudentDAO getStudentDAO();
    public abstract LecturerDAO getLecturerDAO();
    public abstract CourseDAO getCourseDAO();
    public abstract ParticipantDAO getParticipantDAO();
    public abstract UsersDAO getUserDAO();

    public static DAOFactory getDAOFactory(){
        return JdbcDAOFactory.getFactory();
    }

}
