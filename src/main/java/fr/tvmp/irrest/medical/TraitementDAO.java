package fr.tvmp.irrest.medical;

import fr.tvmp.irrest.common.AbstractDAO;

public class TraitementDAO extends AbstractDAO<TraitementEntity> {
    @Override
    protected Class<TraitementEntity> getEntityClass() {
        return TraitementEntity.class;
    }

}
