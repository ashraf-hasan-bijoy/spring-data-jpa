package demo.spring.jpa.devel.service;

import demo.spring.jpa.domain.DeveloperActivity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ashrafhasan
 * @since 1/25/17
 */
@Repository
public class DeveloperActivityService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DeveloperActivity save(DeveloperActivity developerActivity) {
        entityManager.persist(developerActivity);
        return developerActivity;
    }

    @Transactional
    public void remove(long develId) {
        DeveloperActivity developerActivity = findByDevelId(develId);
        entityManager.remove(developerActivity);
    }

    @Transactional
    public void addActivity(long develId, String activityMessage) {
        DeveloperActivity developerActivity = findByDevelId(develId);
        developerActivity.getActivityMessages().add(activityMessage);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DeveloperActivity find(long develId) {
        DeveloperActivity activity = findByDevelId(develId);
        /* OpenEntityManagerInViewInterceptor doesn't work for nested transaction. All the entities fetched in this
         * transaction will be detached after transaction commit. So accessing lazily fetched fields after committing
         * transaction will throw Exception unless they are accessed within Transaction*/
        activity.getActivityMessages().size();
        return activity;
    }

    private DeveloperActivity findByDevelId(long develId) {
        return entityManager.createQuery("SELECT da FROM DeveloperActivity da" +
                " WHERE da.developer.id = :develId", DeveloperActivity.class)
                .setParameter("develId", develId)
                .getSingleResult();
    }
}
