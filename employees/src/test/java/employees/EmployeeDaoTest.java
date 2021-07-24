package employees;

//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
//import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    private ParkingPlaceDao parkingPlaceDao;

    @BeforeEach
    void init() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        employeeDao = new EmployeeDao(entityManagerFactor);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
        parkingPlaceDao = new ParkingPlaceDao(entityManagerFactory);


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

    @Test
    void testNicknames() {
        Employee employee = new Employee("John Doe");
        employee.setNicknames(Set.of("Johnny", "J"));
        employeeDao.saveEmployee(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithNickNames(employee.getId());
        assertEquals(Set.of("J", "Johnny"), anotherEmployee.getNicknames());
    }

    @Test
    void testVacations() {
        Employee employee = new Employee("John Doe");
        employee.setVacationBookings(Set.of(
                new VacationEntry(LocalDate.of(2018, 1, 1), 4),
                new VacationEntry(LocalDate.of(2018, 2, 15), 2)
        ));

        employeeDao.saveEmployee(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithVacations(employee.getId());
        assertEquals(2, anotherEmployee.getVacationBookings().size());
    }

//    @Test
//    void testPhoneNumbers() {
//        Employee employee = new Employee("John Doe");
//        employee.setPhoneNumbers(Map.of(
//                "home", "1234",
//                "work", "4321"));
//
//        employeeDao.saveEmployee(employee);
//
//        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
//        assertEquals("1234", anotherEmployee.getPhoneNumbers().get("home"));
//        assertEquals("4321", anotherEmployee.getPhoneNumbers().get("work"));
//    }

    @Test
    void testPhoneNumbers() {
        PhoneNumber phoneNumberHome = new PhoneNumber("home", "1234");
        PhoneNumber phoneNumberWork = new PhoneNumber("work", "4321");

        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(phoneNumberWork);
        employee.addPhoneNumber(phoneNumberHome);
        employeeDao.saveEmployee(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        assertEquals(2, anotherEmployee.getPhoneNumbers().size());
        assertEquals("work", anotherEmployee.getPhoneNumbers().get(0).getType());
    }

    @Test
    public void testAddPhoneNumber() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        employeeDao.addPhoneNumber(employee.getId(), new PhoneNumber("home", "1111"));
        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        assertEquals(1, anotherEmployee.getPhoneNumbers().size());
    }

    @Test
    void testRemove() {
        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(new PhoneNumber("home", "1111"));
        employee.addPhoneNumber(new PhoneNumber("home", "2222"));
        employeeDao.saveEmployee(employee);

        employeeDao.deleteEmployee(employee.getId());
    }

//    @Test
//    void testEmployeeWithAddress() {
//        Employee employee = new Employee("John Doe");
//
//        Address address = new Address("H-1301", "Budapest", "Fő utca");
//
//        employee.setAddress(address);
//        employeeDao.saveEmployee(employee);
//
//        Long id = employee.getId();
//        Employee anotherEmployee = employeeDao.findEmployeeById(id);
//
//        assertEquals("H-1301", anotherEmployee.getAddress().getZip());
//    }

    @Test
    void testEmployeeWithAddressAttributes() {
        Employee employee = new Employee("John Doe");
        employee.setZip("H-1301");
        employee.setCity("Budapest");
        employee.setLine1("Fő utca");

        employeeDao.saveEmployee(employee);

        Long id = employee.getId();
        Employee another = employeeDao.findEmployeeById(id);

        assertEquals("H-1301", another.getZip());
    }

    @Test
    void testFindEmployeeByName() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        Employee another = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", another.getName());

    }

    @Test
    void testPaging() {
        for (int i = 100; i < 300; i++) {
            Employee employee = new Employee("John Doe " + i);
            employeeDao.saveEmployee(employee);
        }

        List<Employee> employees = employeeDao.listEmployees(50, 20);
        assertEquals("John Doe 150", employees.get(0).getName());
        assertEquals(20, employees.size());
    }

    @Test
    void testFindNumber() {
        Employee employee = new Employee("John Doe");
        ParkingPlace parkingPlace = new ParkingPlace(101);
        parkingPlaceDao.saveParkingPlace(parkingPlace);

        employee.setParkingPlace(parkingPlace);
        employeeDao.saveEmployee(employee);

        int number = employeeDao.findParkingPlaceNumberByEmployeeName("John Doe");

        assertEquals(101, number);
    }

    @Test
    void testBaseData() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        List<Object[]> data = employeeDao.listEmployeeBaseData();
        assertEquals(1, data.size());
        assertEquals("John Doe", data.get(0)[1]);
        System.out.println(Arrays.toString(data.get(0)));
    }

    @Test
    void testDto() {
        employeeDao.saveEmployee(new Employee("John Doe"));
        employeeDao.saveEmployee(new Employee("Jane Doe"));

        List<EmpBaseDataDto> data = employeeDao.ListEmployeeDto();

        System.out.println(data);

        assertEquals(2, data.size());
        assertEquals("Jane Doe", data.get(0).getName());
    }
}