package inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeInheritanceDaoTest {

    private EmployeeInheritanceDao employeeInheritanceDao;

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
        employeeInheritanceDao = new EmployeeInheritanceDao(factory);
    }

    @Test
    void testSaveAndFind() {
        employeeInheritanceDao.saveEmployeeInheritance(new EmployeeInheritance("John Doe"));

        employeeInheritanceDao.saveEmployeeInheritance(new CompanyEmployee("Jack Doe", 22));
        employeeInheritanceDao.saveEmployeeInheritance(new ContractEmployee("Jane Doe", 100));

        EmployeeInheritance employeeInheritance = employeeInheritanceDao.findEmployeeInheritanceByName("John Doe");
        assertEquals("John Doe", employeeInheritance.getName());

        EmployeeInheritance company = employeeInheritanceDao.findEmployeeInheritanceByName("Jack Doe");
        assertEquals("Jack Doe", company.getName());
        assertEquals(22, ((CompanyEmployee) company).getVacation());

        EmployeeInheritance contracted = employeeInheritanceDao.findEmployeeInheritanceByName("Jane Doe");
        assertEquals("Jane Doe", contracted.getName());
        assertEquals(100, ((ContractEmployee) contracted).getDailyRate());
    }

}