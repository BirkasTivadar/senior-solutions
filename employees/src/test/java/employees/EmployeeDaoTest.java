package employees;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    public void init() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

        employeeDao = new EmployeeDao(entityManagerFactory);

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();
    }

    @Test
    void testSaveThenFindId() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        Employee another = employeeDao.findById(id);

        assertEquals("John Doe", another.getName());
    }

    @Test
    void testSaveThenListAll() {
        employeeDao.save(new Employee("John Doe"));
        employeeDao.save(new Employee("Jane Doe"));

        List<Employee> employees = employeeDao.listAll();

        assertEquals(2, employees.size());
        assertEquals(List.of("Jane Doe", "John Doe"), employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();
        employeeDao.changeName(id, "Jane Doe");
        employee = employeeDao.findById(id);

        assertEquals("Jane Doe", employee.getName());
    }

    @Test
    void testDelete(){
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();

        assertTrue(employees.isEmpty());
    }


}