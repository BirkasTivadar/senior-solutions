package emapp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@EntityTest
class EmployeeTest {

    Employee employee;

    @BeforeEach
    void initEmployee() {
        employee = new Employee("John Doe", 1970);
    }

    @Test
    void testGetDatasOfEmployee() {

        assertAll(
                () -> assertEquals("John Doe", employee.getName()),
                () -> assertEquals(30, employee.getAge(2000))
        );
    }

    @Test
    void testGetAgeWithZero(){
        assertEquals(0, employee.getAge(1970));
    }

    @Test
    void testCreateEmployeeWithYearOfBirth1700(){
//        assertThrows(IllegalArgumentException.class, ()->new Employee("Bibi", 1700));
       IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Employee("Bibi", 1700));
       assertEquals("Year: 1700", iae.getMessage());
    }




}