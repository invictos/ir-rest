package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.CPAMEntity;
import fr.tvmp.irrest.dto.TraitementNewDTO;
import fr.tvmp.irrest.traitement.donneemedicale.DonneeMedicaleEntity;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "remboursement")
public class RemboursementEntity extends CPAMEntity {

    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NonNull PatientEntity patient;

    @JsonbTransient
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NonNull MedecinEntity medecin; // We should have an abstract ProfessionnelSante

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "remboursement")
    Set<RemboursableEntity> remboursables;

    @Override
    public String toString(){
        return "Traitement: " + super.toString();
    }
}
