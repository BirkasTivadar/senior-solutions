package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMapDaoTest {

    private ProjectMapDao projectDao;

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
        projectDao = new ProjectMapDao(factory);
    }

//    @Test
//    void testSaveThenFind() {
//        ProjectMap project = new ProjectMap("Java");
//        project.getEmployeeMaps().put("c123", new EmployeeMap("c123", "John Doe"));
//        project.getEmployeeMaps().put("c456", new EmployeeMap("c456", "Jane Doe"));
//        projectDao.saveProject(project);
//
//        Long id = project.getId();
//
//        ProjectMap another = projectDao.findById(id);
//
//        assertEquals("John Doe", another.getEmployeeMaps().get("c123").getName());
//    }

    @Test
    void testSaveThenFind() {
        ProjectMap project = new ProjectMap("Java");
        project.getEmployeeMaps().put("java_1", new EmployeeMap("c123", "John Doe"));
        project.getEmployeeMaps().put("java_2", new EmployeeMap("c456", "Jane Doe"));
        projectDao.saveProject(project);

        Long id = project.getId();

        ProjectMap another = projectDao.findById(id);

        assertEquals("Jane Doe", another.getEmployeeMaps().get("java_2").getName());
    }

}