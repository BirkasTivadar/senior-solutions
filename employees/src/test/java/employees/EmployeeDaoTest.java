package employees;

//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import javax.persistence.EntityManager;
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
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        employeeDao = new EmployeeDao(entityManagerFactor);

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
        Employee employee = new Employee("John Doe");

//        Employee employee = new Employee("x", 1L, "John Doe");

        employeeDao.saveEmployee(employee);

        long id = employee.getId();
        Employee another = employeeDao.findEmployeeById(id);

//        Employee another = employeeDao.findById("x", 1L);

        assertEquals("John Doe", another.getName());
    }

    @Test
    void testSaveThenListAll() {
        employeeDao.saveEmployee(new Employee("John Doe"));
        employeeDao.saveEmployee(new Employee("Jane Doe"));

//        employeeDao.save(new Employee("x", 1L, "John Doe"));
//        employeeDao.save(new Employee("x", 2L, "Jane Doe"));

        List<Employee> employees = employeeDao.listEmployees();

        assertEquals(2, employees.size());
        assertEquals(List.of("Jane Doe", "John Doe"), employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Doe");

//        Employee employee = new Employee("x", 1L, "John Doe");

        employeeDao.saveEmployee(employee);

        long id = employee.getId();
        employeeDao.updateEmployeeName(id, "Jane Doe");
        Employee another = employeeDao.findEmployeeById(id);

//        employeeDao.changeName("x", 1L, "Jane Doe");
//        Employee another = employeeDao.findById("x", 1L);

        assertEquals("Jane Doe", another.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe");

//        Employee employee = new Employee("x", 1L, "John Doe");

        employeeDao.saveEmployee(employee);

        long id = employee.getId();
        employeeDao.deleteEmployee(id);

//        employeeDao.delete("x", 1L);

        List<Employee> employees = employeeDao.listEmployees();

        assertTrue(employees.isEmpty());
    }

    @Test
    void testIllegalId() {
//        Employee employee = employeeDao.findById("x", 12L);

        Employee employee = employeeDao.findEmployeeById(12L);
        assertEquals(null, employee);
    }

    @Test
    void testEmployeeWithAttributes() {
//        for (long i = 0; i < 10; i++) {
//            employeeDao.save(new Employee("x", i, "John Doe", Employee.EmployeeType.HALF_TIME,
        for (int i = 0; i < 10; i++) {
            employeeDao.saveEmployee(new Employee("John Doe", Employee.EmployeeType.HALF_TIME,
                    LocalDate.of(2000, 1, 1)));
        }

        Employee employee = employeeDao.listEmployees().get(0);

        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        employee = employeeDao.findEmployeeById(employee.getId());

        employee.setName("Jack Doe");

        Employee modifiedEmployee = employeeDao.findEmployeeById(employee.getId());

        assertEquals("John Doe", modifiedEmployee.getName());
        assertFalse(employee == modifiedEmployee);
    }

    @Test
    void testMerge() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        employee.setName("Jack Doe");
        employeeDao.updateEmployee(employee);

        Employee modifiedEmployee = employeeDao.findEmployeeById(employee.getId());

        assertEquals("***Jack Doe", modifiedEmployee.getName());    // merge miatt
    }

    @Test
    void testFlush() {
        for (int i = 0; i < 10; i++) {
            employeeDao.saveEmployee(new Employee("John Doe" + i));
        }

        employeeDao.updateEmployeeNames();
    }

}