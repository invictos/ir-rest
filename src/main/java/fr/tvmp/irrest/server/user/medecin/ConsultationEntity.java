package fr.tvmp.irrest.server.user.medecin;

import fr.tvmp.irrest.common.dto.remboursement.ConsultationDTO;
import fr.tvmp.irrest.server.CPAMException;
import fr.tvmp.irrest.server.remboursement.ARemboursable;
import fr.tvmp.irrest.server.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "consultation")
public class ConsultationEntity extends ARemboursable {

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @EqualsAndHashCode.Exclude
    MedecinEntity medecin;

    @Override
    public Float getPrix() {
        switch (medecin.getType()){
            case GENERALISTE:
                return 25F;
            case SPECIALISTE:
                return 100F;
            default:
                throw new RuntimeException("Medecin type needs to have a price associated");
        }
    }

    @Override
    public Float getTauxCPAM() {
        return 0.70F;
    }

    @Override
    public ConsultationDTO toDTO() {
        return new ConsultationDTO(getPrix(), getTauxCPAM(), getRemboursement(), getMedecin().getId());
    }
}