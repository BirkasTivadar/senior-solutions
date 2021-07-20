package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomDaoIT {

    private MeetingRoomDao meetingRoomDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        meetingRoomDao = new MeetingRoomDao(entityManagerFactory);
    }

    @Test
    @DisplayName("Save three meetingrooms then query all")
    void testSaveAndGetOrderedNames() {

        MeetingRoom nagyTargyalo = meetingRoomDao.save("Nagytárgyaló", 5, 8);
        MeetingRoom kisTargyalo = meetingRoomDao.save("Kistárgyaló", 3, 4);
        MeetingRoom konferencia = meetingRoomDao.save("Konferencia", 7, 7);

        List<String> names = meetingRoomDao.getMeetingroomsOrderedByName();

        assertEquals("Kistárgyaló", names.get(0));


    }
}