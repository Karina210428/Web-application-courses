package controllers.DAO;

import controllers.entity.Lecturer;

public interface LecturerDAO extends GenericDAO<Lecturer> {

    Lecturer getLectureByName(String name, String patronymic, String surname);

    Lecturer getLectureById(int id);

    Lecturer getLectureByIdAuth(int id);

}
