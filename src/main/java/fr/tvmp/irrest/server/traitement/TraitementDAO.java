package fr.tvmp.irrest.server.traitement;

import fr.tvmp.irrest.server.common.AbstractDAO;

public class TraitementDAO extends AbstractDAO<TraitementEntity> {
    @Override
    protected Class<TraitementEntity> getEntityClass() {
        return TraitementEntity.class;
    }

}
