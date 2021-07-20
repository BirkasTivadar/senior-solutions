package meetingrooms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MeetingRoomDao {

    private final EntityManagerFactory entityManagerFactory;

    public MeetingRoomDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public MeetingRoom save(String name, int width, int length) {
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    public List<String> getMeetingroomsOrderedByName() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> names = em
                .createQuery("select m.name from MeetingRoom m order by m.name", String.class)
                .getResultList();
        em.close();
        return names;

    }

//    List<String> getEverySecondMeetingRoom();
//
//    List<MeetingRoom> getMeetingRooms();
//
//    List<MeetingRoom> getExactMeetingRoomByName(String name);
//
//    List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix);
//
//    void deleteAll();
}
