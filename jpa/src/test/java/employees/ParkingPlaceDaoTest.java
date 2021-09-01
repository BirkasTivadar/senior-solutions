package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {

    private ParkingPlaceDao parkingPlaceDao;

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
        parkingPlaceDao = new ParkingPlaceDao(factory);
        employeeDao = new EmployeeDao(factory);
    }

    @Test
    void testSave() {
        parkingPlaceDao.saveParkingPlace(new ParkingPlace(100));

        ParkingPlace parkingPlace = parkingPlaceDao.findParkingPlaceByNumber(100);

        assertEquals(100, parkingPlace.getNumber());
    }

    @Test
    void testSaveEmployeeWithParkingPlace() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParkingPlace(parkingPlace);

        Employee employee = new Employee("John Doe");
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findById(employee.getId());

        assertEquals(100, anotherEmployee.getParkingPlace().getNumber());
    }
}