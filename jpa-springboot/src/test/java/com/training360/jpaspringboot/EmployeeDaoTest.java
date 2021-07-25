package com.training360.jpaspringboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
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