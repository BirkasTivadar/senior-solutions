package inheritance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EmployeeInheritanceDao {

    private EntityManagerFactory factory;

    public EmployeeInheritanceDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveEmployeeInheritance(EmployeeInheritance employeeInheritance) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employeeInheritance);
        em.getTransaction().commit();
        em.close();
    }

    public EmployeeInheritance findEmployeeInheritanceByName(String name) {
        EntityManager em = factory.createEntityManager();
        EmployeeInheritance employeeInheritance = em
                .createQuery("select e from EmployeeInheritance e where e.name = :name", EmployeeInheritance.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return employeeInheritance;
    }
}
