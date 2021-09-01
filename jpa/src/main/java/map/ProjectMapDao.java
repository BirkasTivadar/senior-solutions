package map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProjectMapDao {

    private EntityManagerFactory factory;

    public ProjectMapDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveProject(ProjectMap project) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }

    public ProjectMap findById(Long id) {
        EntityManager em = factory.createEntityManager();
        ProjectMap project = em
                .createQuery("select p from ProjectMap p join fetch p.employeeMaps where p.id = :id", ProjectMap.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return project;
    }
}
