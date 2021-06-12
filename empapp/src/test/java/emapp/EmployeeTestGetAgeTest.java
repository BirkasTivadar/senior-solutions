package emapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@EntityTest
public class EmployeeTestGetAgeTest {

    Employee employee;

    @Nested
    class WithJohnAge1970 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1970);
        }

        @Test
        void testGetAge1970() {
            assertEquals(0, employee.getAge(1970));

        }

        @Test
        void testGetName() {
            assertEquals("John Doe", employee.getName());

        }
    }

    @Nested
    class WithJaneAge1980 {
        @BeforeEach
        void init() {
            employee = new Employee("Jane Doe", 1980);
        }

        @Test
        void testGetAge1980() {
            assertEquals(0, employee.getAge(1980));
        }

        @Test
        void testGetName() {
            assertEquals("Jane Doe", employee.getName());

        }
    }

}
