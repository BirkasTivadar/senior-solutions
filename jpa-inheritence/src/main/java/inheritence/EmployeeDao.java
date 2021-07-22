package inheritence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EmployeeDao {

    private EntityManagerFactory factory;

    public EmployeeDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveEmployee(Employee employee) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeByName(String name) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e where e.name = :name", Employee.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return employee;
    }
}
