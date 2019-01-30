package controllers.DAO.jdbc;

import controllers.DAO.ParticipantDAO;
import controllers.Servlets.loginCommand.ConfigurationManager;
import controllers.entity.Participant;

import java.sql.Connection;
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
		return ConfigurationManager.getInstance().getProperty("PARTICIPANT_SELECT_QUERY");
	}

	@Override
	protected String getCreateQuery() {
		return ConfigurationManager.getInstance().getProperty("PARTICIPANT_CREATE_QUERY");
	}

	@Override
	protected String getUpdateQuery() {
		return ConfigurationManager.getInstance().getProperty("PARTICIPANT_UPDATE_QUERY");
	}

	@Override
	protected String getDeleteQuery() {
		return ConfigurationManager.getInstance().getProperty("PARTICIPANT_DELETE_QUERY");
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
			try {
				Participant participant = new Participant();
				participant.setId(rs.getInt(PARTICIPANT_COLUMN_ID));
				participant.setGrade(rs.getInt(PARTICIPANT_COLUMN_GRADE));
				participant.setComment(rs.getString(PARTICIPANT_COLUMN_COMMENT) );
				participant.setStudent(JdbcDAOFactory.getFactory().getStudentDAO().getStudentById(rs.getInt(JdbcParticipantDAO.PARTICIPANT_COLUMN_STUDENT_ID)));
				participant.setCourse(JdbcDAOFactory.getFactory().getCourseDAO().getCourseById(rs.getInt(JdbcParticipantDAO.PARTICIPANT_COLUMN_COURSE_ID)));
				res.add(participant);

			}
			catch(SQLException ex) {
				throw ex;
			}
	}
	return res;
	}

	@Override
	public List<Participant> getParticipantByPage(int pageId, int total) {
		List<Participant> res = new ArrayList<>();
		String sql= "select * from participant limit " +(pageId-1)+ " , " +total;
		try (Connection connection = JdbcDAOFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			ResultSet resultSet = preparedStatement.executeQuery();
			res = parseResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Participant getParticipantByCourseId(int id) {
		List<Participant> list = null;
		String sql = "SELECT * FROM participant WHERE course = ? ";
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
    public List<Participant> getParticipantByStudentId(int id) {
        List<Participant> list = null;
        String sql = "SELECT * FROM participant WHERE student = ? ";
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

    @Override
	public Participant getParticipantById(int id) {
		List<Participant> list = null;
		String sql = "SELECT * FROM participant WHERE idparticipant = ? ";
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
	public List<Participant> getRecords(int start, int total) {
		List<Participant> list = null;
		String sql = "SELECT * FROM participant limit " + (start-1) + "," + total+"";
		try (Connection connection = JdbcDAOFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			//statement.setInt(1,id);
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
