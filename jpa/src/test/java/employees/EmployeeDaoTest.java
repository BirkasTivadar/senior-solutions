package employees;

//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    private ParkingPlaceDao parkingPlaceDao;

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
        parkingPlaceDao = new ParkingPlaceDao(factory);
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

        assertEquals(2, anotherEmployee.getVacationBookings().size());
    }

//    @Test
//    void testPhoneNumbers() {
//        Employee employee = new Employee("John Doe");
//        employee.setPhoneNumbers(Map.of(
//                "home", "1234",
//                "work", "4321"
//        ));
//
//        employeeDao.save(employee);
//
//        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
//
//        assertEquals("1234", anotherEmployee.getPhoneNumbers().get("home"));
//        assertEquals("4321", anotherEmployee.getPhoneNumbers().get("work"));
//    }

    @Test
    void testPhoneNumber() {
        PhoneNumber phoneNumberHome = new PhoneNumber("home", "1234");
        PhoneNumber phoneNumberWork = new PhoneNumber("work", "4321");

        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(phoneNumberWork);
        employee.addPhoneNumber(phoneNumberHome);

        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());

        assertEquals(2, anotherEmployee.getPhoneNumbers().size());
        assertEquals("4321", anotherEmployee.getPhoneNumbers().get(0).getNumber());
    }

    @Test
    void testAddPhoneNumber() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        PhoneNumber phoneNumber = new PhoneNumber("home", "1111");

        employeeDao.addPhoneNumber(employee.getId(), phoneNumber);

        Employee anotherEmployee = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());

        assertEquals("1111", anotherEmployee.getPhoneNumbers().get(0).getNumber());
    }

    @Test
    public void testEmployee() {
        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(new PhoneNumber("home", "1111"));
        employee.addPhoneNumber(new PhoneNumber("work", "2222"));
        employeeDao.save(employee);

        employeeDao.delete(employee.getId());
    }

//    @Test
//    void testEmployeeWithAddress() {
//        Employee employee = new Employee("John Doe");
//        Address address = new Address("H-1301", "Budapest", "Fő utca");
//        employee.setAddress(address);
//        employeeDao.save(employee);
//
//        Employee anotherEmployee = employeeDao.findById(employee.getId());
//
//        assertEquals("H-1301", anotherEmployee.getAddress().getZip());
//    }

    @Test
    void testEmployeeWithAddressAttributes() {
        Employee employee = new Employee("John Doe");
        employee.setZip("H-1301");
        employee.setCity("Budapest");
        employee.setLine("Fő utca");
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findById(employee.getId());

        assertEquals("H-1301", anotherEmployee.getZip());
    }

    @Test
    void testFindEmployeeByName() {
        employeeDao.save(new Employee("John Doe"));

        Employee employee = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testPaging() {
        for (int i = 100; i < 300; i++) {
            Employee employee = new Employee("John Doe " + i);
            employeeDao.save(employee);
        }

        List<Employee> employees = employeeDao.listEmployees(50, 20);

        assertEquals("John Doe 150", employees.get(0).getName());
        assertEquals(20, employees.size());
    }

    @Test
    void testFindParkingPlaceNumber() {
        Employee employee = new Employee("John Doe");
        ParkingPlace parkingPlace = new ParkingPlace(101);
        parkingPlaceDao.saveParkingPlace(parkingPlace);
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);

        int number = employeeDao.findParkingPlaceNumberByEmployeeName("John Doe");
        assertEquals(101, number);
    }

    @Test
    void testBaseData() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        List<Object[]> data = employeeDao.listEmployeeBaseData();
        System.out.println(Arrays.toString(data.get(0)));

        assertEquals(1, data.size());
        assertEquals("John Doe", data.get(0)[1]);
    }

    @Test
    void testDto() {
        employeeDao.save(new Employee("John Doe"));
        employeeDao.save(new Employee("Jane Doe"));

        List<EmpBaseDataDto> data = employeeDao.listEmployeeDto();

        System.out.println(data);
        assertEquals(2, data.size());
        assertEquals("Jane Doe", data.get(0).getName());
    }

    @Test
    void testUpdateToType() {
        employeeDao.save(new Employee("John Doe", Employee.EmployeeType.FULL_TIME, LocalDate.of(1980, 1, 1)));
        employeeDao.save(new Employee("Jack Doe", Employee.EmployeeType.FULL_TIME, LocalDate.of(1990, 1, 1)));
        employeeDao.save(new Employee("Jane Doe", Employee.EmployeeType.FULL_TIME, LocalDate.of(2000, 1, 1)));

        employeeDao.updateToType(LocalDate.of(1985, 1, 1), Employee.EmployeeType.HALF_TIME);

        List<Employee> employees = employeeDao.listAll();

        assertEquals(Employee.EmployeeType.HALF_TIME, employees.get(0).getEmployeeType());
        assertEquals(Employee.EmployeeType.HALF_TIME, employees.get(1).getEmployeeType());
        assertEquals(Employee.EmployeeType.FULL_TIME, employees.get(2).getEmployeeType());
    }

    @Test
    void testDeleteWithoutType() {
        employeeDao.save(new Employee("John Doe", Employee.EmployeeType.FULL_TIME, LocalDate.of(1980, 1, 1)));
        employeeDao.save(new Employee("Jack Doe", null, LocalDate.of(1990, 1, 1)));
        employeeDao.save(new Employee("Jane Doe", Employee.EmployeeType.FULL_TIME, LocalDate.of(2000, 1, 1)));

        employeeDao.deleteWithoutType();

        List<Employee> employees = employeeDao.listAll();

        assertEquals(2, employees.size());
    }
}