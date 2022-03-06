package fr.tvmp.irrest.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class UserDAO {

    @Inject
    EntityManager em;

    @Inject
    Logger logger;


    public List<UserEntity> getAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> rootEntry = cq.from(UserEntity.class);
        CriteriaQuery<UserEntity> all = cq.select(rootEntry);
        TypedQuery<UserEntity> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public UserEntity getUserByUUID(@NonNull UUID id){
        return em.find(UserEntity.class, id);
    }

    @Transactional
    public UserEntity addUser(@NotNull UserEntity user) {
        logger.info("Adding: " + user);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }
}