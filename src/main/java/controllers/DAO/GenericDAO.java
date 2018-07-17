package controllers.DAO;

import java.util.List;
/*
    интерфейс с реализаций CRUD методов
 */
public interface GenericDAO <T> {
    void create(T entity);
    void read(T entity);
    void update(T entity);
    void delete(T entity);
    T find(int id);
    List<T> findAll();
}
