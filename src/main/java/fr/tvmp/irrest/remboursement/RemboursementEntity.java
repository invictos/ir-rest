package fr.tvmp.irrest.remboursement;

import fr.tvmp.irrest.CPAMEntity;
import fr.tvmp.irrest.user.medecin.MedecinEntity;
import fr.tvmp.irrest.user.patient.PatientEntity;
import lombok.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.*;
import java.util.List;

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
    @NonNull MedecinEntity medecin;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "remboursement")
    List<RemboursableEntity> objets;
}
