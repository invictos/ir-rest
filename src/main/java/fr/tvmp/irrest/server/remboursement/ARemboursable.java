package fr.tvmp.irrest.server.remboursement;

import fr.tvmp.irrest.common.dto.remboursement.ARemboursableDTO;
import fr.tvmp.irrest.server.CPAMEntity;
import fr.tvmp.irrest.server.common.ToDTO;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Pas une interface car les interfaces ne fonctionnent pas avec JPA.
 * On utilise donc InheritanceType.TABLE_PER_CLASS
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "remboursable")
public abstract class ARemboursable extends CPAMEntity implements ToDTO {
    public abstract Float getPrix();

    public abstract Float getTauxCPAM();

    public Float getRemboursement(){
        return getPrix()*getTauxCPAM();
    }
}