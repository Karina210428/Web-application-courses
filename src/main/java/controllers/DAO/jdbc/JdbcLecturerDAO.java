package controllers.DAO.jdbc;

import controllers.DAO.LecturerDAO;
import controllers.Servlets.loginCommand.ConfigurationManager;
import controllers.entity.Lecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcLecturerDAO extends JdbcGenericDAO<Lecturer> implements LecturerDAO {
    
    public static final String LECTURER_COLUMN_ID = "idLecturer";
    public static final String LECTURER_COLUMN_NAME = "name";
    public static final String LECTURER_COLUMN_SURNAME = "surname";
	public static final String LECTURER_COLUMN_ID_AUTH = "id_authorization";
    
    @Override
    protected String getSelectQuery() {
		return ConfigurationManager.getInstance().getProperty("LECTURER_SELECT_QUERY");
	}

	@Override
	protected String getCreateQuery() {
		return ConfigurationManager.getInstance().getProperty("LECTURER_CREATE_QUERY");
	}

	@Override
	protected String getUpdateQuery() {
		return ConfigurationManager.getInstance().getProperty("LECTURER_UPDATE_QUERY");
	}

	@Override
	protected String getDeleteQuery() {
		return ConfigurationManager.getInstance().getProperty("LECTURER_DELETE_QUERY");
	}

	@Override
	protected void prepareStatementForCreate(PreparedStatement statement, Lecturer entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
		statement.setInt(3,entity.getId_auth());
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Lecturer entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
		statement.setInt(3, entity.getId());
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, Lecturer entity) throws SQLException {
		statement.setInt(1, entity.getId());
	}

	@Override
	protected List<Lecturer> parseResultSet(ResultSet rs) throws SQLException {
	    List<Lecturer> res = new ArrayList<Lecturer>();
	    while( rs.next() ){
		try {	
		    Lecturer lecturer = new Lecturer();
		    lecturer.setId(rs.getInt(LECTURER_COLUMN_ID));
                    lecturer.setName(rs.getString(LECTURER_COLUMN_NAME));
                    lecturer.setSurname(rs.getString(LECTURER_COLUMN_SURNAME));
                    lecturer.setId_auth(rs.getInt(LECTURER_COLUMN_ID_AUTH));
                    res.add(lecturer);
	            }catch(SQLException ex) {
        	     }
                }
        return res;
	}

	@Override
	public Lecturer getLectureByName(String name, String surname) {
		List<Lecturer> list;
		String sql = "SELECT * FROM lecturer WHERE name = ? AND surname = ?";
		try (Connection connection = JdbcDAOFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.setString(2,surname);
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

	public Lecturer getLectureById(int id) {
		List<Lecturer> list;
		String sql = "SELECT * FROM lecturer WHERE idLecturer =  ?";
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
	public Lecturer getLectureByIdAuth(int id) {
		List<Lecturer> list;
		String sql = "SELECT * FROM lecturer WHERE id_authorization =  ?";
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
