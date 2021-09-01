package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProjectDao {

    private EntityManagerFactory factory;

    public ProjectDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveProject(Project project) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }

    public Project findProjectByName(String name) {
        EntityManager em = factory.createEntityManager();
        Project project = em
                .createQuery("select p from Project p join fetch p.employees where p.name = :name", Project.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return project;
    }
}
