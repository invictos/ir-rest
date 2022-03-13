package fr.tvmp.irrest.user;

import fr.tvmp.irrest.common.AbstractDAO;
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

public class UserDAO extends AbstractDAO<UserEntity> {
    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    public List<UserEntity> getAll(){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(getEntityClass());
        Root<UserEntity> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<UserEntity> all = cq.select(rootEntry);
        TypedQuery<UserEntity> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }
}