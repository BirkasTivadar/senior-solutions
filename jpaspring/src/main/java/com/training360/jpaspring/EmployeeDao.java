package com.training360.jpaspring;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    private LogEntryDao logEntryDao;

    public EmployeeDao(LogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }

    @Transactional
//    @Transactional(dontRollbackOn = IllegalArgumentException.class) // ha nem szeretném, hogy rolbback-eljen, (kivételt dob, de az adatbázisba lementi
    public void saveEmployee(Employee employee) throws IllegalArgumentException {
        logEntryDao.save(new LogEntry("Create employee: " + employee.getName()));
        entityManager.persist(employee);


        if (employee.getName().length() < 3) {
            throw new IllegalArgumentException("Name must be longer than 3 characters");
        }
    }

    public Employee findEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }
}
