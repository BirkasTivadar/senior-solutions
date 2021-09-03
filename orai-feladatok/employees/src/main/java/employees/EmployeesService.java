package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {

    private final ModelMapper modelMapper;

    private EmployeesRepository repository;

//    private AtomicLong idGenerator = new AtomicLong();

//    private EmployeesDao employeesDao;

//    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Employee(idGenerator.incrementAndGet(), "John Doe"),
//            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
//    )));

//    public EmployeesService(ModelMapper modelMapper, EmployeesDao employeesDao) {
//        this.modelMapper = modelMapper;
//        this.employeesDao = employeesDao;
//    }

    public List<EmployeeDTO> listEmployees(Optional<String> prefix) {
        return repository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();

//        return employeesDao.findAll().stream()
//.map(employee -> modelMapper.map(employee, EmployeeDTO.class))
//                .toList();

//        return employees.stream()
//                .filter(employee -> prefix.isEmpty() || employee.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
//                .map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
    }

    public EmployeeDTO findEmployeeById(Long id) {
        return modelMapper.map(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found " + id)),
                EmployeeDTO.class);
//        return modelMapper.map(employeesDao.findById(id), EmployeeDTO.class);
//        return modelMapper.map(findById(id), EmployeeDTO.class);
    }

    public EmployeeDTO createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        repository.save(employee);
//        Employee employee = new Employee(command.getName());
//        employeesDao.createEmployee(employee);
//        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
//        employees.add(employee);
        log.info("Employee has been created");
        log.debug("Employee has been created with name {}", command.getName());
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Transactional
    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeCommand command) {
        Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found " + id));
        employee.setName(command.getName());
//        Employee employee = new Employee(id, command.getName());
//        employeesDao.updateEmployee(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
//        employeesDao.deleteById(id);
    }

    public void deleteAllEmployees() {
        repository.deleteAll();
//        employeesDao.deleteAll();
    }

//    private Employee findById(Long id) {
//        return employees.stream()
//                .filter(e -> e.getId() == id)
////                .findFirst().orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
//                .findFirst().orElseThrow(() -> new EmployeeNotFoundException(id));
}

