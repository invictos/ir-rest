package fr.tvmp.irrest.server.traitement.donneemedicale;


import fr.tvmp.irrest.common.dto.traitement.DonneeNewDTO;
import fr.tvmp.irrest.common.dto.traitement.DonneeType;
import fr.tvmp.irrest.server.CPAMEntity;
import fr.tvmp.irrest.server.common.ToDTO;
import fr.tvmp.irrest.server.traitement.TraitementEntity;
import fr.tvmp.irrest.common.dto.traitement.DonneeDTO;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "donnees_medicales")
public class DonneeMedicaleEntity extends CPAMEntity implements ToDTO<DonneeDTO> {

    @ManyToOne
    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @NonNull TraitementEntity traitement;

    @NonNull DonneeType type;

    @NonNull String value;

    public DonneeMedicaleEntity(@NonNull DonneeNewDTO donneeDTO, @NonNull TraitementEntity traitement){
        this(traitement, donneeDTO.getType(), donneeDTO.getValue());
    }

    @Override
    public DonneeDTO toDTO() {
        return new DonneeDTO(getId(), getType(), getValue());
    }
}