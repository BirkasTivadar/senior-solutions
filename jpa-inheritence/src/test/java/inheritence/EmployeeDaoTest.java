package inheritence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(factory);
    }

    @Test
    void testSaveThenFind() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployee(employee);

        employeeDao.saveEmployee(new CompanyEmployee("Jack Doe", 22));
        employeeDao.saveEmployee(new ContractEmployee("Jane Doe", 100_000));

        Employee another = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", another.getName());

        Employee company = employeeDao.findEmployeeByName("Jack Doe");
        assertEquals("Jack Doe", company.getName());
        assertEquals(22, ((CompanyEmployee) company).getVacation());

        Employee contracted = employeeDao.findEmployeeByName("Jane Doe");
        assertEquals("Jane Doe", contracted.getName());
        assertEquals(100_000, ((ContractEmployee) contracted).getDailyRate());
    }


}