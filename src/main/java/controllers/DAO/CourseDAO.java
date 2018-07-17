package controllers.DAO;

import controllers.entity.Course;

public interface CourseDAO extends GenericDAO<Course> {

    Course getCourseByName(String nameCourse);
}
