package controllers.DAO.jdbc;

import controllers.DAO.GenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcGenericDAO<T> implements GenericDAO<T> {
    public JdbcGenericDAO(){
    }

    /*
     * Возвращает sql запрос для получения всех записей
     * select * from [Table]
     */
    protected abstract  String getSelectQuery();
    /*
     * Возвращает sql запрос для вствки новой записи
     * insert into [Table] ([col1], [col2]...) values (?,?,...)
     */
    protected abstract  String getCreateQuery();
    /*
     * Возвращает sql запрос для удаления записи из БД
     * delete from [Table] where id = ... ;
     */
    protected abstract String getDeleteQuery();
    /*
     * Возвращает sql запрос для обновления записи
     * update[Table] set [col1=..., col2=..., ...] where id = ...;
     */
    protected abstract String getUpdateQuery();


    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T entity) throws SQLException;

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public void create(T entity) {
        String sql = getCreateQuery();
        try(Connection connection = JdbcDAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForCreate(statement,entity);
            if(statement.executeUpdate()>1){
                System.out.println("добавление произошло успешно");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(T entity) {

    }

    @Override
    public void update(T entity) {
        String sql = getUpdateQuery();
        try(Connection connection = JdbcDAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForUpdate(statement, entity);
            if(statement.executeUpdate()>1){
                System.out.println("обновление произошло успешно");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        String sql = getDeleteQuery();
        try(Connection connection = JdbcDAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForDelete(statement, entity);
            if(statement.executeUpdate()>1){
                System.out.println("удаление произошло успешно");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

      @Override
    public T find(int id) {
        List<T> list = null;
        String sql = getSelectQuery();
        sql+= "where id = "+id+"";
        try(Connection connection = JdbcDAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.isEmpty() || list.size()==0){
            return null;
        }
        return  list.iterator().next();
    }

    @Override
    public List<T> findAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        try(Connection connection = JdbcDAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
