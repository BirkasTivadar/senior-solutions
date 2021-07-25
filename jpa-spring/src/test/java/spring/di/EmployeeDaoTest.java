package spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSaveThenList() {
        employeeDao.saveEmployee("John Doe");
        List<String> names = employeeDao.listEmployees();

        assertEquals(List.of("John Doe"), names);
    }

}