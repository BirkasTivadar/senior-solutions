package jpaspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSaveAndFind() {
        employeeDao.saveEmployee(new Employee("John Doe"));

        Employee employee = employeeDao.findEmployeeByName("John Doe");

        assertEquals("John Doe", employee.getName());
    }

}