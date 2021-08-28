package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

        EntityManager en = factory.createEntityManager();

        en.getTransaction().begin();

        en.persist(new Employee("John Doe"));

        en.getTransaction().commit();
    }
}
