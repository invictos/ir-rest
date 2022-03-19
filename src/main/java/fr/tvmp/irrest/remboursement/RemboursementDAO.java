package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.common.AbstractDAO;

public class RemboursementDAO extends AbstractDAO<RemboursementEntity> {
    @Override
    protected Class<RemboursementEntity> getEntityClass() {
        return RemboursementEntity.class;
    }
}
