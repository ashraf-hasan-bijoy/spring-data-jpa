package demo.spring.jpa.project.service;

import demo.spring.jpa.domain.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author ashrafhasan
 * @since 1/23/17
 */
@Repository
public class ProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public Project find(long id) {
        return entityManager.find(Project.class, id);
    }

    public List<Project> search() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class)
                .getResultList();
    }

    @Transactional
    public Project save(Project project) {
        return doSave(project);
    }

    @Transactional
    public Project update(Project project) {
        return doSave(project);
    }

    @Transactional
    public void remove(long develId) {
        List<Project> projects = entityManager.createQuery("SELECT p FROM Project p" +
                " WHERE p.developer.id = :develId", Project.class)
                .setParameter("develId", develId)
                .getResultList();

        for (Project project : projects) {
            entityManager.remove(project);
        }
    }

    private Project doSave(Project project) {
        if (project.isNew()) {
            entityManager.persist(project);
            entityManager.flush();
        } else {
            project = entityManager.merge(project);
        }

        return project;
    }
}
