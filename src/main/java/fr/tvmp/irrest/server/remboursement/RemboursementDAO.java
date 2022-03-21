package fr.tvmp.irrest.server.remboursement;

import fr.tvmp.irrest.server.common.AbstractDAO;

public class RemboursementDAO extends AbstractDAO<RemboursementEntity> {
    @Override
    protected Class<RemboursementEntity> getEntityClass() {
        return RemboursementEntity.class;
    }
}
