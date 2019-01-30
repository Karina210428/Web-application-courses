package controllers.DAO;

import controllers.entity.Student;

public interface StudentDAO extends GenericDAO<Student> {

    Student getStudentByName(String firstName, String lastName);
    Student getStudentById(int id);
    Student getStudentByIdAuth(int id);
}
