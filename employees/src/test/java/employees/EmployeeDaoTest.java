package employees;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    public void init() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

        employeeDao = new EmployeeDao(entityManagerFactory);

//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
//        dataSource.setUser("employees");
//        dataSource.setPassword("employees");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//
//        flyway.clean();
//        flyway.migrate();
    }

    @Test
    void testSaveThenFindId() {
//        Employee employee = new Employee("John Doe");

        Employee employee = new Employee("x", 1L, "John Doe");

        employeeDao.save(employee);

//        long id = employee.getId();
//        Employee another = employeeDao.findById(id);

        Employee another = employeeDao.findById("x", 1L);

        assertEquals("John Doe", another.getName());
    }

    @Test
    void testSaveThenListAll() {
//        employeeDao.save(new Employee("John Doe"));
//        employeeDao.save(new Employee("Jane Doe"));

        employeeDao.save(new Employee("x", 1L, "John Doe"));
        employeeDao.save(new Employee("x", 2L, "Jane Doe"));

        List<Employee> employees = employeeDao.listAll();

        assertEquals(2, employees.size());
        assertEquals(List.of("Jane Doe", "John Doe"), employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
//        Employee employee = new Employee("John Doe");
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

//        long id = employee.getId();
//        employee = employeeDao.findById(id);
//        employeeDao.changeName(id,"Jane Doe");
//        Employee another = employeeDao.findById(id);

        employeeDao.changeName("x", 1L, "Jane Doe");
        Employee another = employeeDao.findById("x", 1L);

        assertEquals("Jane Doe", another.getName());
    }

    @Test
    void testDelete() {
//        Employee employee = new Employee("John Doe");

        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

//        long id = employee.getId();
//        employeeDao.delete(id);

        employeeDao.delete("x", 1L);

        List<Employee> employees = employeeDao.listAll();

        assertTrue(employees.isEmpty());
    }

    @Test
    void testIllegalId() {

//        Employee employee = employeeDao.findById(12L);

        Employee employee = employeeDao.findById("x", 12L);
        assertEquals(null, employee);
    }

    @Test
    void testEmployeeWithAttributes() {
//        for (int i = 0; i < 10; i++) {
//            employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME,
        for (long i = 0; i < 10; i++) {
            employeeDao.save(new Employee("x", i, "John Doe", Employee.EmployeeType.HALF_TIME,
                    LocalDate.of(2000, 1, 1)));
        }

        Employee employee = employeeDao.listAll().get(0);

        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }
}