package employees;

//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        Long id = employee.getId();

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

        Employee modifiedEmployee = employeeDao.findById(id);

        assertEquals("Jack Doe", modifiedEmployee.getName());
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
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 1, 1)));
        }

        Employee employee = employeeDao.listAll().get(0);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        employee.setName("Jack Doe");

        Employee modifiedEmployee = employeeDao.findById(employee.getId());

        assertEquals("John Doe", modifiedEmployee.getName());
        assertFalse(employee == modifiedEmployee);
    }

    //    merge metódus használata nem javasolt
    @Test
    void testMerge() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        employee.setName("Jack Doe");
        employeeDao.updateEmployee(employee);

        Employee modifiedEmployee = employeeDao.findById(employee.getId());

        assertEquals("***Jack Doe", modifiedEmployee.getName());
    }

    @Test
    void testFlush() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe" + i));
        }

        employeeDao.updateEmployeeNames();
    }

    @Test
    void testNicknames() {
        Employee employee = new Employee("John Doe");
        employee.setNicknames(Set.of("Johnny", "J"));
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithNicknames(employee.getId());
        assertEquals(Set.of("J", "Johnny"), anotherEmployee.getNicknames());
    }

    @Test
    void testVacations() {
        Employee employee = new Employee("John Doe");
        employee.setVacationBookings(Set.of(
                new VacationEntry(LocalDate.of(2018, 1, 1), 4),
                new VacationEntry(LocalDate.of(2018, 2, 15), 2)
        ));

        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithNicknamesVacations(employee.getId());

        System.out.println(anotherEmployee.getVacationBookings());
        assertEquals(2, anotherEmployee.getVacationBookings().size());
    }

    @Test
    void testPhoneNumbers() {
        Employee employee = new Employee("John Doe");
        employee.setPhoneNumbers(Map.of(
                "home", "1234",
                "work", "4321"
        ));

        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());

        assertEquals("1234", anotherEmployee.getPhoneNumbers().get("home"));
        assertEquals("4321", anotherEmployee.getPhoneNumbers().get("work"));
    }

}