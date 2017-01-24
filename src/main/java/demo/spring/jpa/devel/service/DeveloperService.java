package demo.spring.jpa.devel.service;

import demo.spring.jpa.domain.Developer;
import demo.spring.jpa.domain.DeveloperActivity;
import demo.spring.jpa.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ashrafhasan
 * @since 1/19/17
 */
@Repository
public class DeveloperService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DeveloperActivityService activityService;

    @Autowired
    private ProjectService projectService;

    @Transactional
    public Developer find(long id) {
        Developer developer = entityManager.find(Developer.class, id);
        developer.setDeveloperActivity(activityService.find(developer.getId()));

        return developer;
    }

    public List<Developer> search() {
        return entityManager.createQuery("SELECT d FROM Developer d", Developer.class)
                .getResultList();
    }

    @Transactional
    public Developer save(Developer developer) {
        developer = doSave(developer);
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        DeveloperActivity da = new DeveloperActivity(Collections.singletonList("Developer form has been created at " + date), developer);
        developer.setDeveloperActivity(activityService.save(da));

        return developer;
    }

    @Transactional
    public void delete(Developer developer) {
        /*
        * Detached entity cannot be deleted. We need to make it managed before calling entityManager.remove(entity)
        * */
        developer = entityManager.getReference(Developer.class, developer.getId());

        /*We need to remove all the entity with developer entity reference before removing it*/
        activityService.remove(developer.getId());
        projectService.remove(developer.getId());

        entityManager.remove(developer);
    }

    @Transactional
    public Developer update(Developer developer) {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        activityService.addActivity(developer.getId(), "Developer form has been updated at " + date);

        return doSave(developer);
    }

    private Developer doSave(Developer developer) {
        if (developer.isNew()) {
            entityManager.persist(developer);
            entityManager.flush();
        } else {
            developer = entityManager.merge(developer);
        }

        return developer;
    }
}
