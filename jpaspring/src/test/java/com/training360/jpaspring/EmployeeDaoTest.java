package com.training360.jpaspring;

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
    void testSaveThenFind() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        Long id = employee.getId();

        assertEquals("John Doe", employeeDao.findEmployeeById(id).getName());
    }


    @Test
    void testSaveWithEmptyName(){
        Employee employee = new Employee("");

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> employeeDao.saveEmployee(employee));
    assertEquals("Name must be longer than 3 characters", iae.getMessage());
    }

}