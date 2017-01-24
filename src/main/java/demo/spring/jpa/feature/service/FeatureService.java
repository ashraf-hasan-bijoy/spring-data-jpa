package demo.spring.jpa.feature.service;

import demo.spring.jpa.domain.Feature;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ashrafhasan
 * @since 1/23/17
 */
@Repository
public class FeatureService {

    @PersistenceContext
    private EntityManager entityManager;

    public Feature find(long id) {
        return entityManager.find(Feature.class, id);
    }

    @Transactional
    public Feature save(Feature feature) {
        return doSave(feature);
    }

    @Transactional
    public void delete(Feature feature) {
        feature = entityManager.getReference(Feature.class, feature.getId());
        entityManager.remove(feature);
    }

    @Transactional
    public Feature update(Feature feature) {
        return doSave(feature);
    }

    private Feature doSave(Feature feature) {
        if (feature.isNew()) {
            entityManager.persist(feature);
            entityManager.flush();
        } else {
            feature = entityManager.merge(feature);
        }

        return feature;
    }
}
