package fr.tvmp.irrest.server.traitement;

import fr.tvmp.irrest.common.dto.traitement.TraitementDTO;
import fr.tvmp.irrest.server.CPAMEntity;
import fr.tvmp.irrest.server.common.ToDTO;
import fr.tvmp.irrest.server.traitement.donneemedicale.DonneeMedicaleEntity;
import fr.tvmp.irrest.common.dto.traitement.TraitementNewDTO;
import fr.tvmp.irrest.server.user.medecin.MedecinEntity;
import fr.tvmp.irrest.server.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.*;
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
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
@Table(name = "traitement")
public class TraitementEntity extends CPAMEntity implements ToDTO {

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @EqualsAndHashCode.Exclude
    MedecinEntity medecin;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @EqualsAndHashCode.Exclude
    PatientEntity patient;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "traitement")
    Set<DonneeMedicaleEntity> donneesMedicales;

    public TraitementEntity(@NonNull TraitementNewDTO traitementNewDTO, @NonNull MedecinEntity medecin, @NonNull PatientEntity patient){
        super();
        this.setMedecin(medecin);
        this.setPatient(patient);
        this.setDonneesMedicales(
                traitementNewDTO.getDonnees()
                        .stream().map(dmDTO -> new DonneeMedicaleEntity(dmDTO, this))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public String toString(){
        return "Traitement: " + super.toString();
    }

    @Override
    public TraitementDTO toDTO() {
        return new TraitementDTO(
                getId(),
                getMedecin().getId(),
                getPatient().getId(),
                getDonneesMedicales().stream()
                        .map(DonneeMedicaleEntity::toDTO)
                        .collect(Collectors.toList())
        );
    }
}