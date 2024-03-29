package map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProjectDao {

    private EntityManagerFactory entityManagerFactory;

    public ProjectDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveProject(Project project) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }

    public Project findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Project project = em
                .createQuery("select p from Project p join fetch p.employees where p.id = :id", Project.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return project;
    }
}
