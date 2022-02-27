package fr.tvmp.irrest.user;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserDAO {

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    public UserEntity getUserByUUID(@NonNull UUID id){
        return em.find(UserEntity.class, id);
    }

    @Transactional
    public void addUser(@NotNull UserEntity user){
        logger.info("Adding: " + user);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}