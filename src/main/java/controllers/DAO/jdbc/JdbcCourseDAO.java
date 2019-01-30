package controllers.DAO.jdbc;

import controllers.DAO.CourseDAO;
import controllers.Servlets.loginCommand.ConfigurationManager;
import controllers.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseDAO extends JdbcGenericDAO<Course> implements CourseDAO {
    public static final String COURSE_COLUMN_ID = "idCourse";
    public static final String COURSE_COLUMN_NAME = "nameCourse";
    public static final String COURSE_COLUMN_LECTURER_ID = "lecturer_id";
	public static final String COURSE_COLUMN_STARTDATE = "startDate";
	public static final String COURSE_COLUMN_FINISHDATE = "finishDate";
	public static final String COURSE_COLUMN_ABOUT = "about";
	public static final String PARTICIPANT_LECTURER_NAME = "name";
	public static final String PARTICIPANT_LECTURER_SURNAME = "surname";
	public static final String PARTICIPANT_LECTURER_PATRONYMIC = "patronymic";
    
	@Override
	protected String getSelectQuery() {
		return ConfigurationManager.getInstance().getProperty("COURSE_SELECT_QUERY");
	}

	@Override
	protected String getCreateQuery() {
		return ConfigurationManager.getInstance().getProperty("COURSE_CREATE_QUERY");
	}

	@Override
	protected String getUpdateQuery() {
		return ConfigurationManager.getInstance().getProperty("COURSE_UPDATE_QUERY");
	}

	@Override
	protected String getDeleteQuery() {
		return ConfigurationManager.getInstance().getProperty("COURSE_DELETE_QUERY");
	}

	@Override
	protected void prepareStatementForCreate(PreparedStatement statement, Course entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setInt(2, entity.getLecturer().getId());
		statement.setString(3,entity.getStartDate());
		statement.setString(4,entity.getFinishDate());
		statement.setString(5,entity.getAboutCourse());
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Course entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setInt(2,entity.getLecturer().getId());
		statement.setInt(6,entity.getId());
		statement.setString(3,entity.getStartDate());
		statement.setString(4,entity.getFinishDate());
		statement.setString(5,entity.getAboutCourse());
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, Course entity) throws SQLException {
		statement.setInt(1,entity.getId());
	}

	@Override
	protected List<Course> parseResultSet(ResultSet rs) throws SQLException {

	    List<Course> res = new ArrayList<>();
            while( rs.next() ){
                try{
				   Course course = new Course();
				   course.setId(rs.getInt(COURSE_COLUMN_ID));
				   course.setName(rs.getString(COURSE_COLUMN_NAME));
                   course.setLecturer(JdbcDAOFactory.getFactory().getLecturerDAO().getLectureById(rs.getInt(COURSE_COLUMN_LECTURER_ID)));
                   course.setStartDate(rs.getString(COURSE_COLUMN_STARTDATE));
					course.setFinishDate(rs.getString(COURSE_COLUMN_FINISHDATE));
					course.setAboutCourse(rs.getString(COURSE_COLUMN_ABOUT));
                   res.add(course);
                   }catch(SQLException ex) {
            	       //Logger.getLogger(JdbcCourseDao.class.getName()).log(Level.ERROR, null, ex);
                      // throw ex;
                   } 
            }
        return res;
	}

	@Override
	public Course getCourseByName(String nameCourse) {
		List<Course> list;
		String sql = "SELECT * FROM course WHERE nameCourse = ? ";
		try (Connection connection = JdbcDAOFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, nameCourse);
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
	public Course getCourseById(int id) {
		List<Course> list = null;
		String sql = "SELECT * FROM course WHERE idCourse = ? ";
		try (Connection connection = JdbcDAOFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1,id);
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
	public List<Course> getCourseByLectureId(int id) {
		List<Course> list = null;
		String sql = "SELECT * FROM course WHERE lecturer_id = ? ";
		try (Connection connection = JdbcDAOFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			if (list == null || list.isEmpty()) {
				return null;
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
}
