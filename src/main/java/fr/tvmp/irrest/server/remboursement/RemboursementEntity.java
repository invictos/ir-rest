package fr.tvmp.irrest.server.remboursement;

import fr.tvmp.irrest.common.dto.remboursement.ARemboursableDTO;
import fr.tvmp.irrest.common.dto.remboursement.RemboursementDTO;
import fr.tvmp.irrest.server.CPAMEntity;
import fr.tvmp.irrest.server.common.ToDTO;
import fr.tvmp.irrest.server.user.medecin.MedecinEntity;
import fr.tvmp.irrest.server.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "remboursement")
public class RemboursementEntity extends CPAMEntity implements ToDTO<RemboursementDTO> {
    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NonNull PatientEntity patient;

    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NonNull MedecinEntity medecin; // We should have an abstract ProfessionnelSante

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    Set<ARemboursable> remboursables;

    @Override
    public String toString(){
        return "Traitement: " + super.toString();
    }

    @Override
    public RemboursementDTO toDTO() {
        return new RemboursementDTO(
                getId(),
                getPatient().getId(),
                getMedecin().getId(),
                getRemboursables().stream().map(ARemboursable::toDTO).collect(Collectors.toList())
        );
    }
}
