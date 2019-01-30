package controllers.DAO;

import controllers.entity.Participant;

import java.util.List;

public interface ParticipantDAO extends GenericDAO<Participant>{
    List<Participant> getParticipantByPage(int pageId, int total);
    Participant getParticipantByCourseId(int id);
    List<Participant> getParticipantByStudentId(int id);
    Participant getParticipantById(int id);
    List<Participant> getRecords(int start, int total);
}
