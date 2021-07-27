package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {

//    ha lombokot használok, és kiteszem @Slf4j annotációt, akkor nem kell
//    private static final Logger log = LoggerFactory.getLogger(EmployeesService.class)

    private ModelMapper modelMapper;

    private EmployeesDao employeesDao;

//    addig kellett, amíg memóriában tároltuk az employeekat
//    private AtomicLong idGenerator = new AtomicLong();
//
//    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Employee(idGenerator.incrementAndGet(), "John Doe"),
//            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
//    )));

//    lombok @AllArgsConstructor annotációjával kiváltottuk
//    public EmployeesService(ModelMapper modelMapper, EmployeesDao employeesDao) {
//        this.modelMapper = modelMapper;
//        this.employeesDao = employeesDao;
//    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employeesDao.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .toList();
    }

//    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<EmployeeDto>>() {
//        }.getType();
//        List<Employee> filtered = employees.stream()
//                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
//                .collect(Collectors.toList());
//        return modelMapper.map(filtered, targetListType);


    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesDao.findById(id), EmployeeDto.class);
    }

    public EmployeeDto createEmployeeCommand(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        employeesDao.createEmployee(employee);
        log.info("Employee has been created");
        log.debug("Employee has been created with name {}", command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = new Employee(id, command.getName());
        employeesDao.updateEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employeesDao.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeesDao.deleteAll();
    }
//    adatbázisból dolgozva már nem kellett
//    private Employee getEmployeeById(Long id) {
//        return employees.stream()
//                .filter(e -> e.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }

}
