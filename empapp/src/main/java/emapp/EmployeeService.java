package emapp;

import java.util.List;

public class EmployeeService {

    public List<Employee> listEmployees() {
        return List.of(new Employee("John Doe", 1970),
                new Employee("Jane Doe", 1980));
    }

    public List<String> listEmployeeNames() {
        return List.of("John Doe", "Jane Doe");
    }

    public void calculateYearlyReport() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            throw new IllegalStateException("Interrupted", ie);
        }
    }
}
