package fr.tvmp.irrest.medical;

import fr.tvmp.irrest.CPAMEntity;
import fr.tvmp.irrest.medical.donneemedicale.DonneeMedicaleEntity;
import fr.tvmp.irrest.dto.TraitementNewDTO;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.*;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.config.PropertyVisibilityStrategy;
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
@Table(name = "traitement")
public class TraitementEntity extends CPAMEntity {

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
                traitementNewDTO.getDonneesMedicales()
                        .stream().map(dmDTO -> new DonneeMedicaleEntity(dmDTO, this))
                        .collect(Collectors.toSet())
        );
    }
    @JsonbProperty(value="medecin")
    public UUID getMedecinUUID(){
        return getMedecin().getId();
    }

    @Override
    public String toString(){
        return "Traitement: " + super.toString();
    }
}