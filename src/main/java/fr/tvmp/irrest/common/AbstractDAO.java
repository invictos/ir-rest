package fr.tvmp.irrest.common;

import fr.tvmp.irrest.CPAMEntity;
import lombok.Getter;
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

@Getter
public abstract class AbstractDAO<T extends CPAMEntity> {
    @Inject
    EntityManager entityManager;

    protected Logger getLogger(){
        return Logger.getLogger(getClass().getName());
    }

    public T getByUUID(@NonNull UUID id){
        return getEntityManager().find(getEntityClass(), id);
    }

    public List<T> getAll(){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        Root<T> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public T add(@NotNull T entity) {
        getLogger().info("Adding: " + entity);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    protected abstract Class<T> getEntityClass();
}
