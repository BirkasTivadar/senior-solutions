package emapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmployeeTest implements PrintNameCapable {

    Employee employee;

    public EmployeeTest() {
        System.out.println("CONSTRUCTOR");
    }

    @BeforeAll
    static void befroeAll() {
        System.out.println("BEFORE ALL");
    }

    @BeforeEach
    void initEmployee() {
        System.out.println("INIT");
        employee = new Employee("John Doe", 1970);
    }

    @Test
   // @DisabledOnOs(OS.WINDOWS)
    @DisplayName("Test age calculation with positive number")
    void testGetAge() {
        System.out.println("TC1");

        assertEquals(30, employee.getAge(2000));

        assertAll();

//        assertTrue(30 == employee.getAge(2000));

//        assertNotNull(employee);
//
//        Employee expected = new Employee("John Doe", 1970);
//        assertEquals(expected, employee);
//        assertNotSame(expected, employee);
//
//        expected = employee;
//        assertSame(expected, employee);
//
//        assertEquals(0.333333, 1.0/3, 0.0001 );

    }

    @Test
    void test_Get_Age_With_Zero() {
        System.out.println("TC2");
        assertEquals(0, employee.getAge(1970));
    }


}