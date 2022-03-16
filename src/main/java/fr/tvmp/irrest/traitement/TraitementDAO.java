package fr.tvmp.irrest.traitement;

import fr.tvmp.irrest.common.AbstractDAO;

public class TraitementDAO extends AbstractDAO<TraitementEntity> {
    @Override
    protected Class<TraitementEntity> getEntityClass() {
        return TraitementEntity.class;
    }

}
