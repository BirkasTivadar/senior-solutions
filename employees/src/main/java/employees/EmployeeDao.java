package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeeDao {

    private final EntityManagerFactory entityManagerFactory;

    public EmployeeDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public void updateEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee merged = em.merge(employee);

//        employee.setName("***"+employee.getName());
        merged.setName("***" + employee.getName());

        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeById(Long id) {
//    public Employee findById(String depName, Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em.find(Employee.class, id);
//        Employee employee = em.find(Employee.class, new EmployeeId(depName, id));
        em.close();
        return employee;
    }

    public Employee findEmployeeByIdWithNickNames(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.nicknames where id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }

    public List<Employee> listEmployees() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> employees = em
                .createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
        em.close();
        return employees;
    }

    public void updateEmployeeNames() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> employees = em
                .createQuery("select e from Employee e order by e.name", Employee.class).getResultList();

        em.getTransaction().begin();

        for (Employee employee : employees) {
            employee.setName(employee.getName() + "***");
            System.out.println("Módosítva");
            em.flush();
        }

        em.getTransaction().commit();
        em.close();
    }

    public void updateEmployeeName(Long id, String name) {
//    public void changeName(String depName, Long id, String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
//        Employee employee = em.find(Employee.class, new EmployeeId(depName, id));
        employee.setName(name);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteEmployee(Long id) {
//    public void delete(String depName, Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.getReference(Employee.class, id);
//        Employee employee = em.getReference(Employee.class, new EmployeeId(depName, id));
        em.remove(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeByIdWithVacations(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.vacationBookings where id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }

//    Map<String, String>-es verzió
//    public Employee findEmployeeByIdWithPhoneNumbers(Long id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        Employee employee = em
//                .createQuery("select e from Employee e join fetch e.phoneNumbers where id = :id", Employee.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        em.close();
//        return employee;
//    }


    public void addPhoneNumber(Long id, PhoneNumber phoneNumber) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
//        Employee employee = em.find(Employee.class, id);
        Employee employee = em.getReference(Employee.class, id);
        phoneNumber.setEmployee(employee);
        em.persist(phoneNumber);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeByIdWithPhoneNumbers(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.phoneNumbers where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }


//    Nem ajánlott, mert nyitva marad persist

//    private EntityManager entityManager;
//
//    public EmployeeDao(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public void saveEmployee(Employee employee) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(employee);
//        entityManager.getTransaction().commit();
//    }
//
//    public Employee findEmployeeById(Long id) {
//        return entityManager.find(Employee.class, id);
//    }
//
//    public List<Employee> listEmployees() {
//        return entityManager.createQuery("select e from Employee e order by e.name", Employee.class).getResultList();
//    }
//
//    public void updateEmployeeName(Long id, String name) {
//        entityManager.getTransaction().begin();
//        Employee employee = entityManager.find(Employee.class, id);
//        employee.setName(name);
//        entityManager.getTransaction().commit();
//    }
//
//    public void deleteEmployee(Long id) {
//        entityManager.getTransaction().begin();
//        Employee employee = entityManager.find(Employee.class, id);
//        entityManager.remove(employee);
//        entityManager.getTransaction().commit();
//    }

}
