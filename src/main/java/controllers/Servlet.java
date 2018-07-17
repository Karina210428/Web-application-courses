package controllers;

import controllers.DAO.DAOFactory;
import controllers.DAO.ParticipantDAO;
import controllers.entity.Course;
import controllers.entity.Lecturer;
import controllers.entity.Participant;
import controllers.entity.Student;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class Servlet extends javax.servlet.http.HttpServlet {

    private DAOFactory daoFactory;

    public Servlet(){
        daoFactory = DAOFactory.getDAOFactory();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        String comment = request.getParameter("comment");
        String idParticipantString = request.getParameter("Id");// id записи

        int grade = Integer.parseInt(request.getParameter("grade"));// оценка за учебу

        Participant participantHelper = new Participant();
        if(idParticipantString != null ) {
            participantHelper = daoFactory.getParticipantDAO().find(Integer.parseInt(idParticipantString));
        }

        Student student = new Student();
        student.setFirstName(request.getParameter("nameStudent"));
        student.setLastName(request.getParameter("lastNameStudent"));

        if(idParticipantString == null || idParticipantString.isEmpty()) {
            daoFactory.getStudentDAO().create(student);
            student = daoFactory.getStudentDAO().getStudentByName(student.getFirstName(), student.getLastName());
        }else {
            //получаем ид студента (идем по ид и получаем ид найденной записи)
            student.setId(participantHelper.getStudent().getId());
            daoFactory.getStudentDAO().update(student);
        }
        Lecturer lecturer = new Lecturer();
        lecturer.setName(request.getParameter("nameLecture"));
        lecturer.setPatronymic(request.getParameter("patronymicLecture"));
        lecturer.setSurname(request.getParameter("surnameLecture"));

        if(idParticipantString == null || idParticipantString.isEmpty()) {
            daoFactory.getLecturerDAO().create(lecturer);
            lecturer = daoFactory.getLecturerDAO().getLectureByName(lecturer.getName(), lecturer.getPatronymic(), lecturer.getSurname());
        }else{
            lecturer.setId(participantHelper.getCourse().getLecturer().getId());
            daoFactory.getLecturerDAO().update(lecturer);
        }
        Course course = new Course();
        course.setName(request.getParameter("nameCourse"));
        course.setLecturer(lecturer);

        if(idParticipantString == null || idParticipantString.isEmpty()) {
            daoFactory.getCourseDAO().create(course);
            course = daoFactory.getCourseDAO().getCourseByName(course.getName());
        }else{
            course.setId(participantHelper.getCourse().getId());
            daoFactory.getCourseDAO().update(course);
        }
        ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
        Participant participant = new Participant();
        participant.setStudent(student);
        participant.setGrade(grade);
        participant.setComment(comment);
        participant.setCourse(course);
        if(idParticipantString == null || idParticipantString.isEmpty()){
            participantDAO.create(participant);
        }else if(Integer.parseInt(idParticipantString) == participantHelper.getId()){

            participant.setId(Integer.parseInt(idParticipantString));
            daoFactory.getParticipantDAO().update(participant);
        }
        RequestDispatcher view = request.getRequestDispatcher("/listParticipant.jsp");
        request.setAttribute("participants", daoFactory.getParticipantDAO().findAll());
        view.forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String forward = "";
        String action = request.getParameter("action");
        Participant participant;
        if(action.equalsIgnoreCase("showList")){
            forward="/listParticipant.jsp";
            request.setAttribute("participants",daoFactory.getParticipantDAO().findAll());
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("Id"));
            participant= daoFactory.getParticipantDAO().find(id);
            daoFactory.getParticipantDAO().delete(participant);
            forward="/listParticipant.jsp";
            request.setAttribute("participants",daoFactory.getParticipantDAO().findAll());
        }else if(action.equalsIgnoreCase("insert")){
            forward="/insertParticipant.jsp";
        }else if(action.equalsIgnoreCase("update")){
            forward="/updateParticipant.jsp";
            int id = Integer.parseInt(request.getParameter("Id"));
            participant = daoFactory.getParticipantDAO().find(id);
            request.setAttribute("participant",participant);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request,response);
    }
}
