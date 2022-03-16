package fr.tvmp.irrest.traitement.donneemedicale;


import fr.tvmp.irrest.CPAMEntity;
import fr.tvmp.irrest.traitement.TraitementEntity;
import fr.tvmp.irrest.dto.DonneeMedicaleDTO;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "donnees_medicales")
public class DonneeMedicaleEntity extends CPAMEntity {

    @ManyToOne
    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @NonNull TraitementEntity traitement;

    @NonNull DonneeMedicaleType type;

    @NonNull String value;

    public DonneeMedicaleEntity(@NonNull DonneeMedicaleDTO donneeMedicaleDTO, @NonNull TraitementEntity traitement){
        this(traitement, donneeMedicaleDTO.getType(), donneeMedicaleDTO.getValeur());
    }
}