package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeeDao {

    private final EntityManagerFactory factory;

    public EmployeeDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void save(Employee employee) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findById(Long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();
        return employee;
    }

    public List<Employee> listAll() {
        EntityManager em = factory.createEntityManager();
        List<Employee> employees = em.createQuery("select e from Employee e order by e.name", Employee.class)
                .getResultList();
        em.close();
        return employees;
    }

    public void changeName(Long id, String name) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        employee.setName(name);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.getReference(Employee.class, id);
        em.remove(employee);
        em.getTransaction().commit();
        em.close();
    }

    //    merge metódus nem javasolt
    public void updateEmployee(Employee employee) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Employee merged = em.merge(employee);

        merged.setName("***" + employee.getName());
        em.getTransaction().commit();
        em.close();
    }

    public void updateEmployeeNames() {
        EntityManager em = factory.createEntityManager();
        List<Employee> employees = em.createQuery("select e from Employee e order by e.name", Employee.class)
                .getResultList();

        em.getTransaction().begin();

        for (Employee employee : employees) {
            employee.setName(employee.getName() + "***");
            System.out.println("Módosítva");
        }

        em.flush();

        em.getTransaction().commit();
        em.close();
    }
}
