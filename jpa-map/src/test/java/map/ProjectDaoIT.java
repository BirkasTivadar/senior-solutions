package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoIT {

    private ProjectDao projectDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        projectDao = new ProjectDao(factory);
    }

    //    @Test
//    void testSaveThenFind() {
//        Project project = new Project("Java");
//
//        project.getEmployees().put("c123", new Employee("c123", "John Doe"));
//        project.getEmployees().put("c456", new Employee("c456", "Jane Doe"));
//
//        projectDao.saveProject(project);
//        Long id = project.getId();
//
//        Project another = projectDao.findById(id);
//
//        assertEquals("John Doe", another.getEmployees().get("c123").getName());
//    }

    @Test
    void testSaveThenFind() {
        Project project = new Project("Java");

        project.getEmployees().put("java_1", new Employee("c123", "John Doe"));
        project.getEmployees().put("java_2", new Employee("c456", "Jane Doe"));

        projectDao.saveProject(project);
        Long id = project.getId();

        Project another = projectDao.findById(id);

        assertEquals("Jane Doe", another.getEmployees().get("java_2").getName());
    }
}