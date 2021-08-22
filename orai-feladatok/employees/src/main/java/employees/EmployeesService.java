package employees;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private final ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jack Doe")
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
        return modelMapper.map(employees.stream()
                        .filter(employee -> employee.getId() == id)
                        .findAny().orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)),
                EmployeeDTO.class);
    }
}
