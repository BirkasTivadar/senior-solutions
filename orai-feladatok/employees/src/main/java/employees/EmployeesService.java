package employees;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeesService {

    private final ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(idGenerator.incrementAndGet(), "John Doe"),
            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> listEmployees(Optional<String> prefix) {
        return employees.stream()
                .filter(employee -> prefix.isEmpty() || employee.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
    }

    public EmployeeDTO findEmployeeById(Long id) {
        return modelMapper.map(findById(id), EmployeeDTO.class);
    }

    public EmployeeDTO createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeCommand command) {
        Employee employee = findById(id);
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public void deleteEmployee(Long id) {
        Employee employee = findById(id);
        employees.remove(employee);
    }

    public void deleteAllEmployees() {
        idGenerator = new AtomicLong();
        employees.clear();
    }

    private Employee findById(Long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
//                .findFirst().orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
                .findFirst().orElseThrow(() -> new EmployeeNotFoundException(id));
    }


}
