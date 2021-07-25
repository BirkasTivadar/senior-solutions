package spring.di;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveEmployee(String name) {
        entityManager.persist(new Employee(name));
    }

    public List<String> listEmployees() {
        return entityManager
                .createQuery("select e from Employee e order by e.name", Employee.class)
                .getResultStream()
                .map(Employee::getName)
                .collect(Collectors.toList());
    }
}
