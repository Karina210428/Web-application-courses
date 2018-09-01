package controllers.DAO.jdbc;

import controllers.DAO.ParticipantDAO;
import controllers.entity.Course;
import controllers.entity.Lecturer;
import controllers.entity.Participant;
import controllers.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcParticipantDAO extends JdbcGenericDAO<Participant> implements ParticipantDAO {

    public static final String PARTICIPANT_COLUMN_ID = "idparticipant";
    public static final String PARTICIPANT_COLUMN_GRADE = "grade";
    public static final String PARTICIPANT_COLUMN_COMMENT = "comment";
    public static final String PARTICIPANT_COLUMN_STUDENT_ID = "student";
    public static final String PARTICIPANT_COLUMN_COURSE_ID = "course";
    public static final String PARTICIPANT_LECTURER_ID = "lecturer_id";
    public static final String PARTICIPANT_LECTURER_NAME = "name";
    public static final String PARTICIPANT_LECTURER_SURNAME = "surname";
	public static final String PARTICIPANT_LECTURER_PATRONYMIC = "patronymic";

	@Override
	protected String getSelectQuery() {
		return "SELECT p.*, p.idparticipant AS id_p, c.*, s.*, "
				+ "l.name AS name, l.surname AS surname, l.patronymic AS patronymic"
				+ " FROM participant p "
				+ "LEFT JOIN course c ON c.idCourse = course "
				+ "LEFT JOIN student s ON s.idStudent = student "
				+ "LEFT JOIN lecturer l ON c.lecturer_id = l.idLecturer ";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO participant (course, student, grade, comment) VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE participant SET course = ?, student = ?, grade = ?, comment = ? WHERE idparticipant = ?";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM participant WHERE idparticipant = ?";
	}

	@Override
	protected void prepareStatementForCreate(PreparedStatement statement, Participant entity) throws SQLException {
		statement.setInt(1, entity.getCourse().getId());
		statement.setInt(2, entity.getStudent().getId());
		statement.setInt(3, entity.getGrade());
		statement.setString(4,entity.getComment());
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Participant entity) throws SQLException {
		statement.setInt(1, entity.getCourse().getId());
		statement.setInt(2, entity.getStudent().getId());
		statement.setInt(3, entity.getGrade());
		statement.setString(4,entity.getComment());
		statement.setInt(5,entity.getId());
		
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, Participant entity) throws SQLException {
		statement.setInt(1,entity.getId());
		
	}

	@Override
	protected List<Participant> parseResultSet(ResultSet rs) throws SQLException {
		List<Participant> res = new ArrayList<>();
        while( rs.next() ){
        	try{
        	    Participant paricipant = new Participant();
        	    paricipant.setId(rs.getInt(PARTICIPANT_COLUMN_ID));
        	    paricipant.setGrade(rs.getInt(PARTICIPANT_COLUMN_GRADE));
        	    paricipant.setComment(rs.getString(PARTICIPANT_COLUMN_COMMENT) );

        	    Student student = new Student();
		    student.setId(rs.getInt(PARTICIPANT_COLUMN_STUDENT_ID));
		    student.setFirstName(rs.getString(JdbcStudentDAO.STUDENT_COLUMN_FIRST_NAME));
		    student.setLastName(rs.getString(JdbcStudentDAO.STUDENT_COLUMN_LAST_NAME));
		    paricipant.setStudent(student);
				
		    Course course = new Course();
	            course.setId(rs.getInt(PARTICIPANT_COLUMN_COURSE_ID));
	            course.setName(rs.getString(JdbcCourseDAO.COURSE_COLUMN_NAME));
	        	
	            Lecturer lecturer = new Lecturer();
	            lecturer.setId(rs.getInt(PARTICIPANT_LECTURER_ID));
	            lecturer.setName(rs.getString(PARTICIPANT_LECTURER_NAME));
	            lecturer.setSurname(rs.getString(PARTICIPANT_LECTURER_SURNAME));
	            lecturer.setPatronymic(rs.getString(JdbcLecturerDAO.LECTURER_COLUMN_PATRONYMIC));
	            course.setLecturer(lecturer);
	            paricipant.setCourse(course);
                    res.add(paricipant);
                    }catch(SQLException ex) {
        		throw ex;
                    } 
                }
        return res;
	}

   /* @Override
    public List<Participant> getParticipantByCourseId(int courseId) {
        List<Participant> list;
        String sql = "SELECT p.*, p.id AS id_p, c.*, s.*, "
		           + "l.first_name AS fname, l.last_name AS lname, l.password"
		           + " FROM participant p "
		           + "LEFT JOIN course c ON c.id = course_id "
		           + "LEFT JOIN student s ON s.id = student_id "
		           + "LEFT JOIN lecturer l ON c.lecturer_id = l.id WHERE course_id = ?";
        try (Connection connection = JdbcDaoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;
        } catch (SQLException e) {
            Logger.getLogger(com.chernyak.dao.jdbc.JdbcParticipantDAO.class.getName()).log(Level.ERROR, null, e);
            return null;
        } 
    }*/
	
}
