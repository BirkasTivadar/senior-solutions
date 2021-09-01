package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {

    private ProjectDao projectDao;

    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
//        dataSource.setUser("employees");
//        dataSource.setPassword("employees");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.clean();
//        flyway.migrate();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        projectDao = new ProjectDao(factory);
        employeeDao = new EmployeeDao(factory);
    }

    @Test
    void testSaveProject() {
        Employee john = new Employee("John Doe");
        Employee jane = new Employee("Jane Doe");
        Employee jack = new Employee("Jack Doe");

        employeeDao.save(john);
        employeeDao.save(jane);
        employeeDao.save(jack);

        Project java = new Project("Java");
        Project dotNet = new Project("dotNet");
        Project python = new Project("Python");

        java.addEmployee(john);
        java.addEmployee(jane);

        python.addEmployee(john);
        python.addEmployee(jack);

        dotNet.addEmployee(jack);

        projectDao.saveProject(java);
        projectDao.saveProject(dotNet);
        projectDao.saveProject(python);

        Project project = projectDao.findProjectByName("Java");

        assertEquals(Set.of("John Doe", "Jane Doe"), project.getEmployees().stream().map(Employee::getName).collect(Collectors.toSet()));
    }


}