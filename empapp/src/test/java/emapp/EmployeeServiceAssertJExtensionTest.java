package emapp;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static emapp.EmployeeAssert.assertThat;


public class EmployeeServiceAssertJExtensionTest {

    @Test
    void testListEmployees() {
        List<Employee> employees = new EmployeeService().listEmployees();

        Employee employee = employees.get(0);

//        Condition<Employee> famillyNameDoe =
//                new Condition<>(e -> e.getName().endsWith("Doe"), "family name is Doe");
//        assertThat(employee).has(famillyNameDoe);

        assertThat(employee).hasName("John Doe");

    }
}
