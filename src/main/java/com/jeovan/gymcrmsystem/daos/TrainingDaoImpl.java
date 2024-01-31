package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Training;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TrainingDaoImpl implements TrainingDaoCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Training> getByUsernameAndCriteria(String username, Map<String, String> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> cr = cb.createQuery(Training.class);

        Root<Training> root = cr.from(Training.class);
        Join<Training, Trainee> traineeTrainings = root.join("trainee").join("user");

        Predicate[] predicates = criteria.entrySet().stream()
                .map(criterion -> cb.equal(root.get(criterion.getKey()), criterion.getValue()))
                .toArray(Predicate[]::new);

        cr.where(predicates).where(cb.equal(traineeTrainings.get("username"), username));
        Query query = entityManager.createQuery(cr);

        List<Training> results = query.getResultList();
        return results;
    }
}
