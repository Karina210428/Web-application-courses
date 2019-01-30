package controllers.DAO;

import controllers.entity.Course;

import java.util.List;

public interface CourseDAO extends GenericDAO<Course> {

    Course getCourseByName(String nameCourse);
    Course getCourseById(int id);
    List<Course> getCourseByLectureId(int id);
}
