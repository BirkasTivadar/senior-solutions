package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
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

    public Employee findEmployeeByIdWithNicknames(long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em.createQuery("select e from Employee e join fetch e.nicknames where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
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

    public Employee findEmployeeByIdWithNicknamesVacations(Long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.vacationBookings where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }

    public Employee findEmployeeByIdWithPhoneNumbers(Long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.phoneNumbers where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }

    public void addPhoneNumber(Long id, PhoneNumber phoneNumber) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.getReference(Employee.class, id);
        phoneNumber.setEmployee(employee);
        em.persist(phoneNumber);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findEmployeeByIdWithProjects(Long id) {
        EntityManager em = factory.createEntityManager();
        Employee employee = em
                .createQuery("select e from Employee e join fetch e.projects where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return employee;
    }

    public Employee findEmployeeByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp).where(cb.equal(emp.get("name"), name));
        Employee employee = entityManager.createQuery(c).getSingleResult();
        entityManager.close();
        return employee;
    }

    public List<Employee> listEmployees(int start, int maxResult) {
        EntityManager entityManager = factory.createEntityManager();
        List<Employee> employees = entityManager
                .createNamedQuery("listEmployees", Employee.class)
                .setFirstResult(start)
                .setMaxResults(maxResult)
                .getResultList();
        entityManager.close();
        return employees;
    }

    public int findParkingPlaceNumberByEmployeeName(String name) {
        EntityManager em = factory.createEntityManager();
        int i = em
                .createQuery("select p.number from Employee e join e.parkingPlace p where e.name = :name", Integer.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return i;
    }

    public List<Object[]> listEmployeeBaseData() {
        EntityManager em = factory.createEntityManager();
        List<Object[]> empDatas = em
                .createQuery("select e.id, e.name from Employee e")
                .getResultList();
        em.close();
        return empDatas;
    }

    public List<EmpBaseDataDto> listEmployeeDto() {
        EntityManager em = factory.createEntityManager();
        List<EmpBaseDataDto> data = em
                .createQuery("select new employees.EmpBaseDataDto(e.id, e.name) from Employee e order by e.name")
                .getResultList();
        em.close();
        return data;
    }

    public void updateToType(LocalDate start, Employee.EmployeeType type) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("update Employee e set e.employeeType = :type where e.dateOfBirth >= :start")
                .setParameter("type", type)
                .setParameter("start", start)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteWithoutType() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete Employee e where e.employeeType is null")
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
