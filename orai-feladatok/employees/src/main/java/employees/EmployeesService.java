package employees;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jack Doe")
    )));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> listEmployees() {
        List<EmployeeDTO> employeeDTOS = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
        return employeeDTOS;
    }

}
