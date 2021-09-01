package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ParkingPlaceDao {

    private EntityManagerFactory factory;

    public ParkingPlaceDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveParkingPlace(ParkingPlace parkingPlace) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(parkingPlace);
        em.getTransaction().commit();
        em.close();
    }

    public ParkingPlace findParkingPlaceByNumber(int number) {
        EntityManager em = factory.createEntityManager();
        ParkingPlace parkingPlace = em
                .createQuery("select  p from ParkingPlace p where p.number = :number", ParkingPlace.class)
                .setParameter("number", number)
                .getSingleResult();
        em.close();
        return parkingPlace;
    }
}
