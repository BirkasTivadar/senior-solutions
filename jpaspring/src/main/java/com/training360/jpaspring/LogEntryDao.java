package com.training360.jpaspring;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LogEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Transactional
    @Transactional(Transactional.TxType.REQUIRES_NEW) // A propagáció beállítása, amikor az employeeDao meghívja a logEntryDao-t akkor a logEntryDao egy új tranzakciót fog indítani
    public void save(LogEntry logEntry) {
        entityManager.persist(logEntry);
    }

    public List<LogEntry> list() {
        return entityManager
                .createQuery("select l from LogEntry l order by l.id", LogEntry.class)
                .getResultList();
    }

}
