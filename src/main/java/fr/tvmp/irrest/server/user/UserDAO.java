package fr.tvmp.irrest.server.user;

import fr.tvmp.irrest.server.common.AbstractDAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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