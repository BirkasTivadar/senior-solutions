package employees;

//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
//        dataSource.setUser("employees");
//        dataSource.setPassword("employees");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.clean();
//        flyway.migrate();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(factory);
    }

    @Test
    void testSaveThenFind() {
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
        assertEquals("Jane Doe", employees.get(0).getName());

        assertEquals(List.of("Jane Doe", "John Doe"), employees.stream().map(Employee::getName).toList());
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.changeName(id, "Jack Doe");

        assertEquals("Jack Doe", employeeDao.findById(id).getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();

        assertTrue(employees.isEmpty());
    }

    @Test
    void testEmployeeWithAttributes() {
        employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 1, 1)));

        Employee employee = employeeDao.listAll().get(0);

        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }
}