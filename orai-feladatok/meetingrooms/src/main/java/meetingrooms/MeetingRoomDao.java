package meetingrooms;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class MeetingRoomDao {

    private final EntityManagerFactory entityManagerFactory;

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

    List<String> getEverySecondMeetingRoom() {
        List<String> meetingRoomsNames = getMeetingroomsOrderedByName();
        return IntStream.range(0, meetingRoomsNames.size()).
                filter(i -> i % 2 != 0).
                mapToObj(meetingRoomsNames::get).collect(Collectors.toList());
    }

    List<MeetingRoom> getMeetingRooms() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = em
                .createQuery("select m from MeetingRoom m order by m.name", MeetingRoom.class)
                .getResultList();
        em.close();
        return meetingRooms;
    }

    List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = em
                .createQuery("select m from MeetingRoom m where m.name = :name", MeetingRoom.class)
                .setParameter("name", name)
                .getResultList();
        em.close();
        return meetingRooms;
    }

//    List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix);


    //    Bulk update-es megoldásra kicserélni
    public void deleteAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = getMeetingRooms();
        em.getTransaction().begin();
        for (MeetingRoom meetingRoom : meetingRooms) {
            em.remove(meetingRoom);
        }
        em.getTransaction().commit();
        em.close();
    }
}
