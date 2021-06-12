package emapp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ServiceTest
class EmployeeServiceTest {

    @Test
    void testListEmployeeNames() {
        assertEquals(List.of("John Doe", "Jane Doe"), new EmployeeService().listEmployeeNames());
    }

    @Test
    void testListEmployees() {
        assertEquals(List.of(new Employee("John Doe", 1970), new Employee("Jane Doe", 1980)),
                new EmployeeService().listEmployees());


        assertEquals(List.of("John Doe", "Jane Doe"), new EmployeeService().listEmployees()
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList()), "Employee lists are different");
    }


}